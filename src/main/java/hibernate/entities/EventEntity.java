package hibernate.entities;

import hibernate.service.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private int browserId;

    public EventEntity() {
    }

    public static Builder newBuilder() {
        return new EventEntity().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setData(Date data){
            EventEntity.this.setData(data);
            return this;
        }

//        public Builder setData(String date) {
//            EventEntity.this.setData(date);
//            return this;
//        }

        public Builder setEventId(int eventId) {
            EventEntity.this.setEventId(eventId);
            return this;
        }

        public Builder setTestId(int testId) {
            EventEntity.this.setTestId(testId);
            return this;
        }

        public Builder setTestId(TestEntity test) {
            EventEntity.this.setTestId(test);
            return this;
        }

        public Builder setLocaleId(int localeId) {
            EventEntity.this.setLocaleId(localeId);
            return this;
        }

        public Builder setLocaleId(String locale) {
            EventEntity.this.setLocaleId(locale);
            return this;
        }

        public Builder setSyswebId(int syswebId) {
            EventEntity.this.setSyswebId(syswebId);
            return this;
        }

        public Builder setSyswebId(String name) {
            EventEntity.this.setSyswebId(name);
            return this;
        }

        public Builder setPcId(int pcId) {
            EventEntity.this.setPcId(pcId);
            return this;
        }

        public Builder setPcId(String pcName, String os) {
            EventEntity.this.setPcId(pcName, os);
            return this;
        }

        public Builder setUrl(String url) {
            EventEntity.this.setUrl(url);
            return this;
        }

        public Builder setMessage(String message) {
            EventEntity.this.setMessage(message);
            return this;
        }

        public Builder setParams(String params) {
            EventEntity.this.setParams(params);
            return this;
        }

        public Builder setBrowserId(int browserId) {
            EventEntity.this.setBrowserId(browserId);
            return this;
        }

        public Builder setBrowserId(String browserName){
            EventEntity.this.setBrowserId(browserName);
            return this;
        }

        public EventEntity build() {
            return EventEntity.this;
        }
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

//    public void setData(String data) {
//        this.data = data;
//    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic
    @Column(name = "test_id", nullable = false)
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setTestId(TestEntity test) {
        TestService service = new TestService();
        List<TestEntity> testsDB = service.findAll();
        for (TestEntity testEntity : testsDB) {
            if (testEntity.equals(test)) {
                setTestId(testEntity.getTestId());
                break;
            }
        }
        service.persist(test);
    }

    @Basic
    @Column(name = "locale_id", nullable = false)
    public int getLocaleId() {
        return localeId;
    }

    public void setLocaleId(int localeId) {
        this.localeId = localeId;
    }

    public void setLocaleId(String locale) {
        try {
            this.localeId = Integer.parseInt(locale);
        } catch (NumberFormatException e) {
            LocaleService service = new LocaleService();
            LocaleEntity localeEntity = service.findByName(locale);
            if (localeEntity != null) {
                this.localeId = localeEntity.getLocaleId();
            } else {
                localeEntity = new LocaleEntity(locale);
                service.persist(localeEntity);
                localeEntity = service.findByName(locale);
                this.localeId = localeEntity.getLocaleId();
            }
        }
    }

    @Basic
    @Column(name = "sysweb_id", nullable = false)
    public int getSyswebId() {
        return syswebId;
    }

    public void setSyswebId(int syswebId) {
        this.syswebId = syswebId;
    }

    public void setSyswebId(String name) {
        try {
            this.syswebId = Integer.parseInt(name);
        } catch (NumberFormatException e) {
            SyswebService service = new SyswebService();
            SyswebEntity sysweb = service.findByName(name);
            if (sysweb != null) {
                this.syswebId = sysweb.getSyswebId();
            } else {
                sysweb = new SyswebEntity(name);
                service.persist(sysweb);
                sysweb = service.findByName(name);
                this.pcId = sysweb.getSyswebId();
            }
        }
    }

    @Basic
    @Column(name = "pc_id", nullable = false)
    public int getPcId() {
        return pcId;
    }

    public void setPcId(int pcId) {
        this.pcId = pcId;
    }

    public void setPcId(String pcName, String os) {
        PcService service = new PcService();
        PcEntity pcEntity = service.findByName(pcName);
        if (pcEntity != null) {
            this.pcId = pcEntity.getPcId();
        } else {
            pcEntity = new PcEntity(pcName, os);
            service.persist(pcEntity);
            pcEntity = service.findByName(pcName);
            this.pcId = pcEntity.getPcId();
        }
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
    @Column(name = "browser_id", nullable = false)
    public int getBrowserId() {
        return browserId;
    }

    public void setBrowserId(int browserId) {
        this.browserId = browserId;
    }

    public void setBrowserId(String browserName) {
        BrowserService service = new BrowserService();
        BrowserEntity browser = service.findByName(browserName);
        if (browser != null) {
            this.browserId = browser.getBrowserId();
        } else {
            browser = new BrowserEntity(browserName);
            service.persist(browser);
            browser = service.findByName(browserName);
            this.browserId = browser.getBrowserId();
        }
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
}
