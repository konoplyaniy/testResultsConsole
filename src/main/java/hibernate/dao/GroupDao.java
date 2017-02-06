package hibernate.dao;

import hibernate.entities.GroupEntity;
import hibernate.utils.DBLogger;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
public class GroupDao extends BaseDao<Integer, GroupEntity> {

    public Boolean exists(String groupName) {
        Query query = getCurrentSession().createQuery("from GroupEntity where name = :groupName");
        query.setString("groupName", groupName);
        return (query.uniqueResult() != null);
    }

    public void persist(GroupEntity group) {
        getCurrentSession().save(group);
    }

    public void update(GroupEntity entity) {
        getCurrentSession().update(entity);
    }

    public GroupEntity findById(Integer id) {
        GroupEntity group = (GroupEntity) getCurrentSession().get(GroupEntity.class, id);
        return group;
    }

    public GroupEntity findByName(String groupName) {
        Query query = getCurrentSession().createQuery("from GroupEntity where name =:groupName");
        query.setParameter("groupName", groupName);
        GroupEntity group = (GroupEntity) query.uniqueResult();
        return group;
    }

    public void delete(GroupEntity entity) {
        getCurrentSession().delete(entity);
    }

    //    @SuppressWarnings("unchecked")
    public List<GroupEntity> findAll() {
        List<GroupEntity> groups = (List<GroupEntity>) getCurrentSession().createQuery("from GroupEntity ").list();
        return groups;
    }

    public void deleteAll() {
        DBLogger.info("WARNING!!!! Delete all groups");
        List<GroupEntity> entityList = findAll();
        for (GroupEntity entity : entityList) {
            delete(entity);
        }
    }
}
