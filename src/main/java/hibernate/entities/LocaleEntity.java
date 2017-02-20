package hibernate.entities;

import javax.persistence.*;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
@Entity
@Table(name = "locale", schema = "crazydomains")
public class LocaleEntity {
    private int localeId;
    private String locale;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "locale_id", nullable = false)
    public int getLocaleId() {
        return localeId;
    }

    public void setLocaleId(int localeId) {
        this.localeId = localeId;
    }

    @Basic
    @Column(name = "locale", nullable = false, length = 7)
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocaleEntity that = (LocaleEntity) o;

        if (localeId != that.localeId) return false;
        if (locale != null ? !locale.equals(that.locale) : that.locale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = localeId;
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "id: " + localeId + " locale: " + locale;
    }
}
