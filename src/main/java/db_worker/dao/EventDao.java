package db_worker.dao;


import db_worker.entities.EventEntity;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.Date;

public class EventDao {
    Session session;

    public EventDao() {
    }

    public EventDao(Session session) {
        this.session = session;
    }

    public void persist(EventEntity entity) {
        session.saveOrUpdate(entity);
    }

    public void update(EventEntity entity) throws HibernateException {
        session.update(entity);
    }

    public EventEntity findById(Integer key) {
        EventEntity event = (EventEntity) session.get(EventEntity.class, key);
        return event;
    }

    public EventEntity findByDate(Date date) {
        Query query = session.createQuery("from EventEntity where data =:date ");
        query.setParameter("date", date);
        return (EventEntity) query.uniqueResult();
    }

    public ArrayList<EventEntity> findByTestName(String testName) throws HibernateException {
        Query query = session.createQuery("from EventEntity where testByTestId.name =:testName ");
        query.setParameter("testName", testName);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findByGroupName(String groupName) throws HibernateException {
        Query query = session.createQuery("from EventEntity where testByTestId.groupByGroupId.name =:groupName");
        query.setParameter("groupName", groupName);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findByClassName(String className) throws HibernateException {
        Query query = session.createQuery("from EventEntity where testByTestId.clazzByClassId.name =:className ");
        query.setParameter("className", className);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findBetweenDates(Date startSate, Date endDate) throws HibernateException {
        Query query = session.createQuery("from EventEntity where data between :startDate and :endDate");
        query.setParameter("startDate", startSate);
        query.setParameter("endDate", endDate);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findByTestNameBetweenDates(String testName, Date startDate, Date endDate) throws HibernateException {
        Query query = session.createQuery("from EventEntity " +
                "where testByTestId.name =:testName and data between :startDate and :endDate");
        query.setParameter("testName", testName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findByMonthEvents(Date date) throws HibernateException {
        Query query = session.createQuery("from EventEntity " +
                "where MONTH(data) = MONTH(:date) ");
        query.setParameter("date", date);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findByDayEvents(Date date) throws HibernateException {
        Query query = session.createQuery("from EventEntity " +
                "where DAY(data) = DAY(:date) ");
        query.setParameter("date", date);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findBySyswebBetweenDates(String sysweb, Date startDate, Date endDate) throws HibernateException {
        Query query = session.createQuery("from EventEntity " +
                "where syswebBySyswebId.name =:sysweb and data between :startDate and :endDate");
        query.setParameter("sysweb", sysweb);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findBySelectedDay(String clazzName, String testName, String sysweb, String locale, Date date) throws HibernateException {
        String sqlQuery = "from EventEntity " +
                "where DAY(data) = DAY(:date) ";
        if (!clazzName.equals("")) {
            sqlQuery = sqlQuery + "and testByTestId.clazzByClassId.name =:clazzName ";
        }
        if (!testName.equals("")) {
            sqlQuery = sqlQuery + "and testByTestId.name =:testName ";
        }
        if (!sysweb.equals("")) {
            sqlQuery = sqlQuery + "and syswebBySyswebId.name =:sysweb ";
        }
        if (!locale.equals("")) {
            sqlQuery = sqlQuery + "and localeByLocaleId.locale =:locale ";
        }

        Query query = session.createQuery(sqlQuery);
        if (!clazzName.equals("")) {
            query.setParameter("clazzName", clazzName);
        }
        if (!testName.equals("")) {
            query.setParameter("testName", testName);
        }
        if (!sysweb.equals("")) {
            query.setParameter("sysweb", sysweb);
        }
        if (!locale.equals("")) {
            query.setParameter("locale", locale);
        }
        query.setParameter("date", date);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findBySelected(
            String clazzName, String testName, String sysweb, String locale, Date startDate, Date endDate)
            throws HibernateException {

        String sqlQuery = "from EventEntity " +
                "where data between :startDate and :endDate ";
        if (!clazzName.equals("")) {
            sqlQuery = sqlQuery + "and testByTestId.clazzByClassId.name =:clazzName ";
        }
        if (!testName.equals("")) {
            sqlQuery = sqlQuery + "and testByTestId.name =:testName ";
        }
        if (!sysweb.equals("")) {
            sqlQuery = sqlQuery + "and syswebBySyswebId.name =:sysweb ";
        }
        if (!locale.equals("")) {
            sqlQuery = sqlQuery + "and localeByLocaleId.locale =:locale ";
        }

        Query query = session.createQuery(sqlQuery);
        if (!clazzName.equals("")) {
            query.setParameter("clazzName", clazzName);
        }
        if (!testName.equals("")) {
            query.setParameter("testName", testName);
        }
        if (!sysweb.equals("")) {
            query.setParameter("sysweb", sysweb);
        }
        if (!locale.equals("")) {
            query.setParameter("locale", locale);
        }
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findByLocale(String localeName) throws HibernateException {
        Query query = session.createQuery("from EventEntity where localeByLocaleId.locale =:localeName ");
        query.setParameter("localeName", localeName);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findBySysweb(String sysweb) throws HibernateException {
        Query query = session.createQuery("from EventEntity where syswebBySyswebId.name =:sysweb");
        query.setParameter("sysweb", sysweb);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findByPcName(String pcName) throws HibernateException {
        Query query = session.createQuery("from EventEntity where pcByPcId.name =:pcName");
        query.setParameter("pcName", pcName);
        Transaction transaction = session.beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByPcOS(String pcOs) throws HibernateException {
        Query query = session.createQuery("from EventEntity where pcByPcId.os =:pcOs");
        query.setParameter("pcOs", pcOs);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findByBrowser(String browser) throws HibernateException {
        Query query = session.createQuery("from EventEntity where browserByBrowserId.browser =:browser");
        query.setParameter("browser", browser);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findOnlyChecked() throws HibernateException {
        int checkStatus = 1;
        Query query = session.createQuery("from EventEntity where checked =:checkStatus");
        query.setParameter("checkStatus", checkStatus);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    public ArrayList<EventEntity> findOnlyUnChecked() throws HibernateException {
        int checkStatus = 0;
        Query query = session.createQuery("from EventEntity where checked =:checkStatus");
        query.setParameter("checkStatus", checkStatus);
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        return results;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<EventEntity> findAll() throws HibernateException {
        Query query = session.createQuery("from EventEntity ");
        query.setCacheMode(CacheMode.IGNORE);
        ArrayList<EventEntity> events = (ArrayList<EventEntity>) query.list();
        return events;
    }

}
