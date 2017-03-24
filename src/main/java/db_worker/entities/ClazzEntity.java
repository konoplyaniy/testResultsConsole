package db_worker.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "class", schema = "crazydomains")
public class ClazzEntity implements Serializable {
    private int classId;
    private String name;
    private Set<TestEntity> testsByClassId  = new HashSet<>(0);

    public ClazzEntity(){}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "class_id", nullable = false, unique = true)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

        ClazzEntity that = (ClazzEntity) o;

        if (classId != that.classId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clazzByClassId")
    public Set<TestEntity> getTestsByClassId() {
        return testsByClassId;
    }

    public void setTestsByClassId(Set<TestEntity> testsByClassId) {
        this.testsByClassId = testsByClassId;
    }
}
