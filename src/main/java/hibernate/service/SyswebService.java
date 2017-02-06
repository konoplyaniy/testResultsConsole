package hibernate.service;

import hibernate.dao.SyswebDao;
import hibernate.entities.SyswebEntity;
import hibernate.utils.DBLogger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
@ManagedBean(name = "syswebService")
@ApplicationScoped
public class SyswebService {
    private static SyswebDao syswebDao;

    public SyswebService() {
        syswebDao = new SyswebDao();
    }

    public void persist(SyswebEntity entity) {
        syswebDao.openCurrentSessionwithTransaction();
        if (!syswebDao.exists(entity.getName())) {
            syswebDao.persist(entity);
            DBLogger.info("Add new sysweb [" + entity.getName() + "]");
        }
        syswebDao.closeCurrentSessionwithTransaction();
    }

    public void update(SyswebEntity entity) {
        syswebDao.openCurrentSessionwithTransaction();
        syswebDao.update(entity);
        syswebDao.closeCurrentSessionwithTransaction();
    }

    public SyswebEntity findById(Integer id) {
        DBLogger.info("Search sysweb id [" + id + "]");
        syswebDao.openCurrentSession();
        SyswebEntity sysweb = syswebDao.findById(id);
        syswebDao.closeCurrentSession();
        DBLogger.info("Find sysweb [" + sysweb + "]");
        return sysweb;
    }

    public SyswebEntity findByName(String name) {
        DBLogger.info("Search sysweb Url [" + name + "]");
        syswebDao.openCurrentSession();
        SyswebEntity sysweb = syswebDao.findByName(name);
        syswebDao.closeCurrentSession();
        DBLogger.info("Find sysweb [" + sysweb + "]");
        return sysweb;
    }

    public void delete(Integer id) {
        syswebDao.openCurrentSessionwithTransaction();
        SyswebEntity sysweb = syswebDao.findById(id);
        syswebDao.delete(sysweb);
        syswebDao.closeCurrentSessionwithTransaction();
    }

    public List<SyswebEntity> findAll() {
        syswebDao.openCurrentSession();
        List<SyswebEntity> syswebs = syswebDao.findAll();
        syswebDao.closeCurrentSession();
        return syswebs;
    }

    public int getRowsCount() {
        syswebDao.openCurrentSession();
        List<SyswebEntity> syswebs = syswebDao.findAll();
        syswebDao.closeCurrentSession();
        return syswebs.size();
    }

    public void deleteAll() {
        syswebDao.openCurrentSessionwithTransaction();
        syswebDao.deleteAll();
        syswebDao.closeCurrentSessionwithTransaction();
    }

    public SyswebDao syswebDao() {
        return syswebDao;
    }

}