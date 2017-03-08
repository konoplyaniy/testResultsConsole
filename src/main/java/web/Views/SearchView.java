package web.Views;

/**
 * Created by Sergiy.K on 06-Feb-17.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class SearchView {
    private Date startDate;
    private Date endDate;
    private String testName;
    private String sysweb;
    private boolean visible = false;
    private boolean visibleTestForm = false;
    private boolean visibleSyswebForm = false;
    private List<EventEntity> events;
    private List<EventEntity> eventsTest;
    private List<EventEntity> eventSysweb;

    public List<EventEntity> getEvents() {
        try {
            if (getStartDate() != null && getEndDate() != null) {
                EventService service = new EventService();
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatter.format(getStartDate()));
                Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatter.format(getEndDate()));
                events = service.findBetweenDate(startDate, endDate);
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

    public List<EventEntity> getEventsTest() {
        System.out.println("getTestEvents");
        FacesMessage message = null;
        if (getTestName() != null && !getTestName().equals("")) {
            EventService service = new EventService();
            System.out.println("in if");
            eventsTest = new ArrayList<>();
            eventsTest = service.findByTestName(getTestName());
        } else {

        }
        if (eventsTest!= null){
            System.out.println(eventsTest.size());
        }else System.out.println("events null");
        return eventsTest;
    }

    public void setEventsTest(List<EventEntity> eventsTest) {
        this.eventsTest = eventsTest;
    }

    public List<EventEntity> getEventSysweb() {
        FacesMessage message = null;
        if (getSysweb() != null && !getSysweb().equals("")) {
            EventService service = new EventService();
            eventSysweb = service.findBySysweb(getSysweb());
        } else {
        }
        return eventSysweb;
    }

    public void setEventSysweb(List<EventEntity> eventSysweb) {
        this.eventSysweb = eventSysweb;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        System.out.println("set test name");
        this.testName = testName;
        System.out.println("test name = " + testName);
    }

    public String getSysweb() {
        return sysweb;
    }

    public void setSysweb(String sysweb) {
        System.out.println("set sysweb");
        this.sysweb = sysweb;
        System.out.println("sysweb = " + sysweb);
    }

    public boolean isVisibleTestForm() {
        return visibleTestForm;
    }

    public void setVisibleTestForm(boolean visibleTestForm) {
        this.visibleTestForm = visibleTestForm;
    }

    public boolean isVisibleSyswebForm() {
        return visibleSyswebForm;
    }

    public void setVisibleSyswebForm(boolean visibleSyswebForm) {
        this.visibleSyswebForm = visibleSyswebForm;
    }

    public void searchTest() {
        System.out.println("click search test");
        if (getTestName() != null && !getTestName().equals("")) {
            setVisibleTestForm(true);
        }
    }

    public void searchSysweb() {
        System.out.println("click search sysweb");
        if (getSysweb() != null && !getSysweb().equals(""))
            setVisibleSyswebForm(true);
    }

    public void click() {
        if (getStartDate() != null && getEndDate() != null) {
            setVisible(true);
        }
    }
}
