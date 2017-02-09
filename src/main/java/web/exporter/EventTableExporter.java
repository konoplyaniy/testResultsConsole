package web.exporter;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;
import oracle.jrockit.jfr.settings.EventSetting;
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
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
@SessionScoped
public class EventTableExporter implements Serializable{
    private List<EventEntity> events;
    private String checkFlag;
    private EventEntity selectedEvent;
    private List<EventEntity> selectedEvents;


    @ManagedProperty("#{eventService}")
    private EventService eventService;

    @PostConstruct
    public void init() {
        eventService = new EventService();
        events = eventService.findAll();
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

    public void clickApplyButton(){
        EventEntity event = new EventEntity();
        EventService service = new EventService();
        System.out.println("click apply");
        if (selectedEvents != null){
            System.out.println("selected events count " + selectedEvents.size());

            for (EventEntity eventEntity : selectedEvents) {
                event = eventEntity;
                event.setChecked(1);
                eventService.update(eventEntity);
            }
        }
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Event Selected", ((EventEntity) event.getObject()).getEventId() +"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Event Unselected", ((EventEntity) event.getObject()).getEventId() +"");
        FacesContext.getCurrentInstance().addMessage(null, msg);
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


}

