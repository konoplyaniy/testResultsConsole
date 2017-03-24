package db_worker.service;


import db_worker.dao.BrowserDao;
import db_worker.entities.BrowserEntity;


public class BrowserService extends BaseService {
    private static BrowserDao browserDao;

    public BrowserService() {
        browserDao = new BrowserDao(session);
    }

    public boolean exist(BrowserEntity browserEntity) {
        boolean isExist = false;
        browserDao.openCurrentSession();
        isExist = browserDao.exists(browserEntity.getBrowser());
        browserDao.closeCurrentSession();
        return isExist;
    }

    public void persist(BrowserEntity entity) {
        browserDao.openCurrentSessionwithTransaction();
        browserDao.persist(entity);
        browserDao.closeCurrentSessionwithTransaction();
    }

    public BrowserEntity findByName(String name) {
        browserDao.openCurrentSession();
        BrowserEntity browser = browserDao.findByName(name);
        browserDao.closeCurrentSession();
        return browser;
    }

    public BrowserDao browserDao() {
        return browserDao;
    }

}
