package db_worker.service;


import db_worker.dao.GroupDao;
import db_worker.entities.GroupEntity;

public class GroupService extends BaseService{
    private static GroupDao groupDao;

    public GroupService() {
        groupDao = new GroupDao(session);
    }

    public boolean exist(String groupName) {
        boolean isExist = false;
        groupDao.openCurrentSession();
        isExist = groupDao.exists(groupName);
        groupDao.closeCurrentSession();
        return isExist;
    }

    public void persist(GroupEntity entity) {
        groupDao.openCurrentSessionwithTransaction();
        groupDao.persist(entity);
        groupDao.closeCurrentSessionwithTransaction();
    }

    public GroupEntity findById(Integer id) {
        groupDao.openCurrentSession();
        GroupEntity group = groupDao.findById(id);
        groupDao.closeCurrentSession();
        return group;
    }

    public GroupEntity findByName(String name) {
        groupDao.openCurrentSession();
        GroupEntity group = groupDao.findByName(name);
        groupDao.closeCurrentSession();
        return group;
    }

    public GroupDao groupDao() {
        return groupDao;
    }

}
