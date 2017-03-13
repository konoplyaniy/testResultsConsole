package web.Views;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean
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
    private ArrayList<EventEntity> events;

    private boolean advancedBuildChecked = false;
    private boolean clickedBuild = false;


    public BarChartModel getModelBySysweb() {
        return modelBySysweb;
    }

    @PostConstruct
    public void init() {
        createBarChartByLocale();
        createBarChartBySysweb();
        createLineChartByDates();
    }

    private void createBarChartByLocale() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        ArrayList<EventEntity> eventList = getEvents();
//      here key is locale, value is count of failed test in this locale
        HashMap<String, Integer> map = new HashMap<>();
        eventList.forEach(event -> {
            String locale = event.getLocaleByLocaleId().getLocale();
            if (!map.containsKey(locale)) {
                map.put(locale, 1);
            } else {
                int count = map.get(locale) + 1;
                map.put(locale, count);
            }
        });
        modelByLocale = new BarChartModel();
        ChartSeries chartSeries;
        /*System.out.println("map size =" + map.size());*/
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            chartSeries = new ChartSeries();
            chartSeries.setLabel(entry.getKey() + " locale");
            chartSeries.set(entry.getKey(), entry.getValue());
            modelByLocale.addSeries(chartSeries);
        }
        Axis xAxis = modelByLocale.getAxis(AxisType.X);
        xAxis.setLabel("Locales");
        Axis yAxis = modelByLocale.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        modelByLocale.setLegendPosition("e");
        modelByLocale.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        modelByLocale.setTitle("Failed tests for today " + formatter.format(date));
    }

    private ArrayList<EventEntity> getEvents() {
        EventService service = new EventService();
        return service.findByCurrentMonthEvents();
    }

    // bad working if Chart series is added 8-9 times
    private void createBarChartBySysweb() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        ArrayList<EventEntity> eventList = getEvents();
//      here key is locale, value is count of failed test in this locale
        HashMap<String, Integer> map = new HashMap<>();
        eventList.forEach(event -> {
            String sysweb = event.getSyswebBySyswebId().getName();
            if (!map.containsKey(sysweb)) {
                map.put(sysweb, 1);
            } else {
                int count = map.get(sysweb) + 1;
                map.put(sysweb, count);
            }
        });

        modelBySysweb = new BarChartModel();
        ChartSeries chartSeries;
        /*System.out.println("sys web map size =" + map.size());*/
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            chartSeries = new ChartSeries();
            chartSeries.setLabel(entry.getKey());
            chartSeries.set(entry.getKey(), entry.getValue());
            modelBySysweb.addSeries(chartSeries);
        }
        Axis xAxis = modelBySysweb.getAxis(AxisType.X);
        xAxis.setLabel("Syswebs");
        Axis yAxis = modelBySysweb.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        modelBySysweb.setLegendPosition("ne");
        modelBySysweb.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        modelBySysweb.setTitle("Failed tests for today " + formatter.format(date));
    }

    private void createLineChartByDates() {
        // events per Day
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        EventService service = new EventService();

//      here get start date first day in month
        Date startDate;
        Calendar calendar_start = Calendar.getInstance();
        calendar_start.set(Calendar.DAY_OF_MONTH, calendar_start.getActualMinimum(Calendar.DAY_OF_MONTH));
        startDate = calendar_start.getTime();

        Date endDate = new Date();

//        Object where will be saved all data needed to build diagram (in this case date and count of failed tests)
        LineChartSeries series = new LineChartSeries();
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

//        per day add to series date and count of failed tests
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            ArrayList<EventEntity> eventList = service.findByDayEvents(date);
            series.set(formatter.format(date), eventList.size());
        }

        modelPerDate = new LineChartModel();
        modelPerDate.addSeries(series);
        modelPerDate.setTitle("Failed tests for period: " + formatter.format(startDate) + " - " + formatter.format(endDate));
        modelPerDate.getAxis(AxisType.Y).setLabel("Count");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
        modelPerDate.getAxes().put(AxisType.X, axis);
    }

    public BarChartModel getModelByLocale() {
        return modelByLocale;
    }

    public LineChartModel getModelPerDate() {
        return modelPerDate;
    }


    /*FOR CUSTOM BUILD*/

    public BarChartModel getModelBySyswebCustom() {
        return modelBySyswebCustom;
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

    private ArrayList<EventEntity> getEventsCustom() {
        EventService service = new EventService();
        return service.findBySelected(getClazzName(), getTestName(), getSysweb(), getLocale(), getStartDate(), getEndDate());
    }

    private void createBarChartByLocaleCustom() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<EventEntity> eventList = events;
//      here key is locale, value is count of failed test in this locale
        HashMap<String, Integer> map = new HashMap<>();
        eventList.forEach(event -> {
            String locale = event.getLocaleByLocaleId().getLocale();
            if (!map.containsKey(locale)) {
                map.put(locale, 1);
            } else {
                int count = map.get(locale) + 1;
                map.put(locale, count);
            }
        });
        modelByLocaleCustom = new BarChartModel();
        ChartSeries chartSeries;
        /*System.out.println("map size =" + map.size());*/
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            chartSeries = new ChartSeries();
            chartSeries.setLabel(entry.getKey() + " locale");
            chartSeries.set(entry.getKey(), entry.getValue());
            modelByLocaleCustom.addSeries(chartSeries);
        }
        Axis xAxis = modelByLocaleCustom.getAxis(AxisType.X);
        xAxis.setLabel("Locales");
        Axis yAxis = modelByLocaleCustom.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        modelByLocaleCustom.setLegendPosition("e");
        modelByLocaleCustom.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        modelByLocaleCustom.setTitle("Failed tests for period: " + formatter.format(getStartDate()) + " - " + formatter.format(getEndDate()));
        modelByLocaleCustom.setAnimate(true);
    }

    // bad working if Chart series is added 8-9 times
    private void createBarChartBySyswebCustom() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<EventEntity> eventList = events;
//      here key is locale, value is count of failed test in this locale
        HashMap<String, Integer> map = new HashMap<>();
        eventList.forEach(event -> {
            String sysweb = event.getSyswebBySyswebId().getName();
            if (!map.containsKey(sysweb)) {
                map.put(sysweb, 1);
            } else {
                int count = map.get(sysweb) + 1;
                map.put(sysweb, count);
            }
        });

        modelBySyswebCustom = new BarChartModel();
        ChartSeries chartSeries;
        /*System.out.println("sys web map size =" + map.size());*/
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            chartSeries = new ChartSeries();
            chartSeries.setLabel(entry.getKey());
            chartSeries.set(entry.getKey(), entry.getValue());
            modelBySyswebCustom.addSeries(chartSeries);
        }
        Axis xAxis = modelBySyswebCustom.getAxis(AxisType.X);
        xAxis.setLabel("Syswebs");
        Axis yAxis = modelBySyswebCustom.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        modelBySyswebCustom.setLegendPosition("ne");
        modelBySyswebCustom.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        modelBySyswebCustom.setTitle("Failed tests for period: " + formatter.format(getStartDate()) + " - " + formatter.format(getEndDate()));
        modelBySyswebCustom.setAnimate(true);
    }

    private void createLineChartByDatesCustom() {
        // events per Day
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        EventService service = new EventService();
//        initialize start end date for building line diagram
        Date startDate = getStartDate();
        Date endDate = getEndDate();

//        Object where will be saved all data needed to build diagram (in this case date and count of failed tests)
        LineChartSeries series = new LineChartSeries();
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

//        per day add to series date and count of failed tests
        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            ArrayList<EventEntity> eventList = service.findBySelectedDay(getClazzName(), getTestName(), getSysweb(), getLocale(), date);
            series.set(formatter.format(date), eventList.size());
        }

        lineChartModelCustom = new LineChartModel();
        lineChartModelCustom.addSeries(series);
        lineChartModelCustom.setTitle("Failed tests for period: " + formatter.format(getStartDate()) + " - " + formatter.format(getEndDate()));
        lineChartModelCustom.getAxis(AxisType.Y).setLabel("Count");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
        lineChartModelCustom.getAxes().put(AxisType.X, axis);
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
        return advancedBuildChecked;
    }

    public void setAdvancedBuildChecked(boolean advancedBuildChecked) {
        this.advancedBuildChecked = advancedBuildChecked;
    }

    public void clickBuildButton() {
        if (getStartDate() != null && getEndDate() != null &&
                getTestName() != null && getSysweb() != null
                && getLocale() != null) {
            events = getEventsCustom();
            createLineChartByDatesCustom();
            createBarChartBySyswebCustom();
            createBarChartByLocaleCustom();
            clickedBuild = true;
        }
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

}
