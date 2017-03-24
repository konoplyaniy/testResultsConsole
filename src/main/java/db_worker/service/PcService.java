package db_worker.service;


import db_worker.dao.PcDao;
import db_worker.entities.PcEntity;

public class PcService extends BaseService{
    private static PcDao pcDao;

    public PcService() {
        pcDao = new PcDao(session);
    }

    public boolean exist(PcEntity pcEntity) {
        boolean isExist = false;
        pcDao.openCurrentSession();
        isExist = pcDao.exists(pcEntity.getName());
        pcDao.closeCurrentSession();
        return isExist;
    }

    public void persist(PcEntity entity) {
        pcDao.openCurrentSessionwithTransaction();
        pcDao.persist(entity);
        pcDao.closeCurrentSessionwithTransaction();
    }


    public PcEntity findById(Integer id) {
        pcDao.openCurrentSession();
        PcEntity pc = pcDao.findById(id);
        pcDao.closeCurrentSession();
        return pc;
    }

    public PcEntity findByName(String name) {
        pcDao.openCurrentSession();
        PcEntity pc = pcDao.findByPcName(name);
        pcDao.closeCurrentSession();
        return pc;
    }

    public PcDao pcDao() {
        return pcDao;
    }

}
