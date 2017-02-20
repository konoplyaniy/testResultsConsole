package hibernate.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@Entity
@Table(name = "group", schema = "crazydomains")
public class GroupEntity implements Serializable {
    private int groupId;
    private String name;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
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

    @Override
    public String toString() {
        return "id: " + groupId + " name: " + name;
    }
}
