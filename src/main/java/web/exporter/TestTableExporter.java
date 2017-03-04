package web.exporter;

import hibernate.entities.TestEntity;
import hibernate.service.TestService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class TestTableExporter {
    private List<TestEntity> tests;

    @ManagedProperty("#{testService}")
    private TestService testService;

    @PostConstruct
    public void init() {
        testService = new TestService();
        tests = testService.findAll();
    }

    public List<TestEntity> getTests() {
        return tests;
    }

    public void setTestService(TestService service) {
        this.testService = service;
    }
}
