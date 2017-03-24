package db_worker.dao;

import db_worker.entities.SyswebEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;

public class SyswebDao extends BaseDao<Integer, SyswebEntity> implements Serializable {
    Session session;

    public SyswebDao(Session session) {
        this.session = session;
    }

    public Boolean exists(String sysweb) throws HibernateException {
        Query query = session.createQuery("from SyswebEntity where name = :sysweb");
        query.setString("sysweb", sysweb);
        boolean result = (query.uniqueResult() != null);
        return result;
    }

    public void persist(SyswebEntity sysweb) throws HibernateException {
        if (!exists(sysweb.getName())){
            session.save(sysweb);
        }
    }

    public void update(SyswebEntity entity) throws HibernateException {
        session.update(entity);
    }

    public SyswebEntity findById(Integer id) throws HibernateException {
        SyswebEntity sysweb = (SyswebEntity) session.get(SyswebEntity.class, id);
        return sysweb;
    }

    public SyswebEntity findByName(String syswebUrl) throws HibernateException {
        Query query = session.createQuery("from SyswebEntity where name =:syswebUrl");
        query.setParameter("syswebUrl", syswebUrl);
        SyswebEntity sysweb = (SyswebEntity) query.uniqueResult();
        return sysweb;
    }

}
