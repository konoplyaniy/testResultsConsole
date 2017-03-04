package hibernate.service;

import hibernate.dao.BrowserDao;
import hibernate.entities.BrowserEntity;
import hibernate.utils.DBLogger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Sergiy.K on 31-Jan-17.
 */
@ApplicationScoped
public class BrowserService {
    private static BrowserDao browserDao;

    public BrowserService(){
        browserDao = new BrowserDao();
    }

    public boolean exist(BrowserEntity browserEntity){
        boolean isExist = false;
        browserDao.openCurrentSession();
        isExist = browserDao.exists(browserEntity.getBrowser());
        browserDao.closeCurrentSession();
        return isExist;
    }

    public void persist(BrowserEntity entity) {
        browserDao.openCurrentSessionwithTransaction();
        if (!browserDao.exists(entity.getBrowser())){
            browserDao.persist(entity);
            DBLogger.info("Add new browser with name [" + entity.getBrowser() + "]");
        }else DBLogger.info("This browser already exist in DB [" + entity.getBrowser() + "]");
        browserDao.closeCurrentSessionwithTransaction();
    }

    public void update(BrowserEntity entity) {
        browserDao.openCurrentSessionwithTransaction();
        browserDao.update(entity);
        browserDao.closeCurrentSessionwithTransaction();
    }

    public BrowserEntity findById(Integer id) {
        DBLogger.info("Search browser ID [" + id + "]");
        browserDao.openCurrentSession();
        BrowserEntity browser = browserDao.findById(id);
        browserDao.closeCurrentSession();
        DBLogger.info("Find browser [" + browser + "]");
        return browser;
    }

    public BrowserEntity findByName(String name) {
        DBLogger.info("Search browser [" + name + "]");
        browserDao.openCurrentSession();
        BrowserEntity browser = browserDao.findByName(name);
        browserDao.closeCurrentSession();
        DBLogger.info("Find browser [" + browser + "]");
        return browser;
    }

    public void delete(Integer id) {
        browserDao.openCurrentSessionwithTransaction();
        BrowserEntity browser = browserDao.findById(id);
        browserDao.delete(browser);
        browserDao.closeCurrentSessionwithTransaction();
    }

    public List<BrowserEntity> findAll() {
        browserDao.openCurrentSession();
        List<BrowserEntity> browsers = browserDao.findAll();
        browserDao.closeCurrentSession();
        DBLogger.info("Get all browsers");
        return browsers;
    }

    public int getRowsCount(){
        browserDao.openCurrentSession();
        List<BrowserEntity> browsers = browserDao.findAll();
        browserDao.closeCurrentSession();
        return browsers.size();
    }

    public void deleteAll() {
        browserDao.openCurrentSessionwithTransaction();
        browserDao.deleteAll();
        browserDao.closeCurrentSessionwithTransaction();
    }

    public BrowserDao browserDao() {
        return browserDao;
    }

}
