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
    private String checkFlag;
    private EventEntity selectedEvent;
    private List<EventEntity> selectedEvents;

    private EventService eventService;
    private boolean detailClicked = false;

    @PostConstruct
    public void init() {
        eventService = new EventService();
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
                if (event.getChecked() == 0) {
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

    public void clickApplyButton() {
        EventEntity event;
        if (selectedEvents != null) {
            for (EventEntity eventEntity : selectedEvents) {
                event = eventEntity;
                if (eventEntity.getChecked() == 0) {
                    changeEventStatus(event.getEventId(), 1);
                } else {
                    changeEventStatus(event.getEventId(), 1);
                }
            }
        }
    }

    public void clickShowEventDetail() {
        /*System.out.println("click by event with date = " + entity.getData());*/
        detailClicked = true;
        setDetailClicked(true);
    }

    public boolean isDetailClicked() {
        return detailClicked;
    }

    public void setDetailClicked(boolean detailClicked) {
        System.out.println("set " +detailClicked);
        this.detailClicked = detailClicked;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Your changes saved", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) throws ParseException {
        Object newValue = event.getNewValue();
        System.out.println("edit cell");

        System.out.println("new value " + event.getNewValue());
        System.out.println("old value " + event.getOldValue());
        System.out.println("row key " + event.getRowKey());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = event.getRowKey().replace(".0", "");
        System.out.println(dateString);

        Date date = formatter.parse(dateString);

        Session session = BaseDao.getSessionFactory().openSession();

        session.beginTransaction();
        EventDao dao = new EventDao(session);
        EventEntity eventEntity = dao.findByDate(date);
        eventEntity.setCausedBy(newValue.toString());
        dao.update(eventEntity);
        session.getTransaction().commit();
        session.close();

        System.out.println(eventEntity.getCausedBy() + " ");
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

    public void setEventService(EventService service) {
        this.eventService = service;
    }

    public String getCheckFlag(EventEntity eventEntity) {
        if (eventEntity.getChecked() == 1) {
            checkFlag = "checked";
        } else checkFlag = "unchecked";
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public EventEntity getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(EventEntity selectedEvent) {
        System.out.println("select event with ID:" + selectedEvent.getData());
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

