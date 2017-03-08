package web.Views;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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
    private LineChartModel model;
    private BarChartModel modelByLocale;
    private BarChartModel modelBySysweb;
    private String sysweb;
    private String testName;
    private String locale;
    private ArrayList<String> locales;
    private Date startDate;
    private Date endDate;

    private boolean advancedBuildChecked;
    private boolean syswebChecked;
    private boolean localeChecked;
    private boolean clickedBuild = false;


    public BarChartModel getModelBySysweb() {
        return modelBySysweb;
    }

    @PostConstruct
    public void init() {
//        createModel();
//        createSyswebModel();
//        createLocaleModel();
    }

//    private void createSyswebModel() {
//        EventService service = new EventService();
//        ArrayList<EventEntity> entities1;
//        ArrayList<EventEntity> entities2;
//        ArrayList<EventEntity> entities3;
//
//        entities1 = (ArrayList<EventEntity>) service.findBySysweb("SYSWEB4.UK.SYRAHOST.COM");
//        entities2 = (ArrayList<EventEntity>) service.findBySysweb("SYSWEB3.UK.SYRAHOST.COM");
//        entities3 = (ArrayList<EventEntity>) service.findBySysweb("SYSWEB5.UK.SYRAHOST.COM");
//
//        ChartSeries sysweb7 = new ChartSeries();
//        ChartSeries sysweb3 = new ChartSeries();
//        ChartSeries sysweb4 = new ChartSeries();
//
//        sysweb7.setLabel("SYSWEB4.UK.SYRAHOST.COM");
//        sysweb7.set("SYSWEB4.UK.SYRAHOST.COM", entities1.size());
//
//        sysweb3.setLabel("SYSWEB3.UK.SYRAHOST.COM");
//        sysweb3.set("SYSWEB3.UK.SYRAHOST.COM", entities2.size());
//
//        sysweb4.setLabel("SYSWEB5.UK.SYRAHOST.COM");
//        sysweb4.set("SYSWEB5.UK.SYRAHOST.COM", entities3.size());
//
//        modelBySysweb = new BarChartModel();
//        modelBySysweb.addSeries(sysweb3);
//        modelBySysweb.addSeries(sysweb4);
//        modelBySysweb.addSeries(sysweb7);
//        modelBySysweb.setLegendPosition("ne");
//        modelBySysweb.setTitle("Syswebs");
//        modelBySysweb.setAnimate(true);
//    }

    public static void main(String[] args) {
        BuildDiagramView b = new BuildDiagramView();
        b.setLocale("CO.UK");
        b.createSyswebModel();
    }

    private void createSyswebModel() {
        // tests by locale
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ss = formatter.parse("2017-03-04");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (getLocale() != null) {
            EventService service = new EventService();
            ArrayList<EventEntity> entities1;
            Date startDate = new Date();

            try {
                startDate = formatter.parse("2017-02-04");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date endDate = new Date();
            try {
                endDate = formatter.parse("2017-03-07");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar start = Calendar.getInstance();
            start.setTime(startDate);
            Calendar end = Calendar.getInstance();
            end.setTime(endDate);

            for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                // Do your job here with `date`.

                Date startt = (Date) date.clone();
                Date endd = (Date) date.clone();
                endd.setHours(23);
                endd.setMinutes(59);
                endd.setSeconds(59);
                System.out.println("CURRENT ITERATION START DATE: " + startt);
                System.out.println("CURRENT ITERATION END DATE: " + endd);
                entities1 = (ArrayList<EventEntity>) service.findBySyswebBetweenDates("ANSWER GET FROM : SYSWEB5.AU.SYRAHOST.COM", startt, endd);
                LineChartSeries series = new LineChartSeries();
                series.set(date, entities1.size());
                System.out.println(date + ": " + entities1.size());
            }

//            entities1 = (ArrayList<EventEntity>) service.findBySyswebBetweenDates(getSysweb(), startDate, endDate);
//            LineChartModel lineChartModel = new LineChartModel();
//            LineChartSeries series = new LineChartSeries();
//            series.setLabel("Checked tests");
//            series.set(startDate, entities1.size());
//
//
//            String endd = formatter.format(endDate);
//            String startt = formatter.format(startDate);
//            lineChartModel.addSeries(series);
//            model = new LineChartModel();
//            model.addSeries(series);
//            model.setTitle("Failed tests " + startt + " - " + endd);
//            model.getAxis(AxisType.Y).setLabel("Count");
//            DateAxis axis = new DateAxis("Dates");
//            axis.setTickAngle(-50);
////        axis.setMax("2017-28-02");
//            axis.setTickFormat("%b %#d, %y");
//            model.getAxes().put(AxisType.X, axis);
        }
    }

    private Date addNewDay(Date date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateParse = date.toString();
        Date result = new Date();
        result = formatter.parse(LocalDate.parse(dateParse).plusDays(1).toString());
        return result;
    }

    public BarChartModel getModelByLocale() {
        return modelByLocale;
    }

    private void createModel() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LineChartModel lineChartModel = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
        series.setLabel("Checked tests");

        EventService service = new EventService();

        Date startDate = new Date();
        startDate.getMonth();
        startDate.setYear(117);
        startDate.setMonth(1);
        startDate.setDate(20);
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);

        Date endDate = new Date();
        endDate.setYear(117);
        endDate.setMonth(1);
        endDate.setDate(20);
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);

        startDate.setDate(20);
        endDate.setDate(20);
        ArrayList<EventEntity> entities3;
        entities3 = (ArrayList<EventEntity>) service.findBetweenDate(startDate, endDate);
        String startt = formatter.format(startDate);
        series.set(formatter.format(startDate), entities3.size());

        startDate.setDate(21);
        endDate.setDate(21);
        ArrayList<EventEntity> entities4;
        entities4 = (ArrayList<EventEntity>) service.findBetweenDate(startDate, endDate);
        series.set(formatter.format(startDate), entities4.size());

        startDate.setDate(23);
        endDate.setDate(23);
        System.out.println();
        ArrayList<EventEntity> entities5;
        entities5 = (ArrayList<EventEntity>) service.findBetweenDate(startDate, endDate);
        String endd = formatter.format(endDate);
        series.set(formatter.format(startDate), entities5.size());

        lineChartModel.addSeries(series);

        model = new LineChartModel();
        model.addSeries(series);
        model.setTitle("Failed tests " + startt + " - " + endd);
        model.getAxis(AxisType.Y).setLabel("Count");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
//        axis.setMax("2017-28-02");
        axis.setTickFormat("%b %#d, %y");
        model.getAxes().put(AxisType.X, axis);
    }

    public LineChartModel getModel() {
        return model;
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

    public ArrayList<String> getLocales() {
        return locales;
    }

    public void setLocales(ArrayList<String> locales) {
        this.locales = locales;
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
        System.out.println("click build button");
        if (getStartDate() != null && getEndDate() != null &&
                getTestName() != null && getSysweb() != null
                 && getLocale() != null){
            createModel();
            clickedBuild = true;
        }else {
            System.out.println("should appear message with error");

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Input data for build diagram"));
        }
        System.out.println("inputted test name: " + getTestName());
        System.out.println("inputted sysweb: " + getSysweb());
        System.out.println("inputted locale: " + getLocale());
        getStartDate();
        getEndDate();
    }

    public void clickHideBtton(){
            clickedBuild = false;
    }

    public Date getStartDate() {
        if (startDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getDefault());
            formatter.format(startDate);
            System.out.println("Get start date " + formatter.format(startDate));
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
            System.out.println("Get end date " + formatter.format(endDate));
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
