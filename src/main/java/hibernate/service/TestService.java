package hibernate.service;

import hibernate.dao.ClazzDao;
import hibernate.dao.GroupDao;
import hibernate.dao.TestDao;
import hibernate.entities.TestEntity;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
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
        testDao.openCurrentSessionwithTransaction();
        testDao.persist(entity);
        testDao.closeCurrentSessionwithTransaction();
    }

    public void update(TestEntity entity) {
        testDao.openCurrentSessionwithTransaction();
        testDao.update(entity);
        testDao.closeCurrentSessionwithTransaction();
    }


    /**
     * @param id - row ID in DB (like 1, 2, 3..) auto increment
     * @return
     */
    public TestEntity findById(Integer id) {
        testDao.openCurrentSession();
        TestEntity clazz = testDao.findById(id);
        testDao.closeCurrentSession();
        return clazz;
    }


    /**
     * @param testID - annotation TestID (Like CR-M-WEBSITEBUILDER-T0001)
     * @return
     */
    public List<TestEntity> findByIdValue(String testID) {
        testDao.openCurrentSession();
        List<TestEntity> tests = testDao.findByIdValue(testID);
        testDao.closeCurrentSession();
        return tests;
    }

    public List<TestEntity> findByTestName(String name) {
        testDao.openCurrentSession();
        List<TestEntity> tests = testDao.findByTestName(name);
        testDao.closeCurrentSession();
        return tests;
    }

    public List<TestEntity> findByClassName(String name) {
        testDao.openCurrentSession();
        List<TestEntity> tests = testDao.findByTestClass(name);
        testDao.closeCurrentSession();
        return tests;
    }

    public List<TestEntity> findByGroup(String testGroup) {
        testDao.openCurrentSession();
        List<TestEntity> tests = testDao.findByTestGroup(testGroup);
        testDao.closeCurrentSession();
        return tests;
    }

    public String getGroupName(int id) {
        String result = "";
        GroupDao groupDao = new GroupDao();
        groupDao.openCurrentSession();
        result = groupDao.findById(id).getName();
        groupDao.closeCurrentSession();
        return result;
    }

    public String getClazzName(int id) {
        String result = "";
        ClazzDao clazzDao = new ClazzDao();
        clazzDao.openCurrentSession();
        result = clazzDao.findById(id).getName();
        clazzDao.closeCurrentSession();
        return result;
    }

    public ArrayList<String> getAllTestsData(){
        testDao.openCurrentSession();
        ArrayList<String> tests = testDao.getAllTests();
        testDao.closeCurrentSession();
        return tests;
    }

    public List<TestEntity> findByBrowser(String browser) {
        testDao.openCurrentSession();
//        List<TestEntity> tests = testDao.findByBrowser(browser);
        testDao.closeCurrentSession();
        return null;
    }

    public void delete(Integer id) {
        testDao.openCurrentSessionwithTransaction();
        TestEntity test = testDao.findById(id);
        testDao.delete(test);
        testDao.closeCurrentSessionwithTransaction();
    }

    public List<TestEntity> findAll() {
        testDao.openCurrentSession();
        List<TestEntity> tests = testDao.findAll();
        testDao.closeCurrentSession();
        return tests;
    }

    public int getRowsCount() {
        testDao.openCurrentSession();
        List<TestEntity> tests = testDao.findAll();
        testDao.closeCurrentSessionwithTransaction();
        return tests.size();
    }

    public void deleteAll() {
        testDao.openCurrentSessionwithTransaction();
        testDao.deleteAll();
        testDao.closeCurrentSessionwithTransaction();
    }

    public TestDao testDao() {
        return testDao;
    }
}
