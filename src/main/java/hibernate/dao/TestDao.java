package hibernate.dao;

import hibernate.entities.TestEntity;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
public class TestDao extends BaseDao<Integer, TestEntity> {
    @Override
    public void persist(TestEntity entity) {

    }

    @Override
    public void update(TestEntity entity) {

    }

    @Override
    public TestEntity findById(Integer id) {
        TestEntity TestEntity = (TestEntity) getCurrentSession().get(TestEntity.class, id);
        return TestEntity;
    }

    public TestEntity findByIdValue(String IdValue) {
        Query query = getCurrentSession().createQuery("from TestEntity where idValue =:IdValue");
        query.setParameter("IdValue", IdValue);
        TestEntity test = (TestEntity) query.uniqueResult();
        return test;
    }

    public List<TestEntity> findByTestName(String testName) {
        Query query = getCurrentSession().createQuery("from TestEntity where name =:testName");
        query.setParameter("testName", testName);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }

    public List<TestEntity> findByTestGroupId(int groupId) {
        Query query = getCurrentSession().createQuery("from TestEntity where groupId =:groupId");
        query.setParameter("groupId", groupId);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }

    public List<TestEntity> findByTestClassId(int classId) {
        Query query = getCurrentSession().createQuery("from TestEntity where classId =:classId");
        query.setParameter("classId", classId);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }

    @Override
    public void delete(TestEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<TestEntity> findAll() {
        List<TestEntity> tests = (List<TestEntity>) getCurrentSession().createQuery("from TestEntity ").list();
        return tests;
    }

    @Override
    public void deleteAll() {

    }
}
