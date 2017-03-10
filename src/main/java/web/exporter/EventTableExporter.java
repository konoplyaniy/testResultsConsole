package web.exporter;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
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


    @ManagedProperty("#{eventService}")
    private EventService eventService;

    @PostConstruct
    public void init() {
        eventService = new EventService();
        events = eventService.findAll();

        System.out.println("1. events.size()events.size()  = " + events.size());
        System.out.println("initLocalesEntities");
        eventsComAuLocale = new ArrayList<>();
        eventsCoUkLocale = new ArrayList<>();
        eventsInLocale = new ArrayList<>();
        eventsCoNzLocale = new ArrayList<>();
        System.out.println("2. events.size()events.size()  = " + events.size());
        events.forEach(eventEntity -> {
            System.out.println("current locale: " + eventEntity.getLocaleByLocaleId().getLocale().toLowerCase());
            switch (eventEntity.getLocaleByLocaleId().getLocale().toLowerCase()) {
                case "com.au":
                    System.out.println("case 1");
                    eventsComAuLocale.add(eventEntity);
                    System.out.println("eventsComAuLocale.size = " + eventsComAuLocale.size());
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
            }
        });
        eventByLocale = new ArrayList<>();
        eventByLocale.add(new ListEntities("com.au", eventsComAuLocale));
        eventByLocale.add(new ListEntities("co.uk", eventsCoUkLocale));
        eventByLocale.add(new ListEntities("co.nz", eventsCoNzLocale));
        eventByLocale.add(new ListEntities("in", eventsInLocale));
    }

    public class ListEntities {
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

    public void clickApplyButton() {
        EventEntity event = new EventEntity();
        System.out.println("click apply");
        if (selectedEvents != null) {
            for (EventEntity eventEntity : selectedEvents) {
                event = eventEntity;
                if (eventEntity.getChecked() == 0) {
                    event.setChecked(1);
                } else {
                    event.setChecked(0);
                }
                eventService.update(eventEntity);
            }
        }
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Event Selected", ((EventEntity) event.getObject()).getEventId() + "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnSelect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Event Unselected", ((EventEntity) event.getObject()).getEventId() + "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
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


}

