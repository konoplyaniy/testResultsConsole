package hibernate.entities;

import javax.persistence.*;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@Entity
@Table(name = "sysweb", schema = "crazydomains")
public class SyswebEntity {
    private int syswebId;
    private String name;

    @Id
    @Column(name = "sysweb_id", nullable = false)
    public int getSyswebId() {
        return syswebId;
    }

    public void setSyswebId(int syswebId) {
        this.syswebId = syswebId;
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

        SyswebEntity that = (SyswebEntity) o;

        if (syswebId != that.syswebId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = syswebId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "id: " + syswebId + " name: " + name;
    }
}
