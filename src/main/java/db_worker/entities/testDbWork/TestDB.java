package db_worker.entities.testDbWork;

import db_worker.dao.*;
import db_worker.entities.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Sergiy.K on 15-Mar-17.
 */
public class TestDB {

  /*  public static void testAdd() {
        Session session = BaseDao.getSessionFactory().openSession();
        session.beginTransaction();

        ClazzEntity clazz = new ClazzEntity();
        clazz.setName("TEST_CLASS");

        GroupEntity group = new GroupEntity();

        if ("TEST_GROUP" != null && !"TEST_GROUP".equals("")) {
            group.setName("TEST_GROUP".replace("[", "").replace("]", ""));
        } else {
            group.setName("NO GROUP");
        }

        TestEntity test = new TestEntity();
        test.setName("testName");

        if ("TEST_ID" != null && "TEST_ID" != "") {
            test.setIdValue("TEST_ID");
        }

        if (new ClazzDao(session).exists(clazz.getName())) {
            clazz = new ClazzDao(session).findByName(clazz.getName());
        } else session.saveOrUpdate(clazz);

        if (new GroupDao(session).exists(group.getName())) {
            group = new GroupDao(session).findByName(group.getName());
        } else session.saveOrUpdate(group);

        test.setGroupByGroupId(group);
        test.setClazzByClassId(clazz);
        group.getTestsByGroupId().add(test);
        clazz.getTestsByClassId().add(test);

        session.saveOrUpdate(group);

        if (new TestDao(session).exists(clazz.getName(), test.getName())) {
            test = new TestDao(session).findByTestNameClassName(test.getName(), clazz.getName());
        } else {
            session.saveOrUpdate(test);
        }

        BrowserEntity browser = new BrowserEntity();
        browser.setBrowser("BROWSER_VERSION");

        if (new BrowserDao(session).exists(browser.getBrowser())) {
            browser = new BrowserDao(session).findByName(browser.getBrowser());
        } else {
            session.save(browser);
        }


        LocaleEntity locale = new LocaleEntity();
        locale.setLocale("TEST_LOCALE".replace("[", "").replace("]", ""));
        if (new LocaleDao(session).exists(locale.getLocale())) {
            locale = new LocaleDao(session).findByLocale(locale.getLocale());
        } else {
            session.saveOrUpdate(locale);
        }

        SyswebEntity sysweb = new SyswebEntity();
        String syswebUrl = "TEST_SYSWEB".replace("[", "").replace("]", "").replace("ANSWER GET FROM :", "").replaceAll(" ", "");
        if (syswebUrl.equals("") || syswebUrl.equals(" ")) {
            syswebUrl = "NO SYSWEB";
        }
        sysweb.setName(syswebUrl);

        if (new SyswebDao(session).exists(syswebUrl)) {
            sysweb = new SyswebDao(session).findByName(syswebUrl);
        } else {
            session.saveOrUpdate(sysweb);
        }

        PcEntity pc = new PcEntity();
        pc.setName("PC_NAME");
        pc.setOs("OS");

        if (new PcDao(session).exists(pc.getName())) {
            pc = new PcDao(session).findByPcName(pc.getName());
        } else {
            session.save(pc);
        }

        session.saveOrUpdate(pc);

        EventEntity event = new EventEntity();
        event.setData(new Date());
        event.setParams("TEST_PARAMETERS");
        event.setUrl("driver.getCurrentUrl()");

        event.setTestByTestId(test);

        event.setLocaleByLocaleId(locale);
        event.setBrowserByBrowserId(browser);
        event.setPcByPcId(pc);
        event.setSyswebBySyswebId(sysweb);

        locale.getEventsByLocaleId().add(event);
        sysweb.getEventsBySyswebId().add(event);


        pc.getEventsByPcId().add(event);
        sysweb.getEventsBySyswebId().add(event);

        test.getEventsByTestId().add(event);


        session.getTransaction().commit();
        session.close();

    }*/

    public static void testTable() {

//        Session session = BaseDao.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        ClazzEntity clazzEntity = new ClazzEntity();
//        clazzEntity.setName("TEST_CLASS1");
//
//        TestEntity testEntity = new TestEntity();
//        testEntity.setName("testName1");

//        ClazzEntity clazzEntity1 = testEntity.getClazzByClassId();
//        System.out.println(clazzEntity1.getName() + " | " + clazzEntity1.getClassId());

//        TestDao testDao = new TestDao(session);
//        testEntity = testDao.findByTestNameClassName("testName1", "TEST_CLASS1");
//        System.out.println("test Name: " + testEntity.getName() + " | class:" + testEntity.getClazzByClassId().getName() + "| group: " + testEntity.getGroupByGroupId().getName());
//
//        System.out.println(testDao.exists("TEST_CLASS1", "testName1"));

    }

    public static void main(String[] args) {
     /*   EventService eventService = new EventService();
        System.out.println(eventService.findByCurrentDayEvents().size());*/

     /*   System.out.println(new EventService().findbyId(1).getUrl());


        Session session = BaseDao.getSessionFactory().openSession();

        session.beginTransaction();
        EventDao dao = new EventDao(session);
        EventEntity eventEntity = dao.findById(1);
        eventEntity.setSteps("1) Go to https://www.crazydomains.in/domain-names/search/\n" +
                "2) Input \"myfirstbuyincrazy.bzh\" in [DOMAIN SEARCH FIELD]\n" +
                "3) Click on [SEARCH BUTTON] with text \"Search\"\n" +
                "REDIRECT TO https://www.crazydomains.in/domain-names/search/?token=013d3a6c3ca9d23ed2859e137db039d5&domain=myfirstbuyincrazy.bzh ANSWER GET FROM : [SYSWEB3.UK.SYRAHOST.COM] \n" +
                "\n" +
                "What went wrong? (Screenshot(s) in attachment): \n" +
                "WebDriver Wait for element timeout Exception expected [true] but found [false]\n");

        *//*eventEntity.setCausedBy(newValue.toString());*//*
        dao.update(eventEntity);
        session.getTransaction().commit();
        session.close();
        System.out.println("end");*/
//        long start = System.currentTimeMillis();
//        testAdd();
//        System.out.println(start - System.currentTimeMillis());


//        Session session = BaseDao.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        EventDao dao = new EventDao(session);
//        EventEntity entity = dao.findById(57);
//        System.out.println(entity.getChecked());
//        entity.setChecked(0);
//        dao.update(entity);
//        System.out.println(entity.getChecked());
//        session.getTransaction().commit();
//        session.close();


//        EventService eventService = new EventService();
//        ArrayList events = eventService.findByCurrentDayEvents();
//        System.out.println(events.size());
//        System.out.println(events);

//        testTable();

//        Session session = BaseDao.getSessionFactory().openSession();
//        session.beginTransaction();
//        Session session = testDao.openCurrentSessionwithTransaction();
//
//
//        ClazzEntity clazz = new ClazzEntity();
//        clazz.setName("TEST_CLASS");
//
//        GroupEntity group = new GroupEntity();
//
//        if ("TEST_GROUP" != null && !"TEST_GROUP".equals("")) {
//            group.setName("TEST_GROUP".replace("[", "").replace("]", ""));
//        } else {
//            group.setName("NO GROUP");
//        }
//
//        TestEntity test = new TestEntity();
//        test.setName("testName");
//
//        if ("TEST_ID" != null && "TEST_ID" != "") {
//            test.setIdValue("TEST_ID");
//        }
//
//        group.getTestsByGroupId().add(test);
//        clazz.getTestsByClassId().add(test);
//
//        test.setGroupByGroupId(group);
//        test.setClazzByClassId(clazz);
//
//        /*session.saveOrUpdate(group);
//        session.flush();
//        session.saveOrUpdate(clazz);
//        session.flush();*/
//
//       /* session.saveOrUpdate(test);
//        session.flush();*/
//
//        BrowserEntity browser = new BrowserEntity();
//        browser.setBrowser("BROWSER_VERSION");
//        /*session.saveOrUpdate(browser);*/
//
//
//        LocaleEntity locale = new LocaleEntity();
//        locale.setLocale("TEST_LOCALE".replace("[", "").replace("]", ""));
//        /*session.saveOrUpdate(locale);*/
//
//
//        SyswebEntity sysweb = new SyswebEntity();
//        String syswebUrl = "TEST_SYSWEB".replace("[", "").replace("]", "").replace("ANSWER GET FROM :", "").replaceAll(" ", "");
//        if (syswebUrl.equals("") || syswebUrl.equals(" ")) {
//            syswebUrl = "NO SYSWEB";
//        }
//        sysweb.setName(syswebUrl);
//        session.saveOrUpdate(sysweb);
//
//        PcEntity pc = new PcEntity();
//        pc.setName("PC_NAME");
//        pc.setOs("OS");
//
//        session.saveOrUpdate(pc);
//
//        EventEntity event = new EventEntity();
//        event.setData(new Date());
//        event.setParams("TEST_PARAMETERS");
//        event.setUrl("driver.getCurrentUrl()");
//
//        event.setTestByTestId(test);
//
//        event.setLocaleByLocaleId(locale);
//        event.setBrowserByBrowserId(browser);
//        event.setPcByPcId(pc);
//        event.setSyswebBySyswebId(sysweb);
//
//        locale.getEventsByLocaleId().add(event);
//        sysweb.getEventsBySyswebId().add(event);
//        session.save(pc);
//        session.flush();
//
//        pc.getEventsByPcId().add(event);
//        sysweb.getEventsBySyswebId().add(event);
//
//
//        test.getEventsByTestId().add(event);
//
//        try {
//            event.setMessage("getWhatWentWrong()");
//        } catch (Exception e) {
//            event.setMessage("Can't parse what wet wrong error");
//        }
//        event.setWebsite("BASE_URL");
//
//        session.save(event);
//        session.flush();
//        testDao.closeCurrentSessionwithTransaction();
//
//        System.out.println("event added to DB");

    }

}
