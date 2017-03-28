package web.Views;

import db_worker.entities.EventEntity;
import db_worker.service.EventService;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean
@SessionScoped
public class DiagramView implements Serializable {
    private LineChartModel modelPerDate;
    private BarChartModel modelByLocale;
    private BarChartModel modelBySysweb;

    /*for custom build*/
    private LineChartModel lineChartModelCustom;
    private BarChartModel modelByLocaleCustom;
    private BarChartModel modelBySyswebCustom;
    private String sysweb = "";
    private HashSet<String> syswebs;
    private String testName = "";
    private String clazzName = "";
    private HashSet<String> clazzNames;
    private HashSet<String> testNames;
    private String locale = "";
    private HashSet<String> locales;
    private Date startDate;
    private Date endDate;

    private boolean advancedBuildChecked = false;
    private boolean clickedBuild = false;

    public BarChartModel getModelBySysweb() {
        return modelBySysweb;
    }

    @PostConstruct
    public void init() {
        modelPerDate = initLineChartModel(false);
        modelByLocale = initBarChartModel(true, false, "", "Locales");
        modelBySysweb = initBarChartModel(false, false, "", "Syswebs");
    }

    //  INIT DROPDOWNS LIST
    private void initDropdownsData() {
        EventService service = new EventService();
        ArrayList<EventEntity> eventList = (ArrayList<EventEntity>) service.findAll();
        HashSet<String> testNames = new HashSet<>();
        HashSet<String> syswebs = new HashSet<>();
        HashSet<String> locales = new HashSet<>();
        HashSet<String> clazzes = new HashSet<>();
        eventList.forEach(eventEntity -> {
            testNames.add(eventEntity.getTestByTestId().getName());
            syswebs.add(eventEntity.getSyswebBySyswebId().getName());
            locales.add(eventEntity.getLocaleByLocaleId().getLocale());
            clazzes.add(eventEntity.getTestByTestId().getClazzByClassId().getName());
        });

        setTestNames(testNames);
        setLocales(locales);
        setSyswebs(syswebs);
        setClazzNames(clazzes);
    }

    public void clickBuildButton() {
        if (getStartDate() != null && getEndDate() != null &&
                getTestName() != null && getSysweb() != null
                && getLocale() != null) {
            if (getStartDate().before(getEndDate())) {
                lineChartModelCustom = initLineChartModel(true);
                modelByLocaleCustom = initBarChartModel(true, true, "locale", "Locales");
                modelBySyswebCustom = initBarChartModel(false, true, "", "Syswebs");
                clickedBuild = true;
            } else {
                setAdvancedBuildChecked(true);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", " Start Date must be earlier then End Date"));
            }
        }
    }

    /**
     * @param isBarChartByLocale if set true will be generated diagram by locale, else by syswebs
     * @param addToLabelString   this label will be displayed after key (like "in locale"  or "SYSWEB4.UK.SYRAHOST.COM sysweb"), could be ""
     * @return BarChart diagram with series
     */
    public BarChartModel initBarChartModel(boolean isBarChartByLocale, boolean isCustomDiagram, String addToLabelString, String label) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        BarChartModel barChartModel = new BarChartModel();
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<EventEntity> events;

        /* init events list*/
        if (!isCustomDiagram) {
            events = getEvents();
        } else events = getEventsCustom();

        /*init map for series*/
        events.forEach(event -> {
            String keyForSearch;
            if (isBarChartByLocale) {
                keyForSearch = event.getLocaleByLocaleId().getLocale();
            } else {
                keyForSearch = event.getSyswebBySyswebId().getName();
            }
            if (!map.containsKey(keyForSearch)) {
                map.put(keyForSearch, 1);
            } else {
                int count = map.get(keyForSearch) + 1;
                map.put(keyForSearch, count);
            }
        });

        /*init series*/
        ChartSeries chartSeries;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            chartSeries = new ChartSeries();
            chartSeries.setLabel(entry.getKey() + " " + addToLabelString);
            chartSeries.set(entry.getKey(), entry.getValue());
            barChartModel.addSeries(chartSeries);
        }

        /*init model settings*/
        Axis xAxis = barChartModel.getAxis(AxisType.X);
        xAxis.setLabel(label);
        Axis yAxis = barChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        barChartModel.setLegendPosition("e");
        barChartModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);

        if (isCustomDiagram) {
            barChartModel.setTitle("Failed tests for period: " + formatter.format(getStartDate()) + " - " + formatter.format(getEndDate()));
            barChartModel.setAnimate(true);
        } else {
            barChartModel.setTitle("Failed tests for today " + formatter.format(new Date()));
        }

        return barChartModel;
    }

    /**
     * @param isCustomDiagram if set true will be generated diagram of failed tests count per day (for current month)
     *                        else by selected by user dates
     * @return LineChart diagram with series
     */
    public LineChartModel initLineChartModel(boolean isCustomDiagram) {
        // events per Day
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        EventService service = new EventService();
        Date startDate;
        Date endDate;

        if (!isCustomDiagram) {
            /*here get start date - first day in month*/
            Calendar calendar_start = Calendar.getInstance();
            calendar_start.set(Calendar.DAY_OF_MONTH, calendar_start.getActualMinimum(Calendar.DAY_OF_MONTH));
            startDate = calendar_start.getTime();
            endDate = new Date();
        } else {
            /*get selected (by user) start and end date if this is not current month diagram */
            startDate = getStartDate();
            endDate = getEndDate();
        }

        LineChartSeries series = new LineChartSeries();
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        /*per day add to series date and count of failed tests*/
        if (isCustomDiagram) {
            for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                ArrayList<EventEntity> eventList = service.findByDayEvents(date);
                series.set(formatter.format(date), eventList.size());
            }
        } else {
            for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                ArrayList<EventEntity> eventList = service.findBySelectedDay(getClazzName(), getTestName(), getSysweb(), getLocale(), date);
                series.set(formatter.format(date), eventList.size());
            }
        }

        /*init model settings*/
        LineChartModel lineChartModel = new LineChartModel();
        lineChartModel.addSeries(series);
        lineChartModel.setTitle("Failed tests for period: " + formatter.format(startDate) + " - " + formatter.format(endDate));
        lineChartModel.getAxis(AxisType.Y).setLabel("Count");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
        lineChartModel.getAxes().put(AxisType.X, axis);

        return lineChartModel;
    }

    private ArrayList<EventEntity> getEvents() {
        EventService service = new EventService();
        return service.findByCurrentMonthEvents();
    }

    private ArrayList<EventEntity> getEventsCustom() {
        EventService service = new EventService();
        return service.findBySelected(getClazzName(), getTestName(), getSysweb(), getLocale(), getStartDate(), getEndDate());
    }

    public BarChartModel getModelByLocale() {
        return modelByLocale;
    }

    public LineChartModel getModelPerDate() {
        return modelPerDate;
    }

    public BarChartModel getModelBySyswebCustom() {
        return modelBySyswebCustom;
    }

    public void clickHideButton() {
        clickedBuild = false;
    }

    public Date getStartDate() {
        if (startDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getDefault());
            formatter.format(startDate);
        }
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        if (endDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getDefault());
            formatter.format(endDate);
        }
        return endDate;
    }

    public HashSet<String> getSyswebs() {
        return syswebs;
    }

    public void setSyswebs(HashSet<String> syswebs) {
        this.syswebs = syswebs;
    }

    public HashSet<String> getTestNames() {
        return testNames;
    }

    public void setTestNames(HashSet<String> testNames) {
        this.testNames = testNames;
    }

    public HashSet<String> getLocales() {
        return locales;
    }

    public void setLocales(HashSet<String> locales) {
        this.locales = locales;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public HashSet<String> getClazzNames() {
        return clazzNames;
    }

    public void setClazzNames(HashSet<String> clazzNames) {
        this.clazzNames = clazzNames;
    }

    public BarChartModel getModelByLocaleCustom() {
        return modelByLocaleCustom;
    }

    public LineChartModel getLineChartModelCustom() {
        return lineChartModelCustom;
    }

    public String getSysweb() {
        return sysweb;
    }

    public void setSysweb(String sysweb) {
        this.sysweb = sysweb;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isClickedBuild() {
        return clickedBuild;
    }

    public void setClickedBuild(boolean clickedBuild) {
        this.clickedBuild = clickedBuild;
    }

    public boolean isAdvancedBuildChecked() {
        initDropdownsData();
        return advancedBuildChecked;
    }

    public void setAdvancedBuildChecked(boolean advancedBuildChecked) {
        this.advancedBuildChecked = advancedBuildChecked;
    }

}