package web.exporter;

import hibernate.entities.TestEntity;
import hibernate.service.TestService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class TestTableExporter {
//    private ArrayList<TestData> tests;
    private List<TestEntity> tests;

    @ManagedProperty("#{testService}")
    private TestService testService;

    @PostConstruct
    public void init() {
        testService = new TestService();
        tests = testService.findAll();

//        List<TestEntity> testEntities = testService.findAll();
//        tests = new ArrayList<TestData>();
//        for (TestEntity testEntity : testEntities) {
//            tests.add(new TestData(testEntity));
//        }
    }

    public List<TestEntity> getTests(){
        return tests;
    }

//    public ArrayList<TestData> getTests(){
//        return tests;
//    }

    public void setTestService(TestService service){
        this.testService = service;
    }
}
