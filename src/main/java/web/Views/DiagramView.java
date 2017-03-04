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

/**
 * Created by Sergiy.K on 14-Feb-17.
 */
@ManagedBean
public class DiagramView implements Serializable {
    private LineChartModel model;
    private BarChartModel modelByLocale;
    private BarChartModel modelBySysweb;

    public BarChartModel getModelBySysweb() {
        return modelBySysweb;
    }

    @PostConstruct
    public void init() {
        createModel();
        createSyswebModel();
        createLocaleModel();
    }

    private void createSyswebModel() {
        EventService service = new EventService();
        ArrayList<EventEntity> entities1;
        ArrayList<EventEntity> entities2;
        ArrayList<EventEntity> entities3;

        entities1 = (ArrayList<EventEntity>) service.findBySysweb("SYSWEB4.UK.SYRAHOST.COM");
        entities2 = (ArrayList<EventEntity>) service.findBySysweb("SYSWEB3.UK.SYRAHOST.COM");
        entities3 = (ArrayList<EventEntity>) service.findBySysweb("SYSWEB5.UK.SYRAHOST.COM");

        ChartSeries sysweb7 = new ChartSeries();
        ChartSeries sysweb3 = new ChartSeries();
        ChartSeries sysweb4 = new ChartSeries();

        sysweb7.setLabel("SYSWEB4.UK.SYRAHOST.COM");
        sysweb7.set("SYSWEB4.UK.SYRAHOST.COM", entities1.size());

        sysweb3.setLabel("SYSWEB3.UK.SYRAHOST.COM");
        sysweb3.set("SYSWEB3.UK.SYRAHOST.COM", entities2.size());

        sysweb4.setLabel("SYSWEB5.UK.SYRAHOST.COM");
        sysweb4.set("SYSWEB5.UK.SYRAHOST.COM", entities3.size());

        modelBySysweb = new BarChartModel();
        modelBySysweb.addSeries(sysweb3);
        modelBySysweb.addSeries(sysweb4);
        modelBySysweb.addSeries(sysweb7);
        modelBySysweb.setLegendPosition("ne");
        modelBySysweb.setTitle("Syswebs");
        modelBySysweb.setAnimate(true);
    }

    private void createLocaleModel() {
        EventService service = new EventService();
        ArrayList<EventEntity> entities1;
        ArrayList<EventEntity> entities2;

        entities1 = (ArrayList<EventEntity>) service.findByLocale("CO.UK");
        entities2 = (ArrayList<EventEntity>) service.findByLocale("COM.AU");

        ChartSeries uaLocale = new ChartSeries();
        ChartSeries comAuLocale = new ChartSeries();

        uaLocale.setLabel("co.uk locale");
        uaLocale.set("au", entities1.size());

        comAuLocale.setLabel(".com.au locale");
        comAuLocale.set("com.au", entities2.size());

        modelByLocale = new BarChartModel();
        modelByLocale.addSeries(comAuLocale);
        modelByLocale.addSeries(uaLocale);
        modelByLocale.setLegendPosition("ne");
        modelByLocale.setTitle("Locales");
        modelByLocale.setAnimate(true);
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

}
