package web.exporter;

import hibernate.entities.ClazzEntity;
import hibernate.service.ClazzService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;


/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class ClassTableExporter {
    private List<ClazzEntity> classes;

    @ManagedProperty("#{clazzService}")
    private ClazzService clazzService;

    @PostConstruct
    public void init() {
        clazzService = new ClazzService();
        classes = clazzService.findAll();
    }

    public List<ClazzEntity> getClasses(){
        return classes;
    }

    public void setClazzService(ClazzService service){
        this.clazzService = service;
    }

}
