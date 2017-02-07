package web.Views;

/**
 * Created by Sergiy.K on 06-Feb-17.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
public class CalendarView {
    private Date startDate;
    private Date endDate;
    private boolean visible = false;
    private List<EventEntity> events;

    public List<EventEntity> getEvents() {
        try {
            if (getStartDate() != null && getEndDate() != null) {
                EventService service = new EventService();
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatter.format(getStartDate()));
                Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatter.format(getEndDate()));
//                List<EventEntity> eventeventEntities = service.findBetweenDate(startDate, endDate);
                events = service.findBetweenDate(startDate, endDate);

//                for (EventEntity eventt : eventeventEntities) {
//                    System.out.println("Date: " + eventt.getData() + " id: " + eventt.getEventId());
//                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void getEventlist(ActionEvent event) {
        if (getEvents().size() > 0) {
            setVisible(true);
        }
    }

    public Date getStartDate() {
        if (startDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getDefault());
            formatter.format(startDate);
            System.out.println("Get start date " + formatter.format(startDate));
        }
        return startDate;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public Date getEndDate() {
        if (endDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getDefault());
            formatter.format(endDate);
            System.out.println("Get end date " + formatter.format(endDate));
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void onDateSelect(SelectEvent event) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        if (getStartDate() != null && getEndDate() != null) {
            setVisible(true);
        }
    }
}
