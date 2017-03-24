package web.Views;

import db_worker.entities.TestcaseEntity;
import db_worker.service.TestCaseService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.HashSet;

@ManagedBean
@SessionScoped
public class TestCasesView implements Serializable {
    private TestcaseEntity testcaseEntity;
    private String testName = "";
    private String clazzName = "";
    private HashSet<String> clazzNames;
    private HashSet<String> testNames;
    private boolean visibleResultsForm = false;

    @PostConstruct
    public void initDropDowns() {
        initDropdownsData();
    }

    private void initDropdownsData() {
        TestCaseService service = new TestCaseService();
        ArrayList<TestcaseEntity> testCaseList = service.findAll();

        HashSet<String> testNames = new HashSet<>();
        HashSet<String> clazzes = new HashSet<>();

        testCaseList.forEach(testCase -> {
            testNames.add(testCase.getTestName());
            clazzes.add(testCase.getClassName());
        });

        setTestNames(testNames);
        setClazzNames(clazzes);
    }

    public void clickSearchButton() {
        if (!getClazzName().equals("") && !getTestName().equals("")) {
            testcaseEntity = new TestCaseService().findByClassNameTestName(getClazzName(), getTestName());
            System.out.println(testcaseEntity);
            setVisibleResultsForm(true);
        }
    }

    public static void main(String[] args) {
        TestcaseEntity testCase = new TestcaseEntity();
        testCase.setClassName("class name");
        testCase.setTestName("testName");
        testCase.setParameters("parameter1, parameter2");
        testCase.setDescription("Description");
        testCase.setSteps("step1, step2, step3");
        testCase.setExpectedResult("expected result");
        testCase.setAditionalInfo("additional info");
        testCase.setMavenFront("command1");
        testCase.setMavenMembers("command2");
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
        System.out.println("set testName " + testName);
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
        System.out.println("set className " + testName);
    }

    public HashSet<String> getClazzNames() {
        return clazzNames;
    }

    public void setClazzNames(HashSet<String> clazzNames) {
        this.clazzNames = clazzNames;
    }

    public HashSet<String> getTestNames() {
        return testNames;
    }

    public void setTestNames(HashSet<String> testNames) {
        this.testNames = testNames;
    }

    public boolean isVisibleResultsForm() {
        return visibleResultsForm;
    }

    public void setVisibleResultsForm(boolean visibleResultsForm) {
        this.visibleResultsForm = visibleResultsForm;
    }

    public TestcaseEntity getTestcaseEntity() {
        return testcaseEntity;
    }

    public void setTestcaseEntity(TestcaseEntity testcaseEntity) {
        this.testcaseEntity = testcaseEntity;
    }
}
