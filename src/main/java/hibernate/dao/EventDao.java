package hibernate.dao;

import hibernate.entities.EventEntity;
import org.hibernate.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
public class EventDao extends BaseDao<Integer, EventEntity> {
    public void persist(EventEntity event) {
        getCurrentSession().save(event);
    }

    public void update(EventEntity entity) {
        getCurrentSession().update(entity);
    }

    public EventEntity findById(Integer id) {
        EventEntity event = (EventEntity) getCurrentSession().get(EventEntity.class, id);
        return event;
    }

    // should add method for find:
    // by test name
    // by date
    // by locale
    // by sysweb
    // by test group

//    select Date, TotalAllowance from Calculation where EmployeeId = 1
//    and Date between '2011/02/25' and '2011/02/27'

    //

    public List<EventEntity> findByDate(Date startSate, Date endDate) {
        Query query = getCurrentSession().createQuery("from EventEntity where data between :startDate and :endDate");
        query.setParameter("startDate", startSate);
        query.setParameter("endDate", endDate);
        List<EventEntity> events = (List<EventEntity>) query.list();
        return events;
    }


    public List<EventEntity> findByTestName(String testName) {
        List<EventEntity> events = getCurrentSession().createQuery("from EventEntity inner join TestEntity on EventEntity.testId = TestEntity.testId").list();
        return events;
    }

    public List<EventEntity> findByLocale(String locale) {
        Query query = getCurrentSession().createQuery("from EventEntity where localeId =:locale");
        query.setParameter("locale", locale);
        List<EventEntity> local = (List<EventEntity>) query.list();
        return local;
    }

    public void delete(EventEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<EventEntity> findAll() {
        List<EventEntity> locales = (List<EventEntity>) getCurrentSession().createQuery("from EventEntity ").list();
        return locales;
    }

    public void deleteAll() {
        List<EventEntity> entityList = findAll();
        for (EventEntity entity : entityList) {
            delete(entity);
        }
    }

}
