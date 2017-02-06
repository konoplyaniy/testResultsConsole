package hibernate.dbTesting;

import hibernate.entities.TestEntity;
import hibernate.service.ClazzService;
import hibernate.service.GroupService;

import javax.persistence.Entity;

/**
 * Created by geser on 04.02.17.
 */
@Entity
public class TestData {
    public TestData(int testId, String idValue, String name, int groupId, int classId, String className, String groupName) {
        this.testId = testId;
        this.idValue = idValue;
        this.name = name;
        this.groupName = new GroupService().findById(groupId).getName();
        this.className = new ClazzService().findById(classId).getName();

    }

    public TestData(TestEntity entity){
        this.testId = entity.getTestId();
        this.idValue = entity.getIdValue();
        this.name = entity.getName();
        this.groupName = new GroupService().findById(entity.getGroupId()).getName();
        this.className = new ClazzService().findById(entity.getClassId()).getName();

    }

    public TestData(int id, String idValue, String name, String groupName, String className) {
        this.testId = id;
        this.idValue = idValue;
        this.name = name;
        this.groupName = groupName;
        this.className = className;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

        private int testId;
        private String idValue;
        private String name;
        private String groupName;
        private String className;

    @Override
    public String toString() {
        return "ID:" + testId + "|Id Value:" + idValue + " |Name:" + name + " |Group:" + groupName + " |Class:" + className +"/n";
    }
}
