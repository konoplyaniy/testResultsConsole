package hibernate.dao;

import hibernate.entities.EventEntity;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
public class EventDao extends BaseDao<Integer, EventEntity> {
    @Override
    public void persist(EventEntity entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(EventEntity entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public EventEntity findById(Integer key) {
        EventEntity event = (EventEntity) getCurrentSession().get(EventEntity.class, key);
        return event;
    }

    public ArrayList<EventEntity> findByTestName(String testName) {
        Query query = getCurrentSession().createQuery("from EventEntity where testByTestId.name =:testName ");
        query.setParameter("testName", testName);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByGroupName(String groupName) {
        Query query = getCurrentSession().createQuery("from EventEntity where testByTestId.groupByGroupId.name =:groupName");
        query.setParameter("groupName", groupName);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByClassName(String className) {
        Query query = getCurrentSession().createQuery("from EventEntity where testByTestId.clazzByClassId.name =:className ");
        query.setParameter("className", className);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findBetweenDates(Date startSate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity where data between :startDate and :endDate");
        query.setParameter("startDate", startSate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }


    public ArrayList<EventEntity> findByTestNameBetweenDates(String testName, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where testByTestId.name =:testName and data between :startDate and :endDate");
        query.setCacheMode(CacheMode.IGNORE);
        query.setParameter("testName", testName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByMonthEvents(Date date) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where MONTH(data) = MONTH(:date) ");
        query.setParameter("date", date);
        query.setCacheMode(CacheMode.IGNORE);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByDayEvents(Date date) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where DAY(data) = DAY(:date) ");
        query.setParameter("date", date);
        query.setCacheMode(CacheMode.IGNORE);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findBySyswebBetweenDates(String sysweb, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where syswebBySyswebId.name =:sysweb and data between :startDate and :endDate");
        query.setCacheMode(CacheMode.IGNORE);
        query.setParameter("sysweb", sysweb);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByClassNameBetweenDates(String clazzName, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where testByTestId.clazzByClassId.name =:clazzName and data between :startDate and :endDate");
        query.setCacheMode(CacheMode.IGNORE);
        query.setParameter("clazzName", clazzName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByClassNameTestNameBetweenDates(String clazzName, String testName, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where testByTestId.clazzByClassId.name =:clazzName " +
                "and testByTestId.name =:testName " +
                "and data between :startDate and :endDate");
        query.setCacheMode(CacheMode.IGNORE);
        query.setParameter("clazzName", clazzName);
        query.setParameter("testName", testName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findBySelectedDay(String clazzName, String testName, String sysweb, String locale, Date date){
        String sqlQuery = "from EventEntity " +
                "where DAY(data) = DAY(:date) ";
        /*where DAY(data) = DAY(:date) ")*/
        if (!clazzName.equals("")){
            sqlQuery = sqlQuery + "and testByTestId.clazzByClassId.name =:clazzName ";
        }
        if (!testName.equals("")){
            sqlQuery = sqlQuery + "and testByTestId.name =:testName ";
        }
        if (!sysweb.equals("")){
            sqlQuery = sqlQuery + "and syswebBySyswebId.name =:sysweb ";
        }
        if (!locale.equals("")){
            sqlQuery = sqlQuery + "and localeByLocaleId.locale =:locale ";
        }

        Query query = getCurrentSession().createQuery(sqlQuery);
        query.setCacheMode(CacheMode.IGNORE);
        if (!clazzName.equals("")){
            query.setParameter("clazzName", clazzName);
        }
        if (!testName.equals("")){
            query.setParameter("testName", testName);
        }
        if (!sysweb.equals("")){
            query.setParameter("sysweb", sysweb);
        }
        if (!locale.equals("")){
            query.setParameter("locale", locale);
        }
        query.setParameter("date", date);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findBySelected(String clazzName, String testName, String sysweb, String locale, Date startDate, Date endDate){
        String sqlQuery = "from EventEntity " +
                "where data between :startDate and :endDate ";
        if (!clazzName.equals("")){
            sqlQuery = sqlQuery + "and testByTestId.clazzByClassId.name =:clazzName ";
        }
        if (!testName.equals("")){
            sqlQuery = sqlQuery + "and testByTestId.name =:testName ";
        }
        if (!sysweb.equals("")){
            sqlQuery = sqlQuery + "and syswebBySyswebId.name =:sysweb ";
        }
        if (!locale.equals("")){
            sqlQuery = sqlQuery + "and localeByLocaleId.locale =:locale ";
        }

        Query query = getCurrentSession().createQuery(sqlQuery);
        query.setCacheMode(CacheMode.IGNORE);
        if (!clazzName.equals("")){
            query.setParameter("clazzName", clazzName);
        }
        if (!testName.equals("")){
            query.setParameter("testName", testName);
        }
        if (!sysweb.equals("")){
            query.setParameter("sysweb", sysweb);
        }
        if (!locale.equals("")){
            query.setParameter("locale", locale);
        }
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByClassNameTestNameSyswebBetweenDates(String clazzName, String testName, String sysweb, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where testByTestId.clazzByClassId.name =:clazzName " +
                "and testByTestId.name =:testName " +
                "and syswebBySyswebId.name =:sysweb " +
                "and data between :startDate and :endDate");
        query.setCacheMode(CacheMode.IGNORE);
        query.setParameter("clazzName", clazzName);
        query.setParameter("testName", testName);
        query.setParameter("sysweb", sysweb);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByClassNameTestNameSyswebLocaleBetweenDates(String clazzName, String testName, String sysweb,
                                                                             String locale, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where testByTestId.clazzByClassId.name =:clazzName " +
                "and testByTestId.name =:testName " +
                "and syswebBySyswebId.name =:sysweb " +
                "and localeByLocaleId.locale =:locale " +
                "and data between :startDate and :endDate");
        query.setCacheMode(CacheMode.IGNORE);
        query.setParameter("clazzName", clazzName);
        query.setParameter("testName", testName);
        query.setParameter("sysweb", sysweb);
        query.setParameter("locale", locale);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }


    public ArrayList<EventEntity> findBySyswebTestNameBetweenDates(String sysweb, String testName, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity " +
                "where syswebBySyswebId.name =:sysweb " +
                "and testByTestId.name =:testName " +
                "and data between :startDate and :endDate");
        query.setCacheMode(CacheMode.IGNORE);
        query.setParameter("sysweb", sysweb);
        query.setParameter("testName", testName);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findBySyswebTestNameLocaleBetweenDates(String sysweb, String testName, String locale, Date startDate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity" +
                " where syswebBySyswebId.name =:sysweb " +
                "and testByTestId.name =:testName  " +
                "and localeByLocaleId.locale =:locale " +
                "and data between :startDate and :endDate");
        query.setCacheMode(CacheMode.IGNORE);
        query.setParameter("testName", testName);
        query.setParameter("locale", locale);
        query.setParameter("sysweb", sysweb);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByLocale(String localeName) {
        Query query = getCurrentSession().createQuery("from EventEntity where localeByLocaleId.locale =:localeName ");
        query.setParameter("localeName", localeName);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findBySysweb(String sysweb) {
        Query query = getCurrentSession().createQuery("from EventEntity where syswebBySyswebId.name =:sysweb");
        query.setParameter("sysweb", sysweb);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByPcName(String pcName) {
        Query query = getCurrentSession().createQuery("from EventEntity where pcByPcId.name =:pcName");
        query.setParameter("pcName", pcName);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByPcOS(String pcOs) {
        Query query = getCurrentSession().createQuery("from EventEntity where pcByPcId.os =:pcOs");
        query.setParameter("pcOs", pcOs);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findByBrowser(String browser) {
        Query query = getCurrentSession().createQuery("from EventEntity where browserByBrowserId.browser =:browser");
        query.setParameter("browser", browser);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findOnlyChecked() {
        int checkStatus = 1;
        Query query = getCurrentSession().createQuery("from EventEntity where checked =:checkStatus");
        query.setParameter("checkStatus", checkStatus);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    public ArrayList<EventEntity> findOnlyUnChecked() {
        int checkStatus = 0;
        Query query = getCurrentSession().createQuery("from EventEntity where checked =:checkStatus");
        query.setParameter("checkStatus", checkStatus);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> results = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return results;
    }

    @Override
    public void delete(EventEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<EventEntity> findAll() {
        Query query = getCurrentSession().createQuery("from EventEntity ");
        query.setCacheMode(CacheMode.IGNORE);
        Transaction transaction = getCurrentSession().beginTransaction();
        ArrayList<EventEntity> events = (ArrayList<EventEntity>) query.list();
        transaction.commit();
        return events;
    }

    @Override
    public void deleteAll() {

    }

}
