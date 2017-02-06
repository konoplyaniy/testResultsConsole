package hibernate.entities;

import hibernate.service.ClazzService;
import hibernate.service.GroupService;
import hibernate.service.TestService;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@Entity
@Table(name = "test", schema = "crazydomains")
public class TestEntity {
    private int testId;
    private String idValue;
    private String name;
    private Integer groupId;
    private int classId;
    private String className;
    private String groupName;

    public String getGroupName() {
        GroupService service = new GroupService();
        groupName = service.findById(this.getGroupId()).getName();
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getClassName() {
        ClazzService service = new ClazzService();
        className = service.findById(this.getClassId()).getName();
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public TestEntity() {
    }

    public static Builder newBuilder() {
        return new TestEntity().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setTestId(int testId) {
            TestEntity.this.setTestId(testId);
            return this;
        }

        public Builder setTestId(TestEntity test) {
            TestEntity.this.setTestId(test);
            return this;
        }

        public Builder setIdValue(String idValue) {
            TestEntity.this.setIdValue(idValue);
            return this;
        }

        public Builder setName(String name) {
            TestEntity.this.setName(name);
            return this;
        }

        public Builder setGroupId(Integer groupId) {
            TestEntity.this.setGroupId(groupId);
            return this;
        }

        public Builder setGroupId(String groupName) {
            TestEntity.this.setGroupId(groupName);
            return this;
        }

        public Builder setClassId(int classId) {
            TestEntity.this.setClassId(classId);
            return this;
        }

        public Builder setClassId(String className) {
            TestEntity.this.setClassId(className);
            return this;
        }

        public TestEntity build() {
            return TestEntity.this;
        }
    }

    @Id
    @Column(name = "test_id", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setTestId(TestEntity test) {
        TestService service = new TestService();
        ArrayList<TestEntity> testsInDB = (ArrayList<TestEntity>) service.findAll();
        if (testsInDB.contains(test)) {
            for (TestEntity testEntity : testsInDB) {
                if (test.equals(testEntity)) {
                    this.testId = testEntity.getTestId();
                }
            }
        } else {
            service.persist(test);
        }
    }

    @Basic
    @Column(name = "id_value", nullable = true, length = 45)
    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "group_id", nullable = true)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setGroupId(String groupName) {
        try {
            this.groupId = Integer.parseInt(groupName);
        } catch (NumberFormatException e) {
            GroupService service = new GroupService();
            GroupEntity group = service.findByName(groupName);
            if (group != null) {
                this.groupId = group.getGroupId();
            } else {
                group = new GroupEntity(groupName);
                service.persist(group);
                group = service.findByName(groupName);
                this.groupId = group.getGroupId();
            }
        }
    }

    @Basic
    @Column(name = "class_id", nullable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setClassId(String className) {
        try {
            this.classId = Integer.parseInt(className);
        } catch (NumberFormatException e) {
            ClazzService service = new ClazzService();
            ClazzEntity clazz = service.findByName(className);
            if (clazz != null) {
                this.classId = clazz.getClassId();
            } else {
                clazz = new ClazzEntity(className);
                service.persist(clazz);
                clazz = service.findByName(className);
                this.classId = clazz.getClassId();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (classId != that.classId) return false;
        if (idValue != null ? !idValue.equals(that.idValue) : that.idValue != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + (idValue != null ? idValue.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + classId;
        return result;
    }

    @Override
    public String toString() {
        return "id value: " + idValue + " name:" + name + " group ID:" + groupId + " class ID:" + classId;
    }
}
