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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
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

    @PostConstruct
    public void init() {
        eventService = new EventService();
        events = eventService.findByCurrentDayEvents();
        /*events = eventService.findByCurrentMonthEvents();*/

        /*System.out.println("1. events.size()events.size()  = " + events.size());
        System.out.println("initLocalesEntities");*/
        eventsComAuLocale = new ArrayList<>();
        eventsCoUkLocale = new ArrayList<>();
        eventsInLocale = new ArrayList<>();
        eventsCoNzLocale = new ArrayList<>();
        /*System.out.println("2. events.size()events.size()  = " + events.size());*/
        events.forEach(eventEntity -> {
            /*System.out.println("current locale: " + eventEntity.getLocaleByLocaleId().getLocale().toLowerCase());*/
            switch (eventEntity.getLocaleByLocaleId().getLocale().toLowerCase()) {
                case "com.au":
                    /*System.out.println("case 1");*/
                    eventsComAuLocale.add(eventEntity);
                    /*System.out.println("eventsComAuLocale.size = " + eventsComAuLocale.size());*/
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

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public ArrayList<EventEntity> getEvents() {
            return events;
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
                System.out.println(entity.getChecked());
                entity.setChecked(setStatus);
                dao.update(entity);
                System.out.println(entity.getChecked());
                session.getTransaction().commit();
                session.close();
    }

    public void clickApplyButton() {
        EventEntity event;
//        System.out.println("click apply");
        if (selectedEvents != null) {

//            System.out.println("selected events: " + selectedEvents.size());
            for (EventEntity eventEntity : selectedEvents) {
                event = eventEntity;
//                System.out.println("before update: " + event.getChecked());
                if (eventEntity.getChecked() == 0) {
                    changeEventStatus(event.getEventId(), 1);
                } else {
                    changeEventStatus(event.getEventId(), 1);
                }
            }
        }
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

