package hibernate.service;

import hibernate.dao.EventDao;
import hibernate.entities.EventEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
@ManagedBean(name = "eventService")
@ApplicationScoped
public class EventService {
    private static EventDao eventDao;

    public EventService(){
        eventDao = new EventDao();
    }

    public void persist(EventEntity entity) {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.persist(entity);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public void update(EventEntity entity) {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.update(entity);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public EventEntity findById(Integer id) {
        eventDao.openCurrentSession();
        EventEntity event = eventDao.findById(id);
        eventDao.closeCurrentSession();
        return event;
    }

    public List<EventEntity> findByTestName(String name) {
        eventDao.openCurrentSession();
        List<EventEntity> event = eventDao.findByTestName(name);
        eventDao.closeCurrentSession();
        return event;
    }

    public void delete(Integer id) {
        eventDao.openCurrentSessionwithTransaction();
        EventEntity event = eventDao.findById(id);
        eventDao.delete(event);
        eventDao.closeCurrentSessionwithTransaction();
    }

    public List<EventEntity> findAll() {
        eventDao.openCurrentSession();
        List<EventEntity> entities = eventDao.findAll();
        eventDao.closeCurrentSession();
        return entities;
    }

    public int getRowsCount(){
        eventDao.openCurrentSession();
        List<EventEntity> entities = eventDao.findAll();
        eventDao.closeCurrentSession();
        return entities.size();
    }

    public void deleteAll() {
        eventDao.openCurrentSessionwithTransaction();
        eventDao.deleteAll();
        eventDao.closeCurrentSessionwithTransaction();
    }

    public EventDao eventDao() {
        return eventDao;
    }

}
