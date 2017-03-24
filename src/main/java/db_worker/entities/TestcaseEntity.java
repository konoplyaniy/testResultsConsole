package db_worker.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "testcase", schema = "crazydomains")
public class TestcaseEntity implements Serializable {
    private int testCaseId;
    private String className;
    private String testName;
    private String parameters;
    private String description;
    private String steps;
    private String expectedResult;
    private String aditionalInfo;
    private String mavenFront;
    private String mavenMembers;

    public TestcaseEntity() {
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "test_case_id", nullable = false)
    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    @Basic
    @Column(name = "class_name", nullable = false, length = 400)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "test_name", nullable = false, length = 400)
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Basic
    @Column(name = "parameters", nullable = true, length = 400)
    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 400)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "steps", nullable = false, length = 1000)
    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Basic
    @Column(name = "expected_result", nullable = true, length = 1000)
    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Basic
    @Column(name = "aditional_info", nullable = true, length = 500)
    public String getAditionalInfo() {
        return aditionalInfo;
    }

    public void setAditionalInfo(String aditionalInfo) {
        this.aditionalInfo = aditionalInfo;
    }

    @Basic
    @Column(name = "maven_front", nullable = true, length = 400)
    public String getMavenFront() {
        return mavenFront;
    }

    public void setMavenFront(String mavenFront) {
        this.mavenFront = mavenFront;
    }

    @Basic
    @Column(name = "maven_members", nullable = true, length = 400)
    public String getMavenMembers() {
        return mavenMembers;
    }

    public void setMavenMembers(String mavenMembers) {
        this.mavenMembers = mavenMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestcaseEntity that = (TestcaseEntity) o;

        if (testCaseId != that.testCaseId) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (testName != null ? !testName.equals(that.testName) : that.testName != null) return false;
        if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (steps != null ? !steps.equals(that.steps) : that.steps != null) return false;
        if (expectedResult != null ? !expectedResult.equals(that.expectedResult) : that.expectedResult != null)
            return false;
        if (aditionalInfo != null ? !aditionalInfo.equals(that.aditionalInfo) : that.aditionalInfo != null)
            return false;
        if (mavenFront != null ? !mavenFront.equals(that.mavenFront) : that.mavenFront != null) return false;
        if (mavenMembers != null ? !mavenMembers.equals(that.mavenMembers) : that.mavenMembers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testCaseId;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (steps != null ? steps.hashCode() : 0);
        result = 31 * result + (expectedResult != null ? expectedResult.hashCode() : 0);
        result = 31 * result + (aditionalInfo != null ? aditionalInfo.hashCode() : 0);
        result = 31 * result + (mavenFront != null ? mavenFront.hashCode() : 0);
        result = 31 * result + (mavenMembers != null ? mavenMembers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return className + "\n " + testName + "\n " + parameters + "\n " + description + "\n " + steps + "\n " + expectedResult + "\n " + aditionalInfo + "\n " + mavenFront + "\n " + mavenMembers;
    }
}
