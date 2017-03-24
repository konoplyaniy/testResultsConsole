package db_worker.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "group", schema = "crazydomains")
public class GroupEntity implements Serializable {
    private int groupId;
    private String name;
    private Set<TestEntity> testsByGroupId  = new HashSet<>(0);

    public GroupEntity(){}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false, unique = true)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(name = "name", nullable = false, length = 100, unique = true)
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

        GroupEntity that = (GroupEntity) o;

        if (groupId != that.groupId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "groupByGroupId", fetch = FetchType.LAZY)
    public Set<TestEntity> getTestsByGroupId() {
        return testsByGroupId;
    }

    public void setTestsByGroupId(Set<TestEntity> testsByGroupId) {
        this.testsByGroupId = testsByGroupId;
    }
}
