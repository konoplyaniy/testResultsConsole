package hibernate.dbTesting;


import hibernate.dao.BrowserDao;
import hibernate.entities.*;
import hibernate.service.ClazzService;
import hibernate.service.EventService;
import hibernate.service.GroupService;
import hibernate.service.TestService;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sergiy.K on 25-Jan-17.
 */
public class TestTbWork {

    public static void main(String[] args) {
        EventService service = new EventService();

        Date startDate = new Date();
        startDate.setYear(117);
        startDate.setMonth(1);
        startDate.setDate(20);
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        System.out.println("start " + startDate);
        TestEntity test = new TestEntity();
        test.setName("Test insert to DB122"); //add new test
        GroupEntity group = new GroupEntity();
        group.setName("INSERTION@d13s2"); // test add new Group
        ClazzEntity clazz = new ClazzEntity();
        clazz.setName("Finish2342W3d3d2"); //test add existing class
        test.setClazzByClassId(clazz);
        test.setGroupByGroupId(group);

        BrowserEntity browser = new BrowserEntity();
        browser.setBrowser("Test browser add2");// add new browser
        LocaleEntity locale = new LocaleEntity();
        locale.setLocale(".sg12");// test existing group
        SyswebEntity sysweb = new SyswebEntity();
        sysweb.setName("sysweb4.uk.syrahost12"); // test existing sysweb

        PcEntity pc = new PcEntity();
        pc.setName("Mobile Phone1"); //add new pc
        pc.setOs("Android2");


        EventEntity event = new EventEntity();
        event.setData(startDate);
        event.setBrowserByBrowserId(browser);
        event.setPcByPcId(pc);
        event.setTestByTestId(test);
        event.setSyswebBySyswebId(sysweb);
        event.setLocaleByLocaleId(locale);
        event.setChecked(0);
        event.setUrl("crazydomains.com.au");
        event.setMessage("Success");
        event.setParams("param 1, param 2");

        service.persist(event);
    }

}
