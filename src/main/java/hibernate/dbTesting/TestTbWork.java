package hibernate.dbTesting;



import hibernate.entities.*;
import hibernate.service.EventService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
public class TestTbWork {
    public static String getPcName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "http://localhost/";
        }
    }


    public static void main(String[] args) {
        System.out.println(getPcName());
        try {
            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
//        EventService service = new EventService();




//
//        Date startDate = new Date();
//        startDate.setYear(117);
//        startDate.setMonth(1);
//        startDate.setDate(20);
//        startDate.setHours(0);
//        startDate.setMinutes(0);
//        startDate.setSeconds(0);
//        System.out.println("start " + startDate);
//        TestEntity test = new TestEntity();
//        test.setName("Test insert to DB1221"); //add new test
//        GroupEntity group = new GroupEntity();
//        group.setName("INSERTION@d13s21"); // test add new Group
//        ClazzEntity clazz = new ClazzEntity();
//        clazz.setName("Finish2342W3d3d21"); //test add existing class
//        test.setClazzByClassId(clazz);
//        test.setGroupByGroupId(group);
//
//        BrowserEntity browser = new BrowserEntity();
//        browser.setBrowser("Test browser add21");// add new browser
//        LocaleEntity locale = new LocaleEntity();
//        locale.setLocale(".sg122");// test existing group
//        SyswebEntity sysweb = new SyswebEntity();
//        sysweb.setName("sysweb4.uk.syrahost121"); // test existing sysweb
//
//        PcEntity pc = new PcEntity();
//        pc.setName("Mobile Phone11"); //add new pc
//        pc.setOs("Android21");
//
//        EventEntity event = new EventEntity();
//        event.setData(startDate);
//        event.setBrowserByBrowserId(browser);
//        event.setPcByPcId(pc);
//        event.setTestByTestId(test);
//        event.setSyswebBySyswebId(sysweb);
//        event.setLocaleByLocaleId(locale);
//        event.setChecked(0);
//        event.setUrl("crazydomains.com.au1");
//        event.setMessage("Success1");
//        event.setParams("param 12, param 22");
//
//        service.persist(event);


    }

}
