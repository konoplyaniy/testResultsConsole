package web.exporter;

import hibernate.entities.LocaleEntity;
import hibernate.service.LocaleService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class LocaleTableExporter {
    private List<LocaleEntity> locales;

    @ManagedProperty("#{localeService}")
    private LocaleService localeService;

    @PostConstruct
    public void init() {
        localeService = new LocaleService();
        locales = localeService.findAll();
    }

    public List<LocaleEntity> getLocales(){
        return locales;
    }

    public void setLocaleService(LocaleService service){
        this.localeService = service;
    }
}
