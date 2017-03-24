package db_worker.dao;


import db_worker.entities.ClazzEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ClazzDao extends BaseDao<Integer, ClazzEntity> {
    Session session;

    public ClazzDao(Session session){
        this.session = session;
    }

    public Boolean exists(String className) throws HibernateException {
        Query query = session.createQuery("from ClazzEntity where name = :className");
        query.setString("className", className);
        boolean result = (query.uniqueResult() != null);
        return result;
    }

    public void persist(ClazzEntity clazz) throws HibernateException {
        if (!exists(clazz.getName())){
            getCurrentSession().save(clazz);
        }
    }

    public ClazzEntity findByName(String className) {
        Query query = session.createQuery("from ClazzEntity where name =:className");
        query.setParameter("className", className);
        ClazzEntity clazz = (ClazzEntity) query.uniqueResult();
        return clazz;
    }
}
