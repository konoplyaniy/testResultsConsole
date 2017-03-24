package db_worker.service;


import db_worker.dao.TestCaseDao;
import db_worker.entities.TestcaseEntity;

import java.util.ArrayList;

public class TestCaseService extends BaseService {
    private static TestCaseDao testCaseDao;

    public TestCaseService() {
        testCaseDao = new TestCaseDao(session);
    }

    public boolean exist(String clazzName, String testName) {
        boolean isExist = false;
        testCaseDao.openCurrentSession();
        isExist = testCaseDao.exists(clazzName, testName);
        testCaseDao.closeCurrentSession();
        return isExist;
    }

    public TestcaseEntity findByClassNameTestName(String className, String testName) {
        testCaseDao.openCurrentSession();
        TestcaseEntity testCase = testCaseDao.findByClassNameTestName(className, testName);
        testCaseDao.closeCurrentSession();
        return testCase;
    }

    public ArrayList<TestcaseEntity> findAll() {
        testCaseDao.openCurrentSession();
        ArrayList<TestcaseEntity> result = testCaseDao.findAll();
        return result;
    }

    public TestCaseDao clazzDao() {
        return testCaseDao;
    }
}
