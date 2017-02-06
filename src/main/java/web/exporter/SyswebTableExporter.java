package web.exporter;

import hibernate.entities.SyswebEntity;
import hibernate.service.SyswebService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class SyswebTableExporter {
    private List<SyswebEntity> syswebs;

    @ManagedProperty("#{syswebService}")
    private SyswebService syswebService;

    @PostConstruct
    public void init() {
        syswebService = new SyswebService();
        syswebs = syswebService.findAll();
    }

    public List<SyswebEntity> getSyswebs(){
        return syswebs;
    }

    public void setSyswebService(SyswebService service){
        this.syswebService = service;
    }

}
