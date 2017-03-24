package db_worker.dao;

import db_worker.entities.LocaleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class LocaleDao extends BaseDao<Integer, LocaleEntity> {
    Session session;

    public LocaleDao(Session session) {
        this.session = session;
    }

    public Boolean exists(String locale) throws HibernateException {
        Query query = session.createQuery("from LocaleEntity where locale = :locale");
        query.setString("locale", locale);
        boolean result = (query.uniqueResult() != null);
        return result;
    }

    public void persist(LocaleEntity locale) throws HibernateException {
        if (!exists(locale.getLocale())){
            session.save(locale);
        }
    }

    public LocaleEntity findById(Integer id) throws HibernateException {
        LocaleEntity locale = (LocaleEntity) session.get(LocaleEntity.class, id);
        return locale;
    }

    public LocaleEntity findByLocale(String locale) throws HibernateException {
        Query query = session.createQuery("from LocaleEntity where locale =:locale");
        query.setParameter("locale", locale);
        LocaleEntity local = (LocaleEntity) query.uniqueResult();
        return local;
    }
}
