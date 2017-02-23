package hibernate.entities;

import hibernate.service.ClazzService;
import hibernate.service.GroupService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@Entity
@Table(name = "test", schema = "crazydomains")
public class TestEntity implements Serializable {
    private int testId;
    private String idValue;
    private String name;
    private Integer groupId;
    private int classId;
    private Collection<EventEntity> eventsByTestId;
    private GroupEntity groupByGroupId;
    private ClazzEntity clazzByClassId;

    public TestEntity() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
        return testId + " " + name + " class: " + getClazzByClassId().getName() + " group: " + getGroupByGroupId().getName();
    }

    @OneToMany(mappedBy = "testByTestId")
    public Collection<EventEntity> getEventsByTestId() {
        return eventsByTestId;
    }

    public void setEventsByTestId(Collection<EventEntity> eventsByTestId) {
        this.eventsByTestId = eventsByTestId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false)
    public GroupEntity getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(GroupEntity groupByGroupId) {
//        GroupService service = new GroupService();
//        if (service.exist(groupByGroupId)){
//            this.groupByGroupId = service.findByName(groupByGroupId.getName());
//        }else
        this.groupByGroupId = groupByGroupId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    public ClazzEntity getClazzByClassId() {
        return clazzByClassId;
    }

    public void setClazzByClassId(ClazzEntity clazzByClassId) {
//        ClazzService service = new ClazzService();
//        if (service.exist(clazzByClassId)){
//            this.clazzByClassId = service.findByName(clazzByClassId.getName());
//        }else
        this.clazzByClassId = clazzByClassId;
    }
}
