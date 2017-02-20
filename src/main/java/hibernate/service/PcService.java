package hibernate.service;

import hibernate.dao.PcDao;
import hibernate.entities.PcEntity;
import hibernate.utils.DBLogger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Sergiy.K on 26-Jan-17.
 */
@ManagedBean(name = "pcService")
@ApplicationScoped
public class PcService {
    private static PcDao pcDao;

    public PcService() {
        pcDao = new PcDao();
    }

    public boolean exist(PcEntity pcEntity){
        boolean isExist = false;
        pcDao.openCurrentSession();
        isExist = pcDao.exists(pcEntity.getName());
        pcDao.closeCurrentSession();
        return isExist;
    }

    public void persist(PcEntity entity) {
        pcDao.openCurrentSessionwithTransaction();
        if (!pcDao.exists(entity.getName())) {
            pcDao.persist(entity);
            DBLogger.info("Add new PC with name [" + entity.getName() + "]");
        }
        pcDao.closeCurrentSessionwithTransaction();
    }

    public void update(PcEntity entity) {
        pcDao.openCurrentSessionwithTransaction();
        pcDao.update(entity);
        pcDao.closeCurrentSessionwithTransaction();
    }

    public PcEntity findById(Integer id) {
        DBLogger.info("Search PC id [" + id + "]");
        pcDao.openCurrentSession();
        PcEntity pc = pcDao.findById(id);
        pcDao.closeCurrentSession();
        DBLogger.info("Find PC [" + pc + "]");
        return pc;
    }

    public PcEntity findByName(String name) {
        DBLogger.info("Search PC name [" + name + "]");
        pcDao.openCurrentSession();
        PcEntity pc = pcDao.findByPcName(name);
        pcDao.closeCurrentSession();
        DBLogger.info("Find [" + pc + "]");
        return pc;
    }

    public void delete(Integer id) {
        pcDao.openCurrentSessionwithTransaction();
        PcEntity pc = pcDao.findById(id);
        pcDao.delete(pc);
        pcDao.closeCurrentSessionwithTransaction();
    }

    public List<PcEntity> findAll() {
        pcDao.openCurrentSession();
        List<PcEntity> pcList = pcDao.findAll();
        pcDao.closeCurrentSession();
        return pcList;
    }

    public int getRowsCount() {
        pcDao.openCurrentSession();
        List<PcEntity> pcList = pcDao.findAll();
        pcDao.closeCurrentSession();
        return pcList.size();
    }

    public void deleteAll() {
        pcDao.openCurrentSessionwithTransaction();
        pcDao.deleteAll();
        pcDao.closeCurrentSessionwithTransaction();
    }

    public PcDao pcDao() {
        return pcDao;
    }

}
