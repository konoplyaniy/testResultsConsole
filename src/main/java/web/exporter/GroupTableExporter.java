package web.exporter;

import hibernate.entities.GroupEntity;
import hibernate.service.GroupService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

/**
 * Created by Sergiy.K on 03-Feb-17.
 */
@ManagedBean
public class GroupTableExporter {
    private List<GroupEntity> groups;

    @ManagedProperty("#{groupService}")
    private GroupService groupService;

    @PostConstruct
    public void init() {
        groupService = new GroupService();
        groups = groupService.findAll();
    }

    public List<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroupService(GroupService service) {
        this.groupService = service;
    }
}

