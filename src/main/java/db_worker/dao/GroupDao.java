package db_worker.dao;


import db_worker.entities.GroupEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class GroupDao extends BaseDao<Integer, GroupEntity> {
    Session session;

    public GroupDao(Session session) {
        this.session = session;
    }

    public Boolean exists(String groupName) throws HibernateException {
        Query query = session.createQuery("from GroupEntity where name = :groupName");
        query.setString("groupName", groupName);
        return (query.uniqueResult() != null);
    }

    public void persist(GroupEntity group) throws HibernateException {
        if (!exists(group.getName())){
            getCurrentSession().save(group);
        }
    }

    public GroupEntity findById(Integer id) throws HibernateException {
        GroupEntity group = (GroupEntity) session.get(GroupEntity.class, id);
        return group;
    }

    public GroupEntity findByName(String groupName) throws HibernateException {
        Query query = session.createQuery("from GroupEntity where name =:groupName");
        query.setParameter("groupName", groupName);
        GroupEntity group = (GroupEntity) query.uniqueResult();
        return group;
    }
}
