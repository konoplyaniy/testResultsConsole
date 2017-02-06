package hibernate.dao;

import hibernate.entities.BrowserEntity;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Sergiy.K on 31-Jan-17.
 */
public class BrowserDao extends BaseDao<Integer,BrowserEntity> {

    public Boolean exists(String browser) {
        Query query = getCurrentSession().createQuery("from BrowserEntity where browser = :browser");
        query.setString("browser", browser);
        return (query.uniqueResult() != null);
    }

    @Override
    public void persist(BrowserEntity entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(BrowserEntity entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public BrowserEntity findById(Integer key) {
        Query query = getCurrentSession().createQuery("from BrowserEntity where browserId =: key");
        query.setParameter("key", key);
        BrowserEntity browser = (BrowserEntity) query.uniqueResult();
        return browser;
    }

    public BrowserEntity findByName(String browser) {
        Query query = getCurrentSession().createQuery("from BrowserEntity where browser =:browser");
        query.setParameter("browser", browser);
        BrowserEntity brows = (BrowserEntity) query.uniqueResult();
        return brows;
    }

    @Override
    public void delete(BrowserEntity entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<BrowserEntity> findAll() {
        List<BrowserEntity> browsers = (List<BrowserEntity>) getCurrentSession().createQuery("from BrowserEntity ").list();
        return browsers;
    }

    @Override
    public void deleteAll() {

    }
}
