package db_worker.service;


import db_worker.dao.SyswebDao;
import db_worker.entities.SyswebEntity;


public class SyswebService extends BaseService{
    private static SyswebDao syswebDao;

    public SyswebService() {
        syswebDao = new SyswebDao(session);
    }

    public boolean exist(String sysweb) {
        boolean isExist = false;
        syswebDao.openCurrentSession();
        isExist = syswebDao.exists(sysweb);
        syswebDao.closeCurrentSession();
        return isExist;
    }

    public void persist(SyswebEntity entity) {
        syswebDao.openCurrentSessionwithTransaction();
        syswebDao.persist(entity);
        syswebDao.closeCurrentSessionwithTransaction();
    }

    public SyswebEntity findById(Integer id) {
        syswebDao.openCurrentSession();
        SyswebEntity sysweb = syswebDao.findById(id);
        syswebDao.closeCurrentSession();
        return sysweb;
    }

    public SyswebEntity findByName(String name) {
        syswebDao.openCurrentSession();
        SyswebEntity sysweb = syswebDao.findByName(name);
        syswebDao.closeCurrentSession();
        return sysweb;
    }

    public SyswebDao syswebDao() {
        return syswebDao;
    }

}
