package db_worker.dao;

import db_worker.entities.TestcaseEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class TestCaseDao extends BaseDao {
    Session session;

    public TestCaseDao(Session session) {
        this.session = session;
    }

    public Boolean exists(String clazzName, String testName) throws HibernateException {
        Query query = session.createQuery("from TestcaseEntity where className =:clazzName and testName =:testName");
        query.setParameter("testName", testName);
        query.setParameter("clazzName", clazzName);
        boolean result = (query.uniqueResult() != null);
        return result;
    }

    public void persist(TestcaseEntity entity) throws HibernateException {
        if (!exists(entity.getClassName(), entity.getTestName())) {
            getCurrentSession().save(entity);
        }
    }

    public TestcaseEntity findByClassNameTestName(String clazzName, String testName) {
        Query query = session.createQuery("from TestcaseEntity where className =:clazzName and testName =:testName");
        query.setParameter("testName", testName);
        query.setParameter("clazzName", clazzName);
        return (TestcaseEntity) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<TestcaseEntity> findAll() {
        ArrayList<TestcaseEntity> locales = (ArrayList<TestcaseEntity>) getCurrentSession().createQuery("from TestcaseEntity ").list();
        return locales;
    }
}
