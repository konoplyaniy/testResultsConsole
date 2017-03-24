package db_worker.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "test", schema = "crazydomains", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "class_id"})})

public class TestEntity implements Serializable {
    private int testId;
    private String idValue;
    private String name;
    private Integer groupId;
    private int classId;
    private Set<EventEntity> eventsByTestId = new HashSet<>(0);
    private GroupEntity groupByGroupId;
    private ClazzEntity clazzByClassId;

    public TestEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", nullable = false, unique = true)
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
    @Column(name = "name", nullable = false, length = 300)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (testId != that.testId) return false;
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

    @OneToMany(mappedBy = "testByTestId", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public Set<EventEntity> getEventsByTestId() {
        return eventsByTestId;
    }

    public void setEventsByTestId(Set<EventEntity> eventsByTestId) {
        this.eventsByTestId = eventsByTestId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "group_id", nullable = false)
    public GroupEntity getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(GroupEntity groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "class_id", nullable = false)
    public ClazzEntity getClazzByClassId() {
        return clazzByClassId;
    }

    public void setClazzByClassId(ClazzEntity clazzByClassId) {
        this.clazzByClassId = clazzByClassId;
    }
}
