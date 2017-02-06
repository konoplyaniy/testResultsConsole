package hibernate.entities;

import javax.persistence.*;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@Entity
@Table(name = "pc", schema = "crazydomains")
public class PcEntity {
    private int pcId;
    private String name;
    private String os;

    public PcEntity() {
    }

    public PcEntity(String name, String os) {
        this.name = name;
        this.os = os;
    }

    @Id
    @Column(name = "pc_id", nullable = false)
    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
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
    @Column(name = "os", nullable = false, length = 45)
    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PcEntity pcEntity = (PcEntity) o;

        if (name != null ? !name.equals(pcEntity.name) : pcEntity.name != null) return false;
        if (os != null ? !os.equals(pcEntity.os) : pcEntity.os != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pcId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (os != null ? os.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "id:" + pcId + " name: " + name + " OS:" + os;
    }
}