package web.exporter;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class EventTableExporter {
    private List<EventEntity> events;

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

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEventService(EventService service) {
        this.eventService = service;
    }
}

