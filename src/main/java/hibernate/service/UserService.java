package hibernate.service;

import hibernate.dao.UserDao;
import hibernate.entities.UserEntity;
import hibernate.utils.DBLogger;

import java.util.List;

/**
 * Created by Sergiy.K on 07-Feb-17.
 */
public class UserService {
    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void persist(UserEntity entity) {
        userDao.openCurrentSessionwithTransaction();
        if (!userDao.isUserExist(entity.getUsername(), entity.getPassword())) {
            userDao.persist(entity);
            DBLogger.info("Add new locale [" + entity.getUsername() + "]");
        }
        userDao.closeCurrentSessionwithTransaction();
    }

    public void update(UserEntity entity) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(entity);
        userDao.closeCurrentSessionwithTransaction();
    }

    public UserEntity findById(Integer id) {
        DBLogger.info("Search user ID [" + id + "]");
        userDao.openCurrentSession();
        UserEntity user = userDao.findById(id);
        userDao.closeCurrentSession();
        DBLogger.info("Find locale [" + user + "]");
        return user;
    }

    public List<UserEntity> findAll() {
        userDao.openCurrentSession();
        List<UserEntity> locales = userDao.findAll();
        userDao.closeCurrentSession();
        return locales;
    }

    public int getRowsCount() {
        userDao.openCurrentSession();
        List<UserEntity> locales = userDao.findAll();
        userDao.closeCurrentSession();
        return locales.size();
    }

    public void deleteAll() {
        userDao.openCurrentSessionwithTransaction();
        userDao.deleteAll();
        userDao.closeCurrentSessionwithTransaction();
    }

    public UserDao userDao() {
        return userDao;
    }
}
