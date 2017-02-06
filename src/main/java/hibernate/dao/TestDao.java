package hibernate.dao;

import hibernate.entities.ClazzEntity;
import hibernate.entities.GroupEntity;
import hibernate.entities.TestEntity;
import hibernate.service.ClazzService;
import hibernate.service.GroupService;
import hibernate.utils.DBLogger;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
public class TestDao extends BaseDao<Integer, TestEntity> {
    public TestDao() {
    }

    public void persist(TestEntity test) {
        // will be edited
        //  name and class
        //
        ArrayList<TestEntity> testsInDb = (ArrayList<TestEntity>) findAll();
        if (!testsInDb.contains(test)) {
            getCurrentSession().save(test);
        } else DBLogger.info("Test:" + test + " is already in DB");
    }
// should be deleted
//    public TestEntity findByNameClassParam(TestEntity test){
//        String name = test.getName();
//        String params = test.getParams();
//        int clazzId = test.getClassId();
//        if (params != null){
//            System.out.println("Find params for this test in DB");
//            Query query = getCurrentSession().createQuery("from TestEntity " +
//                    "where name = :name " +
//                    "and params = :params " +
//                    "and classId = :clazzId");
//            query.setParameter("name", name);
//            query.setParameter("params", params);
//            query.setParameter("clazzId", clazzId);
//            return (TestEntity) query.uniqueResult();
//        }
//        Query query = getCurrentSession().createQuery("from TestEntity " +
//                "where name = :name and classId = :clazzId");
//        query.setParameter("name", name);
//        query.setParameter("clazzId", clazzId);
//        return (TestEntity) query.uniqueResult();
//    }

    public void update(TestEntity entity) {
        getCurrentSession().update(entity);
    }


    public TestEntity findById(Integer id) {
        TestEntity test = (TestEntity) getCurrentSession().get(TestEntity.class, id);
        return test;
    }

    public ArrayList<String> getAllTests(){
        ArrayList<String> result;
//        Query query = getCurrentSession().createQuery("SELECT t.id, t.idValue, t.name, G.name, C.name FROM TestEntity as t" +
//                " inner join ClazzEntity as C on T.classId = C.classId" +
//                " inner join GroupEntity as G on T.groupId = G.groupId");
        Query q = getCurrentSession().createQuery("select g.name, c.name  from TestEntity t, ClazzEntity c, GroupEntity g " +
                "where t.classId = c.classId and g.groupId = t.groupId");
        result = (ArrayList<String>) q.list();

//        List<TestEntity> tests = (List<TestEntity>) getCurrentSession().createQuery("from TestEntity ").list();
        return result;
    }

    public List<TestEntity> findByIdValue(String testId) {
        Query query = getCurrentSession().createQuery("from TestEntity where idValue =:testId");
        query.setParameter("testId", testId);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        if (tests.size() == 0) {
            return tests;
        }
        return null;
    }

    public List<TestEntity> findByTestName(String testName) {
        Query query = getCurrentSession().createQuery("from TestEntity where name =:testName");
        query.setParameter("testName", testName);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }

    public List<TestEntity> findByTestGroup(String testGroup) {
        GroupService service = new GroupService();
        GroupEntity group = service.findByName(testGroup);
        int groupId;
        if (group != null) {
            groupId = group.getGroupId();
            DBLogger.info("Current id for group is " + groupId);
            Query query = getCurrentSession().createQuery("from TestEntity where groupId =:groupId");
            query.setParameter("groupId", groupId);
            List<TestEntity> tests = (List<TestEntity>) query.list();
            return tests;
        }
        DBLogger.info("Can't find group: " + testGroup + " in DB");
        return null;
    }


    /**
     * Here should add NULL check
     *
     * @param id
     * @return
     */
    public String getGruopNameById(int id) {
        GroupService service = new GroupService();
        return service.findById(id).getName();
    }

    /**
     * Here should add NULL check
     *
     * @param id
     * @return
     */
    public String getClassNameById(int id) {
        ClazzService service = new ClazzService();
        return service.findById(id).getName();
    }

    public List<TestEntity> findByTestGroupId(int groupId) {
        Query query = getCurrentSession().createQuery("from TestEntity where groupId =:groupId");
        query.setParameter("groupId", groupId);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }

    public List<TestEntity> findByTestClass(String className) {
        ClazzService service = new ClazzService();
        ClazzEntity clazz = service.findByName(className);
        int id = clazz.getClassId();
        Query query = getCurrentSession().createQuery("from TestEntity where class =:id");
        query.setParameter("id", id);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        if (tests.size() == 0) {
            return tests;
        }
        return null;
    }

    public List<TestEntity> findByClassId(int classId) {
        Query query = getCurrentSession().createQuery("from TestEntity where classId =:classId");
        query.setParameter("classId", classId);
        List<TestEntity> tests = (List<TestEntity>) query.list();
        return tests;
    }

    public void delete(TestEntity entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<TestEntity> findAll() {
        List<TestEntity> tests = (List<TestEntity>) getCurrentSession().createQuery("from TestEntity ").list();
        return tests;
    }

    public void deleteAll() {
        List<TestEntity> entityList = findAll();
        for (TestEntity entity : entityList) {
            delete(entity);
        }
    }
}
