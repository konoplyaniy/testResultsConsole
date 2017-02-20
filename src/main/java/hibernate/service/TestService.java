package hibernate.service;

import hibernate.dao.TestDao;
import hibernate.entities.TestEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
@ManagedBean(name = "testService")
@ApplicationScoped
public class TestService {
    private static TestDao testDao;

    public TestService() {
        testDao = new TestDao();
    }

    public void persist(TestEntity entity) {
        System.out.println("try persist");
        testDao.openCurrentSessionwithTransaction();
        testDao.persist(entity);
        testDao.closeCurrentSessionwithTransaction();
        System.out.println("persist success");
    }

    /**
     * @param id - row ID in DB (like 1, 2, 3..) auto increment
     * @return
     */
    public TestEntity findById(Integer id) {
        testDao.openCurrentSession();
        TestEntity test = testDao.findById(id);
        testDao.closeCurrentSession();
        return test;
    }

    public TestEntity findByIdValue(String idValue) {
        testDao.openCurrentSession();
        TestEntity test = testDao.findByIdValue(idValue);
        testDao.closeCurrentSession();
        return test;
    }

    public List<TestEntity> findByTestName(String testName) {
        testDao.openCurrentSession();
        List<TestEntity> test = testDao.findByTestName(testName);
        testDao.closeCurrentSession();
        return test;
    }

    public List<TestEntity> findByTestGroupId(int id) {
        testDao.openCurrentSession();
        List<TestEntity> result = testDao.findByTestGroupId(id);
        testDao.closeCurrentSession();
        return result;
    }

    public List<TestEntity> findByTestClassId(int id) {
        testDao.openCurrentSession();
        List<TestEntity> result = testDao.findByTestClassId(id);
        testDao.closeCurrentSession();
        return result;
    }

    public List<TestEntity> findAll() {
        testDao.openCurrentSession();
        List<TestEntity> tests = testDao.findAll();
        testDao.closeCurrentSession();
        return tests;
    }

    public TestDao testDao() {
        return testDao;
    }
}
