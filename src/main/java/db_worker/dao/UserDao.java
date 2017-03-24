package db_worker.dao;

import db_worker.entities.UserEntity;
import org.hibernate.Query;


import javax.persistence.NoResultException;


/**
 * Created by Sergiy.K on 07-Feb-17.
 */
public class UserDao extends BaseDao<Integer, UserEntity> {

    public void persist(UserEntity entity) {
    }

    public Boolean isUserExist(String userName, String password) {
        Query query = getCurrentSession().createQuery("from UserEntity where username =:userName and password =:password");
        query.setString("userName", userName);
        query.setString("password", password);
        return (query.uniqueResult() != null);
    }

    public UserEntity getUser(String userName, String password) {
        System.out.println(userName);
        System.out.println(password);
        openCurrentSession();
        try {
            Query query = getCurrentSession().createQuery("from UserEntity where username =:userName and password =:password");
            query.setString("userName", userName);
            query.setString("password", password);
            return (UserEntity) query.uniqueResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            closeCurrentSession();
        }
    }

}
