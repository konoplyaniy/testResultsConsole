package hibernate.dao;

import hibernate.entities.SyswebEntity;
import hibernate.utils.DBLogger;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
public class SyswebDao extends BaseDao<Integer, SyswebEntity> {
    public SyswebDao() {
    }

    public Boolean exists(String sysweb) {
        Query query = getCurrentSession().createQuery("from SyswebEntity where name = :sysweb");
        query.setString("sysweb", sysweb);
        return (query.uniqueResult() != null);
    }

    public void persist(SyswebEntity sysweb) {
            getCurrentSession().save(sysweb);
    }

    public void update(SyswebEntity entity) {
        getCurrentSession().update(entity);
    }

    public SyswebEntity findById(Integer id) {
        SyswebEntity sysweb = (SyswebEntity) getCurrentSession().get(SyswebEntity.class, id);
        return sysweb;
    }

    public SyswebEntity findByName(String syswebUrl) {
        Query query = getCurrentSession().createQuery("from SyswebEntity where name =:syswebUrl");
        query.setParameter("syswebUrl", syswebUrl);
        SyswebEntity sysweb = (SyswebEntity) query.uniqueResult();
        return sysweb;
    }

    public void delete(SyswebEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<SyswebEntity> findAll() {
        List<SyswebEntity> syswebs = (List<SyswebEntity>) getCurrentSession().createQuery("from SyswebEntity ").list();
        return syswebs;
    }

    public void deleteAll() {
        DBLogger.info("WARNING!!! Delete all syswebs");
        List<SyswebEntity> entityList = findAll();
        for (SyswebEntity entity : entityList) {
            delete(entity);
        }
    }
}
