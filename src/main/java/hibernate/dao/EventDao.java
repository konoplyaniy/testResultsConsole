package hibernate.dao;

import hibernate.entities.EventEntity;
import hibernate.service.BrowserService;
import org.hibernate.CacheMode;
import org.hibernate.Query;

import java.util.Date;
import java.util.List;

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

    public List<EventEntity> findByTestName(String testName) {
        Query query = getCurrentSession().createQuery("from EventEntity where testByTestId.name =:testName ");
        query.setParameter("testName", testName);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findByGroupName(String groupName) {
        Query query = getCurrentSession().createQuery("from EventEntity where testByTestId.groupByGroupId.name =:groupName");
        query.setParameter("groupName", groupName);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findByClassName(String className) {
        Query query = getCurrentSession().createQuery("from EventEntity where testByTestId.clazzByClassId.name =:className ");
        query.setParameter("className", className);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findBetweenDates(Date startSate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity where data between :startDate and :endDate");
        query.setParameter("startDate", startSate);
        query.setParameter("endDate", endDate);
        List<EventEntity> events = (List<EventEntity>) query.list();
        return events;
    }

    public List<EventEntity> findByLocale(String localeName) {
        Query query = getCurrentSession().createQuery("from EventEntity where localeByLocaleId.locale =:localeName ");
        query.setParameter("localeName", localeName);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findBySysweb(String sysweb) {
        Query query = getCurrentSession().createQuery("from EventEntity where syswebBySyswebId.name =:sysweb");
        query.setParameter("sysweb", sysweb);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findByPcName(String pcName) {
        Query query = getCurrentSession().createQuery("from EventEntity where pcByPcId.name =:pcName");
        query.setParameter("pcName", pcName);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findByPcOS(String pcOs) {
        Query query = getCurrentSession().createQuery("from EventEntity where pcByPcId.os =:pcOs");
        query.setParameter("pcOs", pcOs);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findByBrowser(String browser) {
        Query query = getCurrentSession().createQuery("from EventEntity where browserByBrowserId.browser =:browser");
        query.setParameter("browser", browser);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findOnlyChecked() {
        int checkStatus = 1;
        Query query = getCurrentSession().createQuery("from EventEntity where checked =:checkStatus");
        query.setParameter("checkStatus", checkStatus);
        return (List<EventEntity>) query.list();
    }

    public List<EventEntity> findOnlyUnChecked() {
        int checkStatus = 0;
        Query query = getCurrentSession().createQuery("from EventEntity where checked =:checkStatus");
        query.setParameter("checkStatus", checkStatus);
        return (List<EventEntity>) query.list();
    }

    @Override
    public void delete(EventEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<EventEntity> findAll() {
        List<EventEntity> events = (List<EventEntity>) getCurrentSession().createQuery("from EventEntity ").list();
        return events;
    }

    @Override
    public void deleteAll() {

    }

}
