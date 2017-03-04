package hibernate.service;


import hibernate.dao.LocaleDao;
import hibernate.entities.LocaleEntity;
import hibernate.utils.DBLogger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@ApplicationScoped
public class LocaleService {
    private static LocaleDao localeDao;

    public LocaleService() {
        localeDao = new LocaleDao();
    }

    public boolean exist(LocaleEntity localeEntity){
        boolean isExist = false;
        localeDao.openCurrentSession();
        isExist = localeDao.exists(localeEntity.getLocale());
        localeDao.closeCurrentSession();
        return isExist;
    }

    public void persist(LocaleEntity entity) {
        localeDao.openCurrentSessionwithTransaction();
        if (!localeDao.exists(entity.getLocale())) {
            localeDao.persist(entity);
            DBLogger.info("Add new locale [" + entity.getLocale() + "]");
        }
        localeDao.closeCurrentSessionwithTransaction();
    }

    public void update(LocaleEntity entity) {
        localeDao.openCurrentSessionwithTransaction();
        localeDao.update(entity);
        localeDao.closeCurrentSessionwithTransaction();
    }

    public LocaleEntity findById(Integer id) {
        DBLogger.info("Search locale ID [" + id + "]");
        localeDao.openCurrentSession();
        LocaleEntity locale = localeDao.findById(id);
        localeDao.closeCurrentSession();
        DBLogger.info("Find locale [" + locale + "]");
        return locale;
    }

    public LocaleEntity findByName(String name) {
        DBLogger.info("Search locale name [" + name + "]");
        localeDao.openCurrentSession();
        LocaleEntity locale = localeDao.findByLocale(name);
        localeDao.closeCurrentSession();
        DBLogger.info("Find locale [" + locale + "]");
        return locale;
    }

    public void delete(Integer id) {
        localeDao.openCurrentSessionwithTransaction();
        LocaleEntity locale = localeDao.findById(id);
        localeDao.delete(locale);
        localeDao.closeCurrentSessionwithTransaction();
    }

    public List<LocaleEntity> findAll() {
        localeDao.openCurrentSession();
        List<LocaleEntity> locales = localeDao.findAll();
        localeDao.closeCurrentSession();
        return locales;
    }

    public int getRowsCount() {
        localeDao.openCurrentSession();
        List<LocaleEntity> locales = localeDao.findAll();
        localeDao.closeCurrentSession();
        return locales.size();
    }

    public void deleteAll() {
        localeDao.openCurrentSessionwithTransaction();
        localeDao.deleteAll();
        localeDao.closeCurrentSessionwithTransaction();
    }

    public LocaleDao localeDao() {
        return localeDao;
    }
}
