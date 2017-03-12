package web.exporter;

import hibernate.entities.BrowserEntity;
import hibernate.service.BrowserService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class BrowserTableExporter {
    private List<BrowserEntity> browsers;
    private int browserId = 0;
    BrowserEntity newBrowser = new BrowserEntity();
    BrowserEntity browser = new BrowserEntity();
    BrowserService service = new BrowserService();

    public void addNewBrowser() {
        if (newBrowser != null) {
            if (service.findByName(newBrowser.getBrowser()) != null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information",
                        "Browser: " + newBrowser.getBrowser() + " is already exist");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                service.persist(newBrowser);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information",
                        "Browser successfully saved.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        }
        newBrowser = new BrowserEntity();
    }

    public void updateBrowser() {
        String name = browser.getBrowser();
        if (service.findById(browserId) == null || browserId == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Information",
                    "Can't find browser with id: " + browserId);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", name);
            RequestContext.getCurrentInstance().showMessageInDialog(message1);
            String browserName = browser.getBrowser();
            browser = service.findById(browserId);
            browser.setBrowser(browserName);
            service.update(browser);
            System.out.println("Browser successfully updated.");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Information",
                    "Browser successfully updated.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        browser = new BrowserEntity();
    }

    public void deleteBrowser() {
        String name = browser.getBrowser();
        if (service.findById(browserId) == null || browserId == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Information",
                    "Can't find browser with id: " + browserId);
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } else {
            FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", name);
            RequestContext.getCurrentInstance().showMessageInDialog(message1);
            service.delete(browserId);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Information",
                    "Browser successfully deleted.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        browser = new BrowserEntity();
    }

    @ManagedProperty("#{browserService}")
    private BrowserService browserService;

    @PostConstruct
    public void init() {
        browserService = new BrowserService();
        browsers = browserService.findAll();
    }

    public List<BrowserEntity> getBrowsers() {
        return browsers;
    }

    public void setBrowserService(BrowserService service) {
        this.browserService = service;
    }

    public int getBrowserId() {
        return browserId;
    }

    public void setBrowserId(int browserId) {
        this.browserId = browserId;
    }

    public BrowserEntity getNewBrowser() {
        return newBrowser;
    }

    public void setNewBrowser(BrowserEntity newBrowser) {
        this.newBrowser = newBrowser;
    }

    public BrowserEntity getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserEntity browser) {
        this.browser = browser;
    }


}

