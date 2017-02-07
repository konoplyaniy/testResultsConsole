package hibernate.dao;

import hibernate.entities.UserEntity;
import org.hibernate.Query;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Sergiy.K on 07-Feb-17.
 */
public class UserDao extends BaseDao<Integer, UserEntity> {
    @Override
    public void persist(UserEntity entity) {
    }

    public Boolean isUserExist(String userName, String password) {
        Query query = getCurrentSession().createQuery("from UserEntity where username =:userName and password =:password");
        query.setString("userName", userName);
        query.setString("password", password);
        return (query.uniqueResult() != null);
    }

    public UserEntity getUser(String userName, String password){
        System.out.println(userName);
        System.out.println(password);
        try {
            Query query = getCurrentSession().createQuery("from UserEntity where username =:userName and password =:password");
            query.setString("userName", userName);
            query.setString("password", password);
            return (UserEntity) query.uniqueResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void update(UserEntity entity) {
    }

    @Override
    public UserEntity findById(Integer key) {
        try {
//            UserEntity user = (UserEntity) em.createQuery("SELECT u from User u where u.nameUser = :name and u.password = :password").setParameter("name", nameUser).setParameter("password", password).getSingleResult();
//            return user;
        } catch (NoResultException e) {
            return null;
        }
        return null;
    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
