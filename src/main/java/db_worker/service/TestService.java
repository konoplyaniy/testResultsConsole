package db_worker.service;


import db_worker.dao.TestDao;
import db_worker.entities.ClazzEntity;
import db_worker.entities.TestEntity;

import java.util.List;

public class TestService extends BaseService{
    private static TestDao testDao;

    public TestService() {
        testDao = new TestDao(session);
    }

    public void persist(TestEntity entity) {
        testDao.openCurrentSessionwithTransaction();
        testDao.persist(entity);
        testDao.closeCurrentSessionwithTransaction();
    }

    public boolean exist(String clazzName, String testName){
        boolean isExist = false;
        testDao.openCurrentSession();
        isExist = testDao.exists(clazzName, testName);
        testDao.closeCurrentSession();
        return isExist;
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

    public TestEntity findByTestNameClazzName(String testName, String clazzName) {
        testDao.openCurrentSession();
        TestEntity test = testDao.findByTestNameClassName(testName, clazzName);
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

    public TestDao testDao() {
        return testDao;
    }
}
