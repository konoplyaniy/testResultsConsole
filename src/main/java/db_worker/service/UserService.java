package db_worker.service;

import db_worker.dao.UserDao;
import db_worker.entities.UserEntity;


public class UserService {
    private static UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void persist(UserEntity entity) {
        userDao.openCurrentSessionwithTransaction();
        if (!userDao.isUserExist(entity.getUsername(), entity.getPassword())) {
            userDao.persist(entity);
        }
        userDao.closeCurrentSessionwithTransaction();
    }

    public UserDao userDao() {
        return userDao;
    }
}
