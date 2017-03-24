package db_worker.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "browser", schema = "crazydomains",uniqueConstraints = {
        @UniqueConstraint(columnNames = "browser_id"),
        @UniqueConstraint(columnNames = "browser") })
public class BrowserEntity implements Serializable {
    private int browserId;
    private String browser;
    private Set<EventEntity> eventsByBrowserId = new HashSet<>(0);

    public BrowserEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "browser_id", nullable = false, unique = true)
    public int getBrowserId() {
        return browserId;
    }

    public void setBrowserId(int browserId) {
        this.browserId = browserId;
    }

    @Basic
    @Column(name = "browser", nullable = false, length = 100, unique = true)
    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrowserEntity that = (BrowserEntity) o;

        if (browserId != that.browserId) return false;
        if (browser != null ? !browser.equals(that.browser) : that.browser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = browserId;
        result = 31 * result + (browser != null ? browser.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "browserByBrowserId", fetch = FetchType.LAZY)
    public Set<EventEntity> getEventsByBrowserId() {
        return eventsByBrowserId;
    }

    public void setEventsByBrowserId(Set<EventEntity> eventsByBrowserId) {
        this.eventsByBrowserId = eventsByBrowserId;
    }
}
