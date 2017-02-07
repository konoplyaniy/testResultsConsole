package hibernate.entities;

import javax.persistence.*;
import java.util.Collection;

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
//    private String className;
//    private String groupName;
    private Collection<EventEntity> eventsByTestId;
    private GroupEntity groupByGroupId;
    private ClazzEntity clazzByClassId;

    public TestEntity() {
    }

    @Id
    @Column(name = "test_id", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
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
    @Column(name = "group_id", nullable = true, insertable = false, updatable = false)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "class_id", nullable = false, insertable = false, updatable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

//    @Basic
//    @Column(name = "className", nullable = true, length = 255)
//    public String getClassName() {
//        return className;
//    }
//
//    public void setClassName(String className) {
//        this.className = className;
//    }
//
//    @Basic
//    @Column(name = "groupName", nullable = true, length = 255)
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity testTable = (TestEntity) o;

        if (testId != testTable.testId) return false;
        if (classId != testTable.classId) return false;
        if (idValue != null ? !idValue.equals(testTable.idValue) : testTable.idValue != null) return false;
        if (name != null ? !name.equals(testTable.name) : testTable.name != null) return false;
        if (groupId != null ? !groupId.equals(testTable.groupId) : testTable.groupId != null) return false;
//        if (className != null ? !className.equals(testTable.className) : testTable.className != null) return false;
//        if (groupName != null ? !groupName.equals(testTable.groupName) : testTable.groupName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + (idValue != null ? idValue.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + classId;
//        result = 31 * result + (className != null ? className.hashCode() : 0);
//        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return testId + " " + name + " class: " + getClazzByClassId().getName() + " group: " + getGroupByGroupId().getName();
    }

    @OneToMany(mappedBy = "testByTestId")
    public Collection<EventEntity> getEventsByTestId() {
        return eventsByTestId;
    }

    public void setEventsByTestId(Collection<EventEntity> eventsByTestId) {
        this.eventsByTestId = eventsByTestId;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    public GroupEntity getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(GroupEntity groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    public ClazzEntity getClazzByClassId() {
        return clazzByClassId;
    }

    public void setClazzByClassId(ClazzEntity clazzByClassId) {
        this.clazzByClassId = clazzByClassId;
    }
}
