package web.Views;


import db_worker.HibernateUtil;
import db_worker.dao.EventDao;
import db_worker.entities.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

@ManagedBean
@SessionScoped
public class SearchView implements Serializable {
    private Date startDate;
    private Date endDate;
    private String testName = "";
    private String sysweb = "";
    private String locale = "";
    private String clazzName = "";
    private boolean visibleResultsForm = false;
    private HashSet<String> clazzNames;
    private HashSet<String> testNames;
    private HashSet<String> locales;
    private HashSet<String> syswebs;
    private ArrayList<EventEntity> resultEventsList;

    public static Session session;

    static {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @PostConstruct
    public void initDropDowns() {
        initDropdownsData();
    }

    public static HashSet<String> getTestNamesList() throws HibernateException {
        HashSet<String> testNames = new HashSet<>();
        ArrayList<TestEntity> testEntities = new ArrayList<>(session.createQuery("from TestEntity").list());
        testEntities.forEach(testEntity -> testNames.add(testEntity.getName()));
        return testNames;
    }

    public static HashSet<String> getClassNamesList() throws HibernateException {
        HashSet<String> clazzes = new HashSet<>();
        ArrayList<ClazzEntity> clazzEntities = new ArrayList<>(session.createQuery("from ClazzEntity ").list());
        clazzEntities.forEach(clazzEntity -> clazzes.add(clazzEntity.getName()));
        return clazzes;
    }

    public static HashSet<String> getLocalesList() throws HibernateException {
        HashSet<String> locales = new HashSet<>();
        ArrayList<LocaleEntity> localeEntities = new ArrayList<>(session.createQuery("from LocaleEntity ").list());
        localeEntities.forEach(localeEntity -> locales.add(localeEntity.getLocale()));
        return locales;
    }

    public static HashSet<String> getSyswebsList() throws HibernateException {
        HashSet<String> syswebs = new HashSet<>();
        ArrayList<SyswebEntity> syswebEntities = new ArrayList<>(session.createQuery("from SyswebEntity ").list());
        syswebEntities.forEach(syswebEntity -> syswebs.add(syswebEntity.getName()));
        return syswebs;
    }

    private void initDropdownsData() {
        /*HashSet<String> testNames = new HashSet<>();*/
        /*HashSet<String> syswebs = new HashSet<>();*/
        /*HashSet<String> locales = new HashSet<>();*/
        /*HashSet<String> clazzes = new HashSet<>();*/

        /*ArrayList<TestEntity> testEntities = new ArrayList<>(session.createQuery("from TestEntity").list());*/
        /*ArrayList<SyswebEntity> syswebEntities = new ArrayList<>(session.createQuery("from SyswebEntity ").list());*/
        /*ArrayList<LocaleEntity> localeEntities = new ArrayList<>(session.createQuery("from LocaleEntity ").list());*/
        /*ArrayList<ClazzEntity> clazzEntities = new ArrayList<>(session.createQuery("from ClazzEntity ").list());*/

        /*testEntities.forEach(testEntity -> testNames.add(testEntity.getName()));*/
        /*syswebEntities.forEach(syswebEntity -> syswebs.add(syswebEntity.getName()));*/
        /*localeEntities.forEach(localeEntity -> locales.add(localeEntity.getLocale()));*/
        /*clazzEntities.forEach(clazzEntity -> clazzes.add(clazzEntity.getName()));*/

        setTestNames(getTestNamesList());
        setLocales(getLocalesList());
        setSyswebs(getSyswebsList());
        setClazzNames(getClassNamesList());
    }

    public void clickSearchButton() {
        if (getStartDate() != null && getEndDate() != null && getStartDate().before(getEndDate())) {
            setVisibleResultsForm(true);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", " Start Date must be earlier then End Date"));
        }
    }

    public ArrayList<EventEntity> getResultEventsList() {
        /*Transaction tr = session.beginTransaction();*/
        resultEventsList = new EventDao(session).findBySelected(getClazzName(), getTestName(), getSysweb(), getLocale(), getStartDate(), getEndDate());
        /*try {
            tr.commit();
        } catch (Exception e) {
            System.out.println("occur error in ArrayList<EventEntity> getResultEventsList() class SearchView");
            e.printStackTrace();
        }*/
        return resultEventsList;
    }

    public void setResultEventsList(ArrayList<EventEntity> resultEventsList) {
        this.resultEventsList = resultEventsList;
    }

    public boolean isVisibleResultsForm() {
        return visibleResultsForm;
    }

    public void setVisibleResultsForm(boolean visibleResultsForm) {
        this.visibleResultsForm = visibleResultsForm;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Date getStartDate() {
        if (startDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getDefault());
            formatter.format(startDate);
        }
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        if (endDate != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getDefault());
            formatter.format(endDate);
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSysweb() {
        return sysweb;
    }

    public void setSysweb(String sysweb) {
        this.sysweb = sysweb;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public HashSet<String> getClazzNames() {
        return clazzNames;
    }

    public void setClazzNames(HashSet<String> clazzNames) {
        this.clazzNames = clazzNames;
    }

    public HashSet<String> getTestNames() {
        return testNames;
    }

    public void setTestNames(HashSet<String> testNames) {
        this.testNames = testNames;
    }

    public HashSet<String> getLocales() {
        return locales;
    }

    public void setLocales(HashSet<String> locales) {
        this.locales = locales;
    }

    public HashSet<String> getSyswebs() {
        return syswebs;
    }

    public void setSyswebs(HashSet<String> syswebs) {
        this.syswebs = syswebs;
    }
}
