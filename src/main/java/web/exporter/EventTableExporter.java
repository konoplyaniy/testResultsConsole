package web.exporter;

import db_worker.dao.BaseDao;
import db_worker.dao.EventDao;
import db_worker.entities.EventEntity;
import db_worker.service.EventService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean
@SessionScoped
public class EventTableExporter implements Serializable {
    private List<EventEntity> events;

    private ArrayList<EventEntity> eventsComAuLocale;
    private ArrayList<EventEntity> eventsCoUkLocale;
    private ArrayList<EventEntity> eventsInLocale;
    private ArrayList<EventEntity> eventsCoNzLocale;

    private ArrayList<ListEntities> eventByLocale;
    private EventEntity selectedEvent;
    private List<EventEntity> selectedEvents;

    @PostConstruct
    public void init() {
        EventService eventService = new EventService();
        events = eventService.findByCurrentDayEvents();
        eventsComAuLocale = new ArrayList<>();
        eventsCoUkLocale = new ArrayList<>();
        eventsInLocale = new ArrayList<>();
        eventsCoNzLocale = new ArrayList<>();
        events.forEach(eventEntity -> {
            switch (eventEntity.getLocaleByLocaleId().getLocale().toLowerCase()) {
                case "com.au":
                    eventsComAuLocale.add(eventEntity);
                    break;
                case "co.uk":
                    eventsCoUkLocale.add(eventEntity);
                    break;
                case "in":
                    eventsInLocale.add(eventEntity);
                    break;
                case "co.nz":
                    eventsCoNzLocale.add(eventEntity);
                    break;
                case "nz":
                    eventsCoNzLocale.add(eventEntity);
                    break;
            }
        });
        eventByLocale = new ArrayList<>();
        eventByLocale.add(new ListEntities("com.au", eventsComAuLocale));
        eventByLocale.add(new ListEntities("co.uk", eventsCoUkLocale));
        eventByLocale.add(new ListEntities("co.nz", eventsCoNzLocale));
        eventByLocale.add(new ListEntities("in", eventsInLocale));
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

    public void changeEventStatus(int eventID, int setStatus) {
        Session session = BaseDao.getSessionFactory().openSession();
        session.beginTransaction();
        EventDao dao = new EventDao(session);
        EventEntity entity = dao.findById(eventID);
        entity.setChecked(setStatus);
        dao.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void onCellEdit(CellEditEvent event) throws ParseException {
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
        new EventService().update(eventEntity);
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

