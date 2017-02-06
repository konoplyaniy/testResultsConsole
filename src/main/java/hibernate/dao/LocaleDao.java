package hibernate.dao;

import hibernate.entities.LocaleEntity;
import hibernate.utils.DBLogger;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
public class LocaleDao extends BaseDao<Integer, LocaleEntity> {

    public Boolean exists(String locale) {
        Query query = getCurrentSession().createQuery("from LocaleEntity where locale = :locale");
        query.setString("locale", locale);
        return (query.uniqueResult() != null);
    }

    public void persist(LocaleEntity locale) {
        getCurrentSession().save(locale);
    }

    public void update(LocaleEntity entity) {
        getCurrentSession().update(entity);
    }

    public LocaleEntity findById(Integer id) {
        LocaleEntity locale = (LocaleEntity) getCurrentSession().get(LocaleEntity.class, id);
        return locale;
    }

    public LocaleEntity findByLocale(String locale) {
        Query query = getCurrentSession().createQuery("from LocaleEntity where locale =:locale");
        query.setParameter("locale", locale);
        LocaleEntity local = (LocaleEntity) query.uniqueResult();
        return local;
    }

    public void delete(LocaleEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<LocaleEntity> findAll() {
        List<LocaleEntity> locales = (List<LocaleEntity>) getCurrentSession().createQuery("from LocaleEntity ").list();
        return locales;
    }

    public void deleteAll() {
        DBLogger.info("WARNING!!! Get all locales");
        List<LocaleEntity> entityList = findAll();
        for (LocaleEntity entity : entityList) {
            delete(entity);
        }
    }

}
