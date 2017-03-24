package db_worker.service;


import db_worker.dao.LocaleDao;
import db_worker.entities.LocaleEntity;

public class LocaleService extends BaseService {
    private static LocaleDao localeDao;

    public LocaleService() {
        localeDao = new LocaleDao(session);
    }

    public boolean exist(String locale) {
        boolean isExist = false;
        localeDao.openCurrentSession();
        isExist = localeDao.exists(locale);
        localeDao.closeCurrentSession();
        return isExist;
    }

    public void persist(LocaleEntity entity) {
        localeDao.openCurrentSessionwithTransaction();
        localeDao.persist(entity);
        localeDao.closeCurrentSessionwithTransaction();
    }

    public LocaleEntity findById(Integer id) {
        localeDao.openCurrentSession();
        LocaleEntity locale = localeDao.findById(id);
        localeDao.closeCurrentSession();
        return locale;
    }

    public LocaleEntity findByName(String name) {
        localeDao.openCurrentSession();
        LocaleEntity locale = localeDao.findByLocale(name);
        localeDao.closeCurrentSession();
        return locale;
    }

    public LocaleDao localeDao() {
        return localeDao;
    }
}
