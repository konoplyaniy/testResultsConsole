package db_worker.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "event", schema = "crazydomains")
public class EventEntity implements Serializable {
    private int eventId;
    private Date data;
    private int testId;
    private int localeId;
    private int syswebId;
    private int pcId;
    private String url;
    private String message;
    private String params;
    private int browserId;
    private int checked;
    private String website;
    private String emailId;
    private String causedBy;
    private TestEntity testByTestId;
    private LocaleEntity localeByLocaleId;
    private SyswebEntity syswebBySyswebId;
    private PcEntity pcByPcId;
    private BrowserEntity browserByBrowserId;

    public EventEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "data", nullable = false)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 1000)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 100)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "params", nullable = true, length = 1000)
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Basic
    @Column(name = "checked", nullable = false)
    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    @Basic
    @Column(name = "website", nullable = true, length = 100)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Column(name = "email_id", nullable = true, length = 45)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Basic
    @Column(name = "caused_by", nullable = true, length = 1000)
    public String getCausedBy() {
        return causedBy;
    }

    public void setCausedBy(String causedBy) {
        this.causedBy = causedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        if (eventId != that.eventId) return false;
        if (testId != that.testId) return false;
        if (localeId != that.localeId) return false;
        if (syswebId != that.syswebId) return false;
        if (pcId != that.pcId) return false;
        if (browserId != that.browserId) return false;
        if (checked != that.checked) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        if (emailId != null ? !emailId.equals(that.emailId) : that.emailId != null) return false;
        if (causedBy != null ? !causedBy.equals(that.causedBy) : that.causedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + testId;
        result = 31 * result + localeId;
        result = 31 * result + syswebId;
        result = 31 * result + pcId;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + browserId;
        result = 31 * result + checked;
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
        result = 31 * result + (causedBy != null ? causedBy.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    public TestEntity getTestByTestId() {
        return testByTestId;
    }

    public void setTestByTestId(TestEntity testByTestId) {
        this.testByTestId = testByTestId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locale_id", nullable = false)
    public LocaleEntity getLocaleByLocaleId() {
        return localeByLocaleId;
    }

    public void setLocaleByLocaleId(LocaleEntity localeByLocaleId) {
        this.localeByLocaleId = localeByLocaleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sysweb_id", nullable = false)
    public SyswebEntity getSyswebBySyswebId() {
        return syswebBySyswebId;
    }

    public void setSyswebBySyswebId(SyswebEntity syswebBySyswebId) {
        this.syswebBySyswebId = syswebBySyswebId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id", nullable = false)
    public PcEntity getPcByPcId() {
        return pcByPcId;
    }

    public void setPcByPcId(PcEntity pcByPcId) {
        this.pcByPcId = pcByPcId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "browser_id", nullable = false)
    public BrowserEntity getBrowserByBrowserId() {
        return browserByBrowserId;
    }

    public void setBrowserByBrowserId(BrowserEntity browserByBrowserId) {
        this.browserByBrowserId = browserByBrowserId;
    }
}
