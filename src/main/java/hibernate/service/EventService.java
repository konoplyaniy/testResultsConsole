package hibernate.service;

import hibernate.dao.EventDao;
import hibernate.entities.EventEntity;

import javax.faces.bean.ApplicationScoped;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    public List<EventEntity> findCurrentMonthEvents() {
        eventDao.openCurrentSession();
        Date date = new Date();
        List<EventEntity> result = eventDao.findByMonthEvents(date);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByMonthEvents(Date date) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByMonthEvents(date);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByDayEvents(Date date) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByDayEvents(date);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByCurrentDayEvents() {
        eventDao.openCurrentSession();
        Date date = new Date();
        List<EventEntity> result = eventDao.findByDayEvents(date);
        eventDao.closeCurrentSession();
        return result;
    }

    /*findByDayEvents*/

    public List<EventEntity> findByTestNameBetweenDates(String testName, Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findByTestNameBetweenDates(testName, startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public ArrayList<EventEntity> findBySelected(String clazzName, String testName, String sysweb, String locale, Date startDate, Date endDate){
        eventDao.openCurrentSession();
        ArrayList<EventEntity> results = eventDao.findBySelected(clazzName, testName, sysweb, locale, startDate, endDate);
        eventDao.closeCurrentSession();
        return results;
    }

    public ArrayList<EventEntity> findBySelectedDay(String clazzName, String testName, String sysweb, String locale, Date date){
        eventDao.openCurrentSession();
        ArrayList<EventEntity> results = eventDao.findBySelectedDay(clazzName, testName, sysweb, locale, date);
        eventDao.closeCurrentSession();
        return results;
    }

    public List<EventEntity> findBySyswebBetweenDates(String sysweb, Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findBySyswebBetweenDates(sysweb, startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findBySyswebTestNameBetweenDates(String sysweb, String testName, Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findBySyswebTestNameBetweenDates(sysweb, testName, startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findBySyswebTestNameLocaleBetweenDates(String sysweb, String testName, String locale, Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao.findBySyswebTestNameLocaleBetweenDates(sysweb, testName, locale, startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByClassNameBetweenDates(String clazzName, Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao
                .findByClassNameBetweenDates(clazzName, startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByClassNameTestNameBetweenDates(String clazzName, String testName,
                                                                 Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao
                .findByClassNameTestNameBetweenDates(clazzName, testName, startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }

    public List<EventEntity> findByClassNameTestNameSyswebBetweenDates(String clazzName, String testName, String sysweb,
                                                                       Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao
                .findByClassNameTestNameSyswebBetweenDates(clazzName, testName, sysweb, startDate, endDate);
        eventDao.closeCurrentSession();
        return result;
    }


    public List<EventEntity> findByClassNameTestNameSyswebLocaleBetweenDates(String clazzName, String testName, String sysweb,
                                                                             String locale, Date startDate, Date endDate) {
        eventDao.openCurrentSession();
        List<EventEntity> result = eventDao
                .findByClassNameTestNameSyswebLocaleBetweenDates(clazzName, testName, sysweb, locale, startDate, endDate);
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

    public EventDao eventDao() {
        return eventDao;
    }
}
