package db_worker.service;


import db_worker.dao.ClazzDao;
import db_worker.entities.ClazzEntity;

public class ClazzService extends BaseService{
    private static ClazzDao clazzDao;

    public ClazzService() {
        clazzDao = new ClazzDao(session);
    }

    public boolean exist(String clazzName) {
        boolean isExist = false;
        clazzDao.openCurrentSession();
        isExist = clazzDao.exists(clazzName);
        clazzDao.closeCurrentSession();
        return isExist;
    }

    public void persist(ClazzEntity entity) {
        clazzDao.openCurrentSessionwithTransaction();
        clazzDao.persist(entity);
        clazzDao.closeCurrentSessionwithTransaction();
    }

    public ClazzEntity findByName(String name) {
        clazzDao.openCurrentSession();
        ClazzEntity clazz = clazzDao.findByName(name);
        clazzDao.closeCurrentSession();
        return clazz;
    }

    public ClazzDao clazzDao() {
        return clazzDao;
    }
}
