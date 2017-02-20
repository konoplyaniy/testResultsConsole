package hibernate.service;

import hibernate.dao.GroupDao;
import hibernate.entities.GroupEntity;
import hibernate.utils.DBLogger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@ApplicationScoped
public class GroupService {
    private static GroupDao groupDao;

    public GroupService() {
        groupDao = new GroupDao();
    }

    public boolean exist(GroupEntity groupEntity){
        boolean isExist = false;
        groupDao.openCurrentSession();
        isExist = groupDao.exists(groupEntity.getName());
        groupDao.closeCurrentSession();
        return isExist;
    }

    public void persist(GroupEntity entity) {
        groupDao.openCurrentSessionwithTransaction();
        if (!groupDao.exists(entity.getName())) {
            groupDao.persist(entity);
            DBLogger.info("Add new group with name [" + entity.getName() + "]");
        }
        groupDao.closeCurrentSessionwithTransaction();
    }

    public void update(GroupEntity entity) {
        groupDao.openCurrentSessionwithTransaction();
        groupDao.update(entity);
        groupDao.closeCurrentSessionwithTransaction();
    }

    public GroupEntity findById(Integer id) {
        DBLogger.info("Search group ID [" + id + "]");
        groupDao.openCurrentSession();
        GroupEntity group = groupDao.findById(id);
        groupDao.closeCurrentSession();
        DBLogger.info("Find group [" + group + "]");
        return group;
    }

    public GroupEntity findByName(String name) {
        DBLogger.info("Search group name [" + name + "]");
        groupDao.openCurrentSession();
        GroupEntity group = groupDao.findByName(name);
        groupDao.closeCurrentSession();
        DBLogger.info("Find group [" + group + "]");
        return group;
    }

    public void delete(Integer id) {
        groupDao.openCurrentSessionwithTransaction();
        GroupEntity group = groupDao.findById(id);
        groupDao.delete(group);
        groupDao.closeCurrentSessionwithTransaction();
    }

    public List<GroupEntity> findAll() {
        groupDao.openCurrentSession();
        List<GroupEntity> groups = groupDao.findAll();
        groupDao.closeCurrentSession();
        return groups;
    }

    public int getRowsCount() {
        groupDao.openCurrentSession();
        List<GroupEntity> groups = groupDao.findAll();
        groupDao.closeCurrentSession();
        return groups.size();
    }

    public void deleteAll() {
        groupDao.openCurrentSessionwithTransaction();
        groupDao.deleteAll();
        groupDao.closeCurrentSessionwithTransaction();
    }

    public GroupDao groupDao() {
        return groupDao;
    }

}
