package web.Views;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by geser on 05.02.17.
 */
@ManagedBean
public class MainTable {
    private List<EventEntity> events;
    private int count;

    // hard code for testing
    public int getCount() {
        if (events != null)
            count = events.size();
        else count = 0;
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // hard code for testing
    public String getSiteURL() {
        siteURL = "http://crazydomains.com.au";
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    private String siteURL;

    @ManagedProperty("#{eventService}")
    private EventService eventService;

    @PostConstruct
    public void init() {
        eventService = new EventService();
        events = eventService.findAll();
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEventService(EventService service) {
        this.eventService = service;
    }
}
