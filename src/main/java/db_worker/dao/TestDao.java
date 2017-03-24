package db_worker.dao;

import db_worker.entities.TestEntity;
import db_worker.service.ClazzService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class TestDao extends BaseDao<Integer, TestEntity> {
    Session session;

    public TestDao(Session session) {
        this.session = session;
    }

    public void persist(TestEntity entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public Boolean exists(String clazzName, String testName) throws HibernateException {
        ClazzDao dao = new ClazzDao(session);
        if (dao.exists(clazzName)) {
            int clazzId = dao.findByName(clazzName).getClassId();
            Query query = session.createQuery("from TestEntity where clazzByClassId.classId =:clazzId and name =:testName");
            query.setParameter("testName", testName);
            query.setParameter("clazzId", clazzId);
            boolean result = (query.uniqueResult() != null);
            return result;
        } else return false;
    }

    public TestEntity findById(Integer id) throws HibernateException {
        TestEntity testEntity = (TestEntity) session.get(TestEntity.class, id);
        return testEntity;
    }

    public TestEntity findByIdValue(String IdValue) throws HibernateException {
        Query query = session.createQuery("from TestEntity where idValue =:IdValue");
        query.setParameter("IdValue", IdValue);
        TestEntity test = (TestEntity) query.uniqueResult();
        return test;
    }

    public List<TestEntity> findByTestName(String testName) throws HibernateException {
        Query query = session.createQuery("from TestEntity where name =:testName");
        query.setParameter("testName", testName);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }

    public TestEntity findByTestNameClassName(String testName, String clazzName) throws HibernateException {
        Query query = session.createQuery("from TestEntity where name =:testName and clazzByClassId.name =:clazzName");
        query.setParameter("testName", testName);
        query.setParameter("clazzName", clazzName);
        TestEntity tests = (TestEntity) query.uniqueResult();
        return tests;
    }

    public List<TestEntity> findByTestGroupId(int groupId) throws HibernateException {
        Query query = session.createQuery("from TestEntity where groupByGroupId.groupId =:groupId");
        query.setParameter("groupId", groupId);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }

    public List<TestEntity> findByTestClassId(int classId) throws HibernateException {
        Query query = getCurrentSession().createQuery("from TestEntity where clazzByClassId.classId =:classId");
        query.setParameter("classId", classId);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }
}
