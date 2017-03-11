package hibernate.dbTesting;


import hibernate.service.EventService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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

    private static String addNewDay(Date date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateParse = date.toString();
        Date result = new Date();
        String d23 = (LocalDate.parse(dateParse).plusDays(1).toString());
        return d23;
    }


    public static void main(String[] args) {
        Date begining;
        Calendar calendar_start = Calendar.getInstance();
        calendar_start.set(Calendar.DAY_OF_MONTH,calendar_start.getActualMinimum(Calendar.DAY_OF_MONTH));
        begining = calendar_start.getTime();
        System.out.println(begining);

        /*EventService service = new EventService();
        System.out.println(service.findByCurrentMonthEvents());

*/
  /*      String query = "INSERT INTO `event` VALUES " +
        "(1,'2017-01-26 16:14:29',2,5,3,3,NULL,NULL,NULL,1,0),";*/

        /*"(5,'2017-01-26 17:51:08',5,7,5,5,NULL,NULL,NULL,1,1)," +
        "(7,'2017-01-26 17:58:32',6,7,5,5,NULL,NULL,NULL,1,0)," +
        "(11,'2017-01-26 18:15:19',7,7,5,5,NULL,NULL,NULL,1,1)," +
        "(12,'2017-01-26 18:15:37',7,7,5,5,NULL,NULL,NULL,1,0)," +
        "(13,'2017-01-26 18:16:01',7,7,5,5,NULL,NULL,NULL,1,1)," +
        "(14,'2017-01-26 18:17:56',7,7,5,5,NULL,NULL,NULL,1,0)," +
        "(16,'2017-01-26 18:19:49',7,7,5,5,NULL,NULL,NULL,1,0)," +
        "(17,'2017-01-26 18:21:27',7,7,5,5,NULL,NULL,NULL,1,1)," +
        "(18,'2017-01-26 18:22:52',7,7,5,5,NULL,NULL,NULL,1,1)," +
        "(20,'2017-01-26 18:30:23',8,8,6,6,NULL,NULL,NULL,1,0)," +
        "(21,'2017-01-26 18:33:12',9,8,6,6,NULL,NULL,NULL,1,1)," +
        "(22,'2017-01-26 18:33:51',9,8,6,6,NULL,NULL,NULL,1,1)," +
        "(23,'2017-01-27 16:31:35',9,8,6,6,NULL,NULL,NULL,1,1)," +
        "(24,'2017-01-27 16:35:32',10,8,6,6,NULL,NULL,NULL,1,0)," +
        "(25,'2017-01-27 16:36:48',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(26,'2017-01-27 16:36:48',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(27,'2017-01-27 16:44:56',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(28,'2017-01-27 16:44:56',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(29,'2017-01-27 16:53:53',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(30,'2017-01-27 16:53:53',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(32,'2017-01-31 17:36:02',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(34,'2017-01-31 17:36:54',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(35,'2017-01-31 17:36:54',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(36,'2017-01-31 18:11:14',10,8,6,6,NULL,NULL,NULL,1,1)," +
        "(37,'2017-01-31 18:11:14',10,8,6,6,NULL,NULL,NULL,1,1);";*/



//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate = new Date();
//        try {
//            startDate = formatter.parse("2017-01-04");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date endDate = new Date();
//        try {
//            endDate = formatter.parse("2017-03-06");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar start = Calendar.getInstance();
//        start.setTime(startDate);
//        Calendar end = Calendar.getInstance();
//        end.setTime(endDate);
//
//        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
//            // Do your job here with `date`.
//            System.out.println("date " + date);
//            System.out.println("start: " + start.getTime());
//        }

//        System.out.println(getPcName());
//        try {
//            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }

//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        formatter.setTimeZone(TimeZone.getDefault());
//
//
//        EventService service = new EventService();
//        Date startDate = new Date();
//        startDate.setDate(1);
//        Date endDate = new Date();
//        endDate.setDate(6);
//        System.out.println(startDate);
//        System.out.println(endDate);
//
//        try {
//            Date ss = formatter.parse("2017-02-22");
//            System.out.println(service.findByDate(ss));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(service.findBySyswebBetweenDates("ANSWER GET FROM : SYSWEB3.UK.SYRAHOST.COM", startDate, endDate));



//        System.out.println(service.findBySyswebBetweenDates("ANSWER GET FROM : SYSWEB3.UK.SYRAHOST.COM", startDate, endDate).size());
//        System.out.println("*********************************************************************************************************************");
//        System.out.println("findBySyswebTestNameBetweenDates test");
//        System.out.println(service.findBySyswebTestNameBetweenDates("ANSWER GET FROM : SYSWEB3.UK.SYRAHOST.COM","REGISTER NEW COSTUMER IN HEADER TEST", startDate, endDate));
//        System.out.println("*********************************************************************************************************************");
//        System.out.println("findBySyswebTestNameLocaleBetweenDates test");
//        System.out.println(service.findBySyswebTestNameLocaleBetweenDates("ANSWER GET FROM : SYSWEB3.UK.SYRAHOST.COM","REGISTER NEW COSTUMER IN HEADER TEST","CO.UK", startDate, endDate));
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
