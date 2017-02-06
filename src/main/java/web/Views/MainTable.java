package web.Views;

import hibernate.dbTesting.TestData;
import hibernate.entities.TestEntity;
import hibernate.service.LocaleService;
import hibernate.service.TestService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by geser on 05.02.17.
 */
@ManagedBean
public class MainTable {
    private ArrayList<TestReporter> reports;

    public TestReporterService getReporterService() {
        return reporterService;
    }

    @ManagedProperty("#{reporterService}")
    private TestReporterService reporterService;

    public void setReporterService(TestReporterService reporter) {
        this.reporterService = reporter;
    }

    @PostConstruct
    public void init() {
        reporterService = new TestReporterService();
        reports = new ArrayList<>();
        reports.add(reporterService.createForTest1());
        reports.add(reporterService.createForTest2());
        reports.add(reporterService.createForTest3());
    }

    public ArrayList<TestReporter> getReports(){
        return reports;
    }

    public class TestReporterService{
        // hard code for test, will be edited

        public TestReporter createForTest1(){
            int count = 10;
            String mainSiteUrl = "http://some.url1.com";
            String locale = new LocaleService().findById(13).getLocale();
            List<TestEntity>  testEntities = new TestService().findAll();
            ArrayList<TestData> tests = new ArrayList<>();
            for (TestEntity testEntity : testEntities) {
                tests.add(new TestData(testEntity));
            }
            return new TestReporter(count, locale, mainSiteUrl, tests);
        }

        public TestReporter createForTest2(){
            int count = 8;
            String mainSiteUrl = "http://some.url2.com";
            String locale = new LocaleService().findById(11).getLocale();
            List<TestEntity>  testEntities = new TestService().findAll();
            ArrayList<TestData> tests = new ArrayList<>();
            for (TestEntity testEntity : testEntities) {
                tests.add(new TestData(testEntity));
            }
            return new TestReporter(count, locale, mainSiteUrl, tests);
        }

        public TestReporter createForTest3(){
            int count = 6;
            String mainSiteUrl = "http://some.url3.com";
            String locale = new LocaleService().findById(12).getLocale();
            List<TestEntity>  testEntities = new TestService().findAll();
            ArrayList<TestData> tests = new ArrayList<>();
            for (TestEntity testEntity : testEntities) {
                tests.add(new TestData(testEntity));
            }
            return new TestReporter(count, locale, mainSiteUrl, tests);
        }
    }

    public class TestReporter{
        private int count;
        private String locale;
        private String mainSiteUrl;
        private ArrayList<TestData> tests;

        public HashMap<String, String> getTestDetail() {
            return testDetail;
        }

        public void setTestDetail(HashMap<String, String> testDetail) {
            this.testDetail = testDetail;
        }

        private HashMap<String, String> testDetail;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getMainSiteUrl() {
            return mainSiteUrl;
        }

        public void setMainSiteUrl(String mainSiteUrl) {
            this.mainSiteUrl = mainSiteUrl;
        }

        public ArrayList<TestData> getTests() {
            return tests;
        }

        public void setTests(ArrayList<TestData> tests) {
            this.tests = tests;
        }

        /**
         * This class will be get information from DB (Event table) by date
         * then parse it
         */
        public TestReporter(){
        }

        public TestReporter(int count, String locale, String mainSiteUrl, ArrayList<TestData> tests) {
            this.count = count;
            this.locale = locale;
            this.mainSiteUrl = mainSiteUrl;
            this.tests = tests;
            this.testDetail = new HashMap<>();
            for (TestData test : tests) {
                //will be edited
                this.testDetail.put(test.getName(), test.getClassName());
            }
        }
    }
}
