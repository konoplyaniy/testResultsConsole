package hibernate.dbTesting;

import hibernate.entities.*;
import hibernate.service.*;
import hibernate.utils.DBLogger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Sergiy.K on 25-Jan-17.
 */
public class TestTbWork {
    public static void main(String[] args) throws InterruptedException {

        TestService service = new TestService();
        ArrayList<String> tests = service.getAllTestsData();
        for (String test : tests) {
            System.out.println(test);
        }

//        System.out.println(new ClazzEntity(new ClazzService().findByName("LogoDesignBuyingProcess").getClassId(), new ClazzService().findByName("LogoDesignBuyingProcess").getName()));
//        System.out.println(new ClazzEntity(new ClazzService().findByName("Email Hosting").getClassId(), new ClazzService().findByName("Email Hosting").getName()));

//
//        testTestTable();
//        testEventTable();

//        testClassTable();
//        testGroupTable();
//        testLocaleTable();
//        testSyswebTable();
//        testPCtable();
//        testEventTable();
    }

    // SQL for get all values from Event table

//    SELECT E.data, T.browser, PC.name, PC.os, C.name, G.name, T.id_value, T.name, T.params, T.url, T.message
//    FROM crazydomains.event as E
//    inner join crazydomains.locale as L on E.locale_id = L.locale_id
//    inner join crazydomains.pc     as PC on E.pc_id = PC.pc_id
//    inner join crazydomains.sysweb as SW on E.sysweb_id = SW.sysweb_id
//    inner join crazydomains.test   as T on E.test_id = T.test_id
//    inner join crazydomains.group  as G on T.group_id = G.group_id
//    inner join crazydomains.class  as C on T.class_id = C.class_id;


    public static void testEventTable() {
        EventService eventService = new EventService();
        TestService testService = new TestService();

        TestEntity builder = TestEntity.newBuilder()
                .setName("ColoTest")
                .setClassId("Site Protection")
                .setGroupId("Finish")
                .build();
        testService.persist(builder);


        TestEntity test1 = TestEntity.newBuilder()
                .setName("Web test")
                .setIdValue("FIRST DB")
                .setClassId("Site Protection")
                .build();

        EventEntity event2 = EventEntity.newBuilder()
                .setData(getCurrentTime())
                .setLocaleId(".com.au")
                .setTestId(test1)
                .setSyswebId("sysweb3.syrahost.com.au")
                .setPcId("QA-SERGEY-K", "Windows 10")
                .setBrowserId("chrome, version 55.0.2883.87")
                .build();

        eventService.persist(event2);

        EventEntity event = EventEntity.newBuilder()
                .setData(getCurrentTime())
                .setLocaleId(".com.au")
                .setTestId(builder)
                .setSyswebId("sysweb3.syrahost.com.au")
                .setPcId("QA-SERGEY-K", "Windows 10")
                .setBrowserId("chrome, version 55.0.2883.87")
                .build();
        eventService.persist(event);

        event = EventEntity.newBuilder()
                .setData(getCurrentTime())
                .setLocaleId(".com.au")
                .setTestId(10)
                .setSyswebId("sysweb3.syrahost.com.au")
                .setPcId("QA-SERGEY-K", "Windows 10")
                .setBrowserId("chrome, version 55.0.2883.87")
                .build();
        eventService.persist(event);
        DBLogger.info("End testEventTable");
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        return date;
    }

    public static void testTestTable() {
        TestService service = new TestService();
        TestEntity test1 = TestEntity.newBuilder()
                .setName("Web test")
                .setIdValue("FIRST DB")
                .setClassId("Site Protection")
                .build();

        service.persist(test1);

        System.out.println("******************");


//        DBLogger.info("find test by name " + service.findByTestName("ColoTest"));
//        DBLogger.info("find test by name " + service.findByBrowser("Google Chrome"));
//
//        DBLogger.info("find test by name " + service.findByClassName("ColoTest"));
//
//        DBLogger.info("find test by name " + service.findByTestName("ColoTest"));
//        DBLogger.info("find test by name " + service.findByTestName("ColoTest"));
//
//        TestEntity builder = TestEntity.newBuilder()
//                .setName("ColoTest")
//                .setMessage("error colo update")
//                .setBrowser("Google Chrome")
//                .setClassId("Site Protection")
//                .setGroupId("Finish")
//                .build();
//
//        service.persist(builder);
//        System.out.println(service.findAll());
        DBLogger.info("End testTestTable");
    }

    public static void testClassTable() {


        DBLogger.info("***Test class table***");
        ClazzService service = new ClazzService();

//        ClazzEntity clazzEntity = service.findByName("jkhsdjkfhsdk").getName();
//        clazzEntity.getName();
//        clazzEntity.getClassId();


        DBLogger.info("Try to add WebDesign to DB");
        ClazzEntity entity1 = new ClazzEntity("WebDesign class");
        service.persist(entity1);

        DBLogger.info("Try to add Servers builder to DB");
        entity1 = new ClazzEntity("Servers");
        service.persist(entity1);

        DBLogger.info("Try to add new class2 to DB");
        entity1 = new ClazzEntity("new class2");
        service.persist(entity1);

        DBLogger.info("*Test find by name: " + service.findByName("WebDesign class"));

        DBLogger.info("*Test findAll method:");
        List<ClazzEntity> entities = service.findAll();
        DBLogger.info("End testClassTable");
        System.out.printf("classes: ");
        System.out.println(entities);
    }

    public static void testGroupTable() {
        GroupEntity entity = new GroupEntity("Eligibility");
        GroupEntity entity1 = new GroupEntity("Validation Connect to");
        GroupEntity entity2 = new GroupEntity("Test Write to DB");
        GroupEntity entity3 = new GroupEntity("2Finish");
        GroupService service = new GroupService();
        service.persist(entity);
        service.persist(entity1);
        service.persist(entity2);
        service.persist(entity3);

        System.out.println("last added: " + service.findByName("Finish"));

        System.out.println("rows count: " + service.getRowsCount());

        System.out.println("*******************");
        System.out.println(service.findAll());
        DBLogger.info("End testGroupTable");
    }

    public static void testLocaleTable() {
        LocaleEntity entity = new LocaleEntity("ssd.sd");
        LocaleEntity entity2 = new LocaleEntity("sd.nz");
        LocaleEntity entity3 = new LocaleEntity("fo.fk");
        LocaleEntity entity4 = new LocaleEntity(".4k");

        LocaleService service = new LocaleService();

        service.persist(entity);
        service.persist(entity2);
        service.persist(entity3);
        service.persist(entity4);

        System.out.println("should be 4k: " + service.findByName(".4k").getLocaleId());
        System.out.println("rows count: " + service.getRowsCount());
        System.out.println("*******************");
        System.out.println(service.findAll());
        DBLogger.info("End testLocaleTable");
    }

    public static void testPCtable() {
        PcService service = new PcService();
        PcEntity entity = new PcEntity("PC1000", "Windows 10");
        service.persist(entity);
        entity = new PcEntity("PC123234", "Linux");
        service.persist(entity);
        System.out.println("rows count: " + service.getRowsCount());
        System.out.println("*******************");
        System.out.println(service.findAll());
        System.out.println("*******************");
        System.out.println("PC1 OS = " + service.findByName("PC1000").getOs());
        System.out.println("id = 1,  name = " + service.findById(8).getName() + " OS: " + service.findById(8).getOs());
        DBLogger.info("End testPCtable");
    }

    public static void testSyswebTable() {
        SyswebService service = new SyswebService();
        SyswebEntity entity = new SyswebEntity("sysweb4.uk.syrahost");
        service.persist(entity);
        entity = new SyswebEntity("sysweb5");
        service.persist(entity);

        System.out.println("rows count: " + service.getRowsCount());
        System.out.println("*******************");
        System.out.println(service.findAll());
        System.out.println("*******************");
        System.out.println("sysweb id = " + service.findByName("sysweb5").getSyswebId());
        System.out.println("id = 1,  name = " + service.findById(7).getName());
        DBLogger.info("End testSyswebTable");
    }
}
