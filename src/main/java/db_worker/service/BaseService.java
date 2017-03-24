package db_worker.service;

import db_worker.dao.BaseDao;
import org.hibernate.Session;

import java.io.Serializable;


public class BaseService implements Serializable {
    public Session session;

    public BaseService() {
        session = BaseDao.getSessionFactory().openSession();
    }
}
