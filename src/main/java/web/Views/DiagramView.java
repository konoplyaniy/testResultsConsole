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
    private LineChartModel modelPerDate;
    private BarChartModel modelByLocale;
    private BarChartModel modelBySysweb;

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
        modelByLocale.setTitle("Failed tests for current month");
    }

    private ArrayList<EventEntity> getEvents() {
        EventService service = new EventService();
        return service.findByCurrentMonthEvents();
    }

    // bad working if Chart series is added 8-9 times
    private void createBarChartBySysweb() {
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
        modelBySysweb.setTitle("Failed tests for current month");
    }

    private void createLineChartByDates() {
        // events per Day
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        EventService service = new EventService();

//      here get start date first day in month
        Date startDate;
        Calendar calendar_start = Calendar.getInstance();
        calendar_start.set(Calendar.DAY_OF_MONTH,calendar_start.getActualMinimum(Calendar.DAY_OF_MONTH));
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

}
