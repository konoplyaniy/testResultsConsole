package hibernate.service;

import hibernate.dao.EventDao;
import hibernate.entities.EventEntity;

import javax.faces.bean.ApplicationScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
@ApplicationScoped
public class EventService {
    private EventDao eventDao;

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

    public ArrayList<EventEntity> findByTestName(String testName) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> result = eventDao.findByTestName(testName);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByGroupName(String groupName) {
        ArrayList<EventEntity> result;
        eventDao.openCurrentSession();
        result = eventDao.findByGroupName(groupName);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByClassName(String className) {
        ArrayList<EventEntity> result;
        eventDao.openCurrentSession();
        result = eventDao.findByClassName(className);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByBrowser(String browser) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> result = eventDao.findByBrowser(browser);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByPcOS(String pcOs) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> result = eventDao.findByPcOS(pcOs);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByPcName(String pcName) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> result = eventDao.findByPcName(pcName);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findBySysweb(String sysweb) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> result = eventDao.findBySysweb(sysweb);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByLocale(String locale) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> result = eventDao.findByLocale(locale);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findBetweenDate(Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> result = eventDao.findBetweenDates(startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByCurrentMonthEvents() {
        eventDao.openCurrentSession();
        Date date = new Date();
        ArrayList<EventEntity> result = eventDao.findByMonthEvents(date);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByMonthEvents(Date date) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByMonthEvents(date);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByDayEvents(Date date) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> result = eventDao.findByDayEvents(date);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findByCurrentDayEvents() {
        eventDao.openCurrentSession();
        Date date = new Date();
        ArrayList<EventEntity> result = eventDao.findByDayEvents(date);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findBySelected(String clazzName, String testName, String sysweb, String locale, Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> results = eventDao.findBySelected(clazzName, testName, sysweb, locale, startDate, endDate);
        eventDao.closeCurrentSession();
        return results;
    }

    public ArrayList<EventEntity> findBySelectedDay(String clazzName, String testName, String sysweb, String locale, Date date) {
        eventDao.openCurrentSession();
        ArrayList<EventEntity> results = eventDao.findBySelectedDay(clazzName, testName, sysweb, locale, date);
        eventDao.closeCurrentSession();
        return results;
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

    public EventDao eventDao() {
        return eventDao;
    }
}
