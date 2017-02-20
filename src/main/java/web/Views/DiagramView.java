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

    public BarChartModel getModelByClass() {
        return modelByClass;
    }

    private BarChartModel modelByClass;

    private boolean isClickedBuild = false;

    public boolean isClickedBuild() {
        return isClickedBuild;
    }

    public void setClickedBuild(boolean clickedBuild) {
        isClickedBuild = clickedBuild;
    }

    public BarChartModel getModelBySysweb() {
        return modelBySysweb;
    }

    @PostConstruct
    public void init() {
        createModel();
        createSyswebModel();
        createClassModel();
        createLocaleModel();
    }

    public void clickBuild() {
        System.out.println(isClickedBuild);
        setClickedBuild(true);
        System.out.println("after click build");
        System.out.println(isClickedBuild);
    }

    public void clickHide() {
        System.out.println(isClickedBuild);
        setClickedBuild(false);
        System.out.println("after click hide");
        System.out.println(isClickedBuild);
    }

    private void createClassModel() {
        modelByClass = new BarChartModel();

        EventService service = new EventService();

        ArrayList<EventEntity> entities = (ArrayList<EventEntity>) service.findAll();
        ArrayList<String> clazzes = new ArrayList<>();
        for (EventEntity eventEntity : entities) {
            clazzes.add(eventEntity.getTestByTestId().getClazzByClassId().getName());
        }

        System.out.println("entities " + entities.size());

        HashMap<String, Integer> map = new HashMap<>();

        Set<Map.Entry<String, Integer>> set = map.entrySet();
        for (EventEntity entity : entities) {
            System.out.println("1");
            for (Map.Entry<String, Integer> entry : set) {
                System.out.println("2");
                String className = entity.getTestByTestId().getClazzByClassId().getName();
                if (entry.getValue().equals(className)) {
                    System.out.println("2.1");
                    map.put(className, entry.getValue() + 1);
                } else {
                    System.out.println("2.2");
                    map.put(className, 1);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ChartSeries clazzInfo = new ChartSeries();
            clazzInfo.setLabel(entry.getKey());
            clazzInfo.set(entry.getKey(), entry.getValue());
            modelByClass.addSeries(clazzInfo);
        }
        System.out.println("map " + map.size());
        modelByClass.setLegendPosition("ne");
        modelByClass.setTitle("Classes");
        modelByClass.setAnimate(true);
    }

    private void createSyswebModel() {
        EventService service = new EventService();
        ArrayList<EventEntity> entities1;
        ArrayList<EventEntity> entities2;
        ArrayList<EventEntity> entities3;

        entities1 = (ArrayList<EventEntity>) service.findBySysweb("sysweb7.syrahost.com.au");
        entities2 = (ArrayList<EventEntity>) service.findBySysweb("sysweb3.syrahost.com.au");
        entities3 = (ArrayList<EventEntity>) service.findBySysweb("sysweb4.syrahost.com.au");

        ChartSeries sysweb7 = new ChartSeries();
        ChartSeries sysweb3 = new ChartSeries();
        ChartSeries sysweb4 = new ChartSeries();

        sysweb7.setLabel("sysweb7.syrahost.com.au");
        sysweb7.set("sysweb7.syrahost.com.au", entities1.size());

        sysweb3.setLabel("sysweb3.syrahost.com.au");
        sysweb3.set("sysweb3.syrahost.com.au", entities2.size());

        sysweb4.setLabel("sysweb4.syrahost.com.au");
        sysweb4.set("sysweb4.syrahost.com.au", entities3.size());

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

        entities1 = (ArrayList<EventEntity>) service.findByLocale(".ua");
        entities2 = (ArrayList<EventEntity>) service.findByLocale(".com.au");

        ChartSeries uaLocale = new ChartSeries();
        ChartSeries comAuLocale = new ChartSeries();

        uaLocale.setLabel(".ua locale");
        uaLocale.set("au", entities1.size());

        comAuLocale.setLabel(".com.au locale");
        comAuLocale.set("com.au", entities2.size());

        modelByLocale = new BarChartModel();
        modelByLocale.addSeries(comAuLocale);
        modelByLocale.addSeries(uaLocale);
        modelByLocale.setLegendPosition("ne");
        modelByLocale.setTitle("Locales");
        modelByLocale.setAnimate(true);
        System.out.println(modelByLocale);
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
        startDate.setMonth(0);
        startDate.setDate(20);
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);

        Date endDate = new Date();
        endDate.setYear(117);
        endDate.setMonth(0);
        endDate.setDate(20);
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);

        startDate.setDate(26);
        endDate.setDate(26);
        ArrayList<EventEntity> entities3;
        entities3 = (ArrayList<EventEntity>) service.findBetweenDate(startDate, endDate);
        String startt = formatter.format(startDate);
        series.set(formatter.format(startDate), entities3.size());

        startDate.setDate(27);
        endDate.setDate(27);
        ArrayList<EventEntity> entities4;
        entities4 = (ArrayList<EventEntity>) service.findBetweenDate(startDate, endDate);
        series.set(formatter.format(startDate), entities4.size());

        startDate.setDate(31);
        endDate.setDate(31);
        ArrayList<EventEntity> entities5;
        entities5 = (ArrayList<EventEntity>) service.findBetweenDate(startDate, endDate);
        String endd = formatter.format(endDate);
        series.set(formatter.format(startDate), entities5.size());

//        String endd = formatter.format(endDate);
        lineChartModel.addSeries(series);

        model = new LineChartModel();
        model.addSeries(series);
        model.setTitle("Failed tests " + startt + " - " + endd);
        model.getAxis(AxisType.Y).setLabel("Count");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setMax("2017-02-01");
        axis.setTickFormat("%b %#d, %y");
        model.getAxes().put(AxisType.X, axis);
    }

    public LineChartModel getModel() {
        return model;
    }

}
