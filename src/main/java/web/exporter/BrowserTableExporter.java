package web.exporter;

import hibernate.entities.BrowserEntity;
import hibernate.service.BrowserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class BrowserTableExporter {
    private List<BrowserEntity> browsers;

    @ManagedProperty("#{browserService}")
    private BrowserService browserService;

    @PostConstruct
    public void init() {
        browserService = new BrowserService();
        browsers = browserService.findAll();
    }

    public List<BrowserEntity> getBrowsers(){
        return browsers;
    }

    public void setBrowserService(BrowserService service){
        this.browserService = service;
    }

}

