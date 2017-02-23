package hibernate.dao;

import hibernate.entities.ClazzEntity;
import hibernate.utils.DBLogger;
import org.hibernate.CacheMode;
import org.hibernate.Query;

import java.util.List;


/**
 * Created by Sergiy.K on 25-Jan-17.
 */
public class ClazzDao extends BaseDao<Integer, ClazzEntity> {

    public Boolean exists(String className) {
        Query query = getCurrentSession().createQuery("from ClazzEntity where name = :className");
        query.setCacheMode(CacheMode.REFRESH);
        query.setString("className", className);

        return (query.uniqueResult() != null);
    }

    public void persist(ClazzEntity clazz) {
        getCurrentSession().save(clazz);
    }

    public void update(ClazzEntity entity) {
        getCurrentSession().update(entity);
    }

    public ClazzEntity findById(Integer id) {
        ClazzEntity clazz = (ClazzEntity) getCurrentSession().get(ClazzEntity.class, id);
        return clazz;
    }

    public ClazzEntity findByName(String className) {
        Query query = getCurrentSession().createQuery("from ClazzEntity where name =:className");
        query.setParameter("className", className);
        ClazzEntity clazz = (ClazzEntity) query.uniqueResult();
        return clazz;
    }

    public void delete(ClazzEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ClazzEntity> findAll() {
        List<ClazzEntity> clazzes = (List<ClazzEntity>) getCurrentSession().createQuery("from ClazzEntity ").list();
        return clazzes;
    }

    public void deleteAll() {
        DBLogger.info("WARNING!!!!! Delete all Classes");
        List<ClazzEntity> entityList = findAll();
        for (ClazzEntity entity : entityList) {
            delete(entity);
        }
    }
}
