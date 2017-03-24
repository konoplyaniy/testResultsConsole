package db_worker.dao;


import db_worker.entities.PcEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class PcDao extends BaseDao<Integer, PcEntity> {
    Session session;

    public PcDao(Session session) {
        this.session = session;
    }

    public Boolean exists(String pcName) throws HibernateException {
        Query query = session.createQuery("from PcEntity where name = :pcName");
        query.setString("pcName", pcName);
        boolean result = (query.uniqueResult() != null);
        return result;
    }

    public void persist(PcEntity pcDetails) throws HibernateException {
        if (!exists(pcDetails.getName())){
            getCurrentSession().save(pcDetails);
        }
    }

    public PcEntity findById(Integer id) throws HibernateException {
        PcEntity pc = (PcEntity) session.get(PcEntity.class, id);
        return pc;
    }

    public PcEntity findByPcName(String pcName) throws HibernateException {
        Query query = session.createQuery("from PcEntity where name =:pcName");
        query.setParameter("pcName", pcName);
        PcEntity pc = (PcEntity) query.uniqueResult();
        return pc;
    }
}
