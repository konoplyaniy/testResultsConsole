package hibernate.service;

import hibernate.dao.EventDao;
import hibernate.entities.EventEntity;
import hibernate.utils.DBLogger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
@ManagedBean(name = "eventService")
@ApplicationScoped
public class EventService {
    private static EventDao eventDao;

    public EventService() {
        eventDao = new EventDao();
    }

    public void persist(EventEntity entity) {
        System.out.println("try to add event");
        eventDao.openCurrentSessionwithTransaction();
        eventDao.persist(entity);
        eventDao.closeCurrentSessionwithTransaction();
        System.out.println("success");
    }

    public void update(EventEntity eventEntity) {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.update(eventEntity);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public EventEntity findbyId(int id) {
        eventDao.openCurrentSession();
        EventEntity result = eventDao.findById(id);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByTestName(String testName) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByTestName(testName);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByGroupName(String groupName) {
        List<EventEntity> result;
        eventDao.openCurrentSession();
        result = eventDao.findByGroupName(groupName);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByClassName(String className) {
        List<EventEntity> result;
        eventDao.openCurrentSession();
        result = eventDao.findByClassName(className);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByCurrentDate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date startDate = calendar.getTime();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        Date endDate = calendar.getTime();
        endDate.setHours(23);
        endDate.setMinutes(59);
        endDate.setSeconds(59);
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findBetweenDates(startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByBrowser(String browser) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByBrowser(browser);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByPcOS(String pcOs) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByPcOS(pcOs);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByPcName(String pcName) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByPcName(pcName);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findBySysweb(String sysweb) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findBySysweb(sysweb);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByLocale(String locale) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByLocale(locale);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findBetweenDate(Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findBetweenDates(startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findAllChecked() {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findOnlyChecked();
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findAllUnChecked() {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findOnlyUnChecked();
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findAll() {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findAll();
        eventDao.closeCurrentSession();
        return result;
    }

}
