package hibernate.entities;

import javax.persistence.*;

/**
 * Browser table
 * Columns:
 * browser_id
 * browser
 */
@Entity
@Table(name = "browser", schema = "crazydomains")
public class BrowserEntity {
    private int browserId;
    private String browser;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "browser_id", nullable = false)
    public int getBrowserId() {
        return browserId;
    }

    public void setBrowserId(int browserId) {
        this.browserId = browserId;
    }

    @Basic
    @Column(name = "browser", nullable = false, length = 45)
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
}
