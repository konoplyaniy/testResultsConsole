package db_worker.dao;


import db_worker.entities.BrowserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class BrowserDao extends BaseDao<Integer, BrowserEntity> {
    Session session;

    public BrowserDao(Session session) {
        this.session = session;
    }

    public Boolean exists(String browser) throws HibernateException {
        Query query = session.createQuery("from BrowserEntity where browser = :browser");
        query.setString("browser", browser);
        boolean result = (query.uniqueResult() != null);
        return result;
    }

    public void persist(BrowserEntity entity) throws HibernateException {
        if (!exists(entity.getBrowser())){
            getCurrentSession().save(entity);
        }
    }

    public BrowserEntity findByName(String browser) throws HibernateException {
        Query query = session.createQuery("from BrowserEntity where browser =:browser");
        query.setParameter("browser", browser);
        BrowserEntity brows = (BrowserEntity) query.uniqueResult();
        return brows;
    }
}
