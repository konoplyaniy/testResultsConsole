package hibernate.dao;

import hibernate.entities.PcEntity;
import hibernate.utils.DBLogger;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
public class PcDao extends BaseDao<Integer, PcEntity> {

    public PcDao() {
    }

    public Boolean exists(String pcName) {
        Query query = getCurrentSession().createQuery("from PcEntity where name = :pcName");
        query.setString("pcName", pcName);
        return (query.uniqueResult() != null);
    }


    public void persist(PcEntity pcDetails) {
            getCurrentSession().save(pcDetails);
    }

    public void update(PcEntity entity) {
        getCurrentSession().update(entity);
    }

    public PcEntity findById(Integer id) {
        PcEntity pc = (PcEntity) getCurrentSession().get(PcEntity.class, id);
        return pc;
    }

    public PcEntity findByPcName(String pcName) {
        Query query = getCurrentSession().createQuery("from PcEntity where name =:pcName");
        query.setParameter("pcName", pcName);
        PcEntity pc = (PcEntity) query.uniqueResult();
        return pc;
    }

    public void delete(PcEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<PcEntity> findAll() {
        List<PcEntity> pcList = (List<PcEntity>) getCurrentSession().createQuery("from PcEntity ").list();
        return pcList;
    }

    public void deleteAll() {
        DBLogger.info("WARNING!!! Delete all PCs");
        List<PcEntity> entityList = findAll();
        for (PcEntity entity : entityList) {
            delete(entity);
        }
    }

}
