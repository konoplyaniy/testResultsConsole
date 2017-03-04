package hibernate.service;


import hibernate.dao.ClazzDao;
import hibernate.entities.ClazzEntity;
import hibernate.utils.DBLogger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@ApplicationScoped
public class ClazzService {
    private static ClazzDao clazzDao;

    public ClazzService(){
        clazzDao = new ClazzDao();
    }

    public boolean exist(ClazzEntity clazzEntity){
        boolean isExist = false;
        clazzDao.openCurrentSession();
        isExist = clazzDao.exists(clazzEntity.getName());
        clazzDao.closeCurrentSession();
        return isExist;
    }

    public void persist(ClazzEntity entity) {
        clazzDao.openCurrentSessionwithTransaction();
        if (!clazzDao.exists(entity.getName())){
            clazzDao.persist(entity);
            DBLogger.info("Add new class with name [" + entity.getName() + "]");
        }
        clazzDao.closeCurrentSessionwithTransaction();
    }

    public void update(ClazzEntity entity) {
        clazzDao.openCurrentSessionwithTransaction();
        clazzDao.update(entity);
        clazzDao.closeCurrentSessionwithTransaction();
    }

    public ClazzEntity findById(Integer id) {
        DBLogger.info("Search group ID [" + id + "]");
        clazzDao.openCurrentSession();
        ClazzEntity clazz = clazzDao.findById(id);
        clazzDao.closeCurrentSession();
        DBLogger.info("Find class [" + clazz + "]");
        return clazz;
    }

    public ClazzEntity findByName(String name) {
        DBLogger.info("Search class name [" + name + "]");
        clazzDao.openCurrentSession();
        ClazzEntity clazz = clazzDao.findByName(name);
        clazzDao.closeCurrentSession();
        DBLogger.info("Find class [" + clazz + "]");
        return clazz;
    }

    public void delete(Integer id) {
        clazzDao.openCurrentSessionwithTransaction();
        ClazzEntity clazz = clazzDao.findById(id);
        clazzDao.delete(clazz);
        clazzDao.closeCurrentSessionwithTransaction();
    }

    public List<ClazzEntity> findAll() {
        clazzDao.openCurrentSession();
        List<ClazzEntity> clazzes = clazzDao.findAll();
        clazzDao.closeCurrentSession();
        DBLogger.info("Get all classes");
        return clazzes;
    }

    public int getRowsCount(){
        clazzDao.openCurrentSession();
        List<ClazzEntity> clazzes = clazzDao.findAll();
        clazzDao.closeCurrentSession();
        return clazzes.size();
    }

    public void deleteAll() {
        clazzDao.openCurrentSessionwithTransaction();
        clazzDao.deleteAll();
        clazzDao.closeCurrentSessionwithTransaction();
    }

    public ClazzDao clazzDao() {
        return clazzDao;
    }
}
