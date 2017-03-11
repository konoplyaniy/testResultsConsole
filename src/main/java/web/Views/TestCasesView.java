package web.Views;

import hibernate.entities.EventEntity;
import hibernate.service.EventService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by geser on 11.03.17.
 */
@ManagedBean
public class TestCasesView {
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
        EventService service = new EventService();
        ArrayList<EventEntity> eventList = (ArrayList<EventEntity>) service.findAll();
        HashSet<String> testNames = new HashSet<>();
        HashSet<String> clazzes = new HashSet<>();

        eventList.forEach(eventEntity -> {
            testNames.add(eventEntity.getTestByTestId().getName());
            clazzes.add(eventEntity.getTestByTestId().getClazzByClassId().getName());
        });

        setTestNames(testNames);
        setClazzNames(clazzes);
    }

    public void clickSearchButton() {
        if (!getClazzName().equals("") && !getTestName().equals("")) {
            setVisibleResultsForm(true);
        }
    }

    /*here will be added methods for get or generating test cases for display
    for now will be used written test case example (hard code)*/


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
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
}
