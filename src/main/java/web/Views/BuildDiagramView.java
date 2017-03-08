package web.Views;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Sergiy.K on 06-Mar-17.
 */
@ManagedBean
public class BuildDiagramView implements Serializable {
    private LineChartModel lineChartModel;
    private BarChartModel modelByLocale;
    private BarChartModel modelBySysweb;
    private String sysweb;
    private String testName;
    private String locale;
    private ArrayList<String> locales;
    private Date startDate;
    private Date endDate;

    private boolean advancedBuildChecked = false;
    private boolean syswebChecked;
    private boolean localeChecked;
    private boolean clickedBuild = false;

    public BarChartModel getModelBySysweb() {
        return modelBySysweb;
    }

    private void createBarChartByLocale(){
        EventService service = new EventService();
        ArrayList<EventEntity> eventList;
        eventList = (ArrayList<EventEntity>) service.findByTestNameBetweenDates(getTestName(), getStartDate(), getEndDate());
//      here key is locale, value is count of failed test in this locale
        HashMap<String, Integer> map = new HashMap<>();
        eventList.forEach(event->{
            String locale = event.getLocaleByLocaleId().getLocale();
            if (!map.containsKey(locale)){
                map.put(locale, 1);
            }else {
                int count = map.get(locale) + 1;
                map.put(locale, count);
            }
        });

        modelByLocale = new BarChartModel();
        ChartSeries chartSeries;
        /*System.out.println("map size =" + map.size());*/

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            chartSeries  = new ChartSeries();
            chartSeries.setLabel(entry.getKey() + " locale");
            chartSeries.set(entry.getKey(), entry.getValue());
            modelByLocale.addSeries(chartSeries);
        }
        Axis xAxis = modelByLocale.getAxis(AxisType.X);
        xAxis.setLabel("Locales");
        Axis yAxis = modelByLocale.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        modelByLocale.setLegendPosition("ne");
        modelByLocale.setTitle("Test: " + getTestName() + " failed by locales");
        modelByLocale.setAnimate(true);
    }

    private void createBarChartBySysweb(){
        EventService service = new EventService();
        ArrayList<EventEntity> eventList;
        eventList = (ArrayList<EventEntity>) service.findByTestNameBetweenDates(getTestName(), getStartDate(), getEndDate());
//      here key is locale, value is count of failed test in this locale
        HashMap<String, Integer> map = new HashMap<>();
        eventList.forEach(event->{
            String sysweb = event.getSyswebBySyswebId().getName();
            if (!map.containsKey(sysweb)){
                map.put(sysweb, 1);
            }else {
                int count = map.get(sysweb) + 1;
                map.put(sysweb, count);
            }
        });

        modelBySysweb = new BarChartModel();
        ChartSeries chartSeries;
        /*System.out.println("map size =" + map.size());*/

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            chartSeries  = new ChartSeries();
            chartSeries.setLabel(entry.getKey());
            chartSeries.set(entry.getKey(), entry.getValue());
            modelBySysweb.addSeries(chartSeries);
        }
        Axis xAxis = modelBySysweb.getAxis(AxisType.X);
        xAxis.setLabel("Syswebs");
        Axis yAxis = modelBySysweb.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        modelBySysweb.setLegendPosition("ne");
        modelBySysweb.setTitle("Syswebs");
        modelBySysweb.setAnimate(true);
    }

    private void createLineChartByTestName() {
        // tests per Day
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
                Date startt = (Date) date.clone();
                Date endd = (Date) date.clone();
                endd.setHours(23);
                endd.setMinutes(59);
                endd.setSeconds(59);
                /*System.out.println("CURRENT ITERATION START DATE: " + startt);
                System.out.println("CURRENT ITERATION END DATE: " + endd);*/
                ArrayList<EventEntity> eventList;
                eventList = (ArrayList<EventEntity>) service.findByTestNameBetweenDates(getTestName(), startt, endd);
                series.set(formatter.format(startt), eventList.size());
                /*System.out.println(startt + ": " + eventList.size());*/
            }

        lineChartModel = new LineChartModel();
        lineChartModel.addSeries(series);
        lineChartModel.setTitle("Failed tests " + formatter.format(getStartDate()) + " - " + formatter.format(getEndDate()));
        lineChartModel.getAxis(AxisType.Y).setLabel("Count");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
        lineChartModel.getAxes().put(AxisType.X, axis);
    }

    public BarChartModel getModelByLocale() {
        return modelByLocale;
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
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

    public boolean isSyswebChecked() {
        return syswebChecked;
    }

    public void setSyswebChecked(boolean syswebChecked) {
        this.syswebChecked = syswebChecked;
    }

    public boolean isLocaleChecked() {
        return localeChecked;
    }

    public void setLocaleChecked(boolean localeChecked) {
        this.localeChecked = localeChecked;
    }

    public void clickBuildButton() {
        /*System.out.println("click build button");*/
        if (getStartDate() != null && getEndDate() != null &&
                getTestName() != null && getSysweb() != null
                 && getLocale() != null){
            createLineChartByTestName();
            createBarChartBySysweb();
            createBarChartByLocale();
            clickedBuild = true;
        }
        System.out.println("inputted test name: " + getTestName());
        System.out.println("inputted sysweb: " + getSysweb());
        System.out.println("inputted locale: " + getLocale());
        getStartDate();
        getEndDate();
        /*   Eshop buying     2017-01-26 17:58:32 start   2017-01-31 17:36:02 end */
    }

    public void clickHideButton(){
            clickedBuild = false;
    }

    public Date getStartDate() {
        if (startDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getDefault());
            formatter.format(startDate);
            /*System.out.println("Get start date " + formatter.format(startDate));*/
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
            /*System.out.println("Get end date " + formatter.format(endDate));*/
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
