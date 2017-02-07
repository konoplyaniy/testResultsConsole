package hibernate.entities;

import javax.persistence.*;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@Entity
@Table(name = "class", schema = "crazydomains")
public class ClazzEntity {
    private int classId;
    private String name;

    public ClazzEntity() {
    }

    @Id
    @Column(name = "class_id", nullable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 55)
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
    @Override
    public String toString() {
        return "id: " + classId + " name: " + name;
    }
}
