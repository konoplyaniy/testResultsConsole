package web.exporter;

import db_worker.HibernateUtil;
import db_worker.dao.EventDao;
import db_worker.entities.EventEntity;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean
@SessionScoped
public class EventTableExporter implements Serializable {
    public static Session session;

    static {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    private List<EventEntity> events;

    private ArrayList<EventEntity> eventsComAuLocale;
    private ArrayList<EventEntity> eventsCoUkLocale;
    private ArrayList<EventEntity> eventsInLocale;
    private ArrayList<EventEntity> eventsCoNzLocale;
    private ArrayList<EventEntity> eventsAeLocale;

    private ArrayList<ListEntities> eventByLocale;
    private EventEntity selectedEvent;
    private List<EventEntity> selectedEvents;

    @PostConstruct
    public void init() {
        Transaction tr = session.beginTransaction();
        events = new EventDao(session).findByDayEvents(new Date());
        tr.commit();

        eventsComAuLocale = new ArrayList<>();
        eventsCoUkLocale = new ArrayList<>();
        eventsInLocale = new ArrayList<>();
        eventsCoNzLocale = new ArrayList<>();
        eventsAeLocale = new ArrayList<>();
        System.out.println("events size " + events.size());

        events.forEach(eventEntity -> {
            switch (eventEntity.getLocaleByLocaleId().getLocale().toLowerCase()) {
                case "com.au":
                    System.out.println("add COM.AU locale event");
                    eventsComAuLocale.add(eventEntity);
                    break;
                case "co.uk":
                    System.out.println("add CO.UK locale event");
                    eventsCoUkLocale.add(eventEntity);
                    break;
                case "in":
                    System.out.println("add IN locale event");
                    eventsInLocale.add(eventEntity);
                    break;
                case "co.nz":
                    System.out.println("add NZ locale event");
                    eventsCoNzLocale.add(eventEntity);
                    break;
                case "nz":
                    System.out.println("add NZ locale event");
                    eventsCoNzLocale.add(eventEntity);
                    break;
                case "ae":
                    System.out.println("add AE locale event");
                    eventsAeLocale.add(eventEntity);
                    break;
            }
        });
        eventByLocale = new ArrayList<>();
        eventByLocale.add(new ListEntities("com.au", eventsComAuLocale));
        eventByLocale.add(new ListEntities("co.uk", eventsCoUkLocale));
        eventByLocale.add(new ListEntities("co.nz", eventsCoNzLocale));
        eventByLocale.add(new ListEntities("in", eventsInLocale));
        eventByLocale.add(new ListEntities("ae", eventsAeLocale));
    }

    public ArrayList<String> getStatusesList() {
        ArrayList<String> statuses = new ArrayList<>();
        statuses.add("Checked");
        statuses.add("Checked, Issue");
        statuses.add("Checked, Fixed");
        statuses.add("Unchecked");
        return statuses;
    }

    public class ListEntities implements Serializable {
        String locale;
        ArrayList<EventEntity> events;
        ArrayList<EventEntity> uncheckedEvents;

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public ArrayList<EventEntity> getEvents() {
            return events;
        }

        public ArrayList<EventEntity> getUncheckedEvents() {
            uncheckedEvents = new ArrayList<>();
            events.forEach(event -> {
                if (!event.getStatus().toLowerCase().equals("checked")) {
                    uncheckedEvents.add(event);
                }
            });
            return uncheckedEvents;
        }

        public void setUncheckedEvents(ArrayList<EventEntity> uncheckedEvents) {
            this.uncheckedEvents = uncheckedEvents;
        }

        public void setEvents(ArrayList<EventEntity> events) {
            this.events = events;
        }

        public ListEntities(String locale, ArrayList<EventEntity> events) {
            this.locale = locale;
            this.events = events;
        }

        public int getFailedTestsCount() {
            return events.size();
        }
    }

    public ArrayList<ListEntities> getEventByLocale() {
        return eventByLocale;
    }

    public void setEventByLocale(ArrayList<ListEntities> eventByLocale) {
        this.eventByLocale = eventByLocale;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object newValue = event.getNewValue();
        EventEntity eventEntity = (EventEntity) ((DataTable) event.getComponent()).getRowData();
        System.out.println("edit event,  data = " + eventEntity.getData());

        String columnName = event.getColumn().getHeaderText();
        System.out.println("edit cell " + columnName);

        System.out.println("new value " + event.getNewValue());
        System.out.println("old value " + event.getOldValue());
        if (columnName.equals("Status")) {
            eventEntity.setStatus(newValue.toString());
        }
        if (columnName.equals("Ticket")) {
            eventEntity.setTicket(newValue.toString());
        }

        Transaction tr = session.beginTransaction();
        new EventDao(session).update(eventEntity);
        tr.commit();
        System.out.println("end cell edit ");
    }

    public HashSet<String> getLocales() {
        HashSet<String> set = new HashSet<>();
        ArrayList<EventEntity> events = (ArrayList<EventEntity>) getEvents();
        events.forEach(eventEntity -> set.add(eventEntity.getLocaleByLocaleId().getLocale()));
        return set;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public EventEntity getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(EventEntity selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public List<EventEntity> getSelectedEvents() {
        return selectedEvents;
    }

    public void setSelectedEvents(List<EventEntity> selectedEvents) {
        this.selectedEvents = selectedEvents;
    }

    public List<EventEntity> getEventsComAuLocale() {
        return eventsComAuLocale;
    }

    public String getCurrentDate() {
        Date today = new Date();
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(today);
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

}

