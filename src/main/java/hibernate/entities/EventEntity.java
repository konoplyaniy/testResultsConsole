package hibernate.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sergiy.K on 31-Jan-17.
 */
@Entity
@Table(name = "event", schema = "crazydomains")
public class EventEntity {
    private int eventId;
    private Date data;
    private int testId;
    private int localeId;
    private int syswebId;
    private int pcId;
    private String url;
    private String message;
    private String params;
    private String website;
    private int browserId;
    private TestEntity testByTestId;
    private LocaleEntity localeByLocaleId;
    private SyswebEntity syswebBySyswebId;
    private PcEntity pcByPcId;
    private BrowserEntity browserByBrowserId;
    private int checked;

    public EventEntity() {
    }

    @Id
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
    @Column(name = "test_id", nullable = false, insertable = false, updatable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "locale_id", nullable = false, insertable = false, updatable = false)
    public int getLocaleId() {
        return localeId;
    }

    public void setLocaleId(int localeId) {
        this.localeId = localeId;
    }

    @Basic
    @Column(name = "sysweb_id", nullable = false, insertable = false, updatable = false)
    public int getSyswebId() {
        return syswebId;
    }

    public void setSyswebId(int syswebId) {
        this.syswebId = syswebId;
    }

    @Basic
    @Column(name = "pc_id", nullable = false, insertable = false, updatable = false)
    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 300)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "params", nullable = true, length = 200)
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Basic
    @Column(name = "browser_id", nullable = false, insertable = false, updatable = false)
    public int getBrowserId() {
        return browserId;
    }

    public void setBrowserId(int browserId) {
        this.browserId = browserId;
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
    @Column(name = "website", nullable = false)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;

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
        return result;
    }

    @Override
    public String toString() {
        return eventId + "\n" +
                data + "\n"
                + " GROUP: " + testByTestId.getGroupByGroupId().getName() + "\n"
                + " GROUP ID: " + testByTestId.getGroupByGroupId().getGroupId() + "\n"
                + " CLASS: " + testByTestId.getClazzByClassId().getName() + "\n" +
                " TEST NAME: " + testByTestId.getName() + "\n"
                + " LOCALE: " + localeByLocaleId.getLocale() + "\n"
                + " SYSWEB: " + syswebBySyswebId.getName() + "\n"
                + " PC NAME: " + pcByPcId.getName() + "\n"
                + " PC OS: " + pcByPcId.getOs() + "\n"
                + " BROWSER: " + browserByBrowserId.getBrowser();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id", referencedColumnName = "test_id", nullable = false)
    public TestEntity getTestByTestId() {
        return testByTestId;
    }

    public void setTestByTestId(TestEntity testByTestId) {
        this.testByTestId = testByTestId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locale_id", referencedColumnName = "locale_id", nullable = false)
    public LocaleEntity getLocaleByLocaleId() {
        return localeByLocaleId;
    }

    public void setLocaleByLocaleId(LocaleEntity localeByLocaleId) {
//        LocaleService service = new LocaleService();
//        if (service.exist(localeByLocaleId)) {
//            this.localeByLocaleId = service.findByName(localeByLocaleId.getLocale());
//        }else
        this.localeByLocaleId = localeByLocaleId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sysweb_id", referencedColumnName = "sysweb_id", nullable = false)
    public SyswebEntity getSyswebBySyswebId() {
        return syswebBySyswebId;
    }

    public void setSyswebBySyswebId(SyswebEntity syswebBySyswebId) {
//        SyswebService service = new SyswebService();
//        if (service.exist(syswebBySyswebId)) {
//            this.syswebBySyswebId = service.findByName(syswebBySyswebId.getName());
//        } else
        this.syswebBySyswebId = syswebBySyswebId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pc_id", referencedColumnName = "pc_id", nullable = false)
    public PcEntity getPcByPcId() {
        return pcByPcId;
    }

    public void setPcByPcId(PcEntity pcByPcId) {
        this.pcByPcId = pcByPcId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "browser_id", referencedColumnName = "browser_id", nullable = false)
    public BrowserEntity getBrowserByBrowserId() {
        return browserByBrowserId;
    }

    public void setBrowserByBrowserId(BrowserEntity browserByBrowserId) {
        this.browserByBrowserId = browserByBrowserId;
    }
}
