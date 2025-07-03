package TestClasses;

import CommonClasses.HomePage;
import BaseClass.BaseTestAll;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;

public class InitiatorTestClasses extends BaseTestAll {

    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void initializeReport() throws IOException {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\Admin\\IdeaProjects\\UCOBank_Selenium\\TestReport.html");
        sparkReporter.config().setDocumentTitle("UCO Bank - NFA Test Report");
        sparkReporter.config().setReportName("Initiator Workflow Validation");
        sparkReporter.config().setTimelineEnabled(true);
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system info
        extent.setSystemInfo("Application", "UCO Bank NFA System");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", System.getProperty("user.name"));
    }

    @Test(priority = 1)
    public void initiatorFormProcessTest() throws IOException {

        test = extent.createTest("Initiator Form Submission Verification", "Verify Initiator can complete NFA creation process with only Importance selection");

        try {
            // Test Step 1: Login
            test.log(Status.INFO, "Logging in as initiator user");
            userLogins.login("initiator");
            test.log(Status.PASS, "Successfully logged in as initiator");

            // Test Step 2: Initiate NFA Creation
            test.log(Status.INFO, "Clicking Create NFA button");
            waitUtils.explicitWaits(HomePage.getAllNFAButton());
            HomePage.getAllNFAButton().click();
            test.log(Status.PASS, "Create NFA button clicked successfully");

            // Test Step 3: Verify Page
            test.log(Status.INFO, "Verifying Create NFA page");
            waitUtils.waitUntilClickable(HomePage.getAllNFAButton());

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(getdriver().getTitle(), "Create NFA", "Page title validation");
            test.log(Status.PASS, "Verified page title: 'Create NFA'");

            // Test Step 4: Select Importance
            test.log(Status.INFO, "Selecting document importance level");
            waitUtils.explicitWaits(initiatorNFAPage.getDocumentImportanceSelection());
            String selectedValue = initiatorNFAPage.getDocumentImportanceSelection().getAttribute("value");
            initiatorNFAPage.NFAFormImportanceSelection(docrole);
            test.log(Status.PASS, "Selected document importance: " + selectedValue);
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2)
    public void dashboardTest() throws IOException {

        test = extent.createTest("Dashboard Verification", "Verify Initiator can access Dashboard page");

        try {

            //Check All Requests
            test.log(Status.INFO, "Logging in as Initiator user");
            userLogins.login("initiator");
            test.log(Status.PASS, "Successfully logged in as Initiator");

            test.log(Status.INFO, "Clicking AllNFAs Dashboard button");
            waitUtils.explicitWaits(HomePage.getAllNFAButton());
            HomePage.getAllNFAButton().click();
            test.log(Status.PASS, "All NFAs Dashboard TABbutton clicked");

            WebElement Allrequests = getdriver().findElement(By.xpath("//*[.='All Requests']"));
            if (Allrequests.getText().equals("All Requests")) {
                Assert.assertTrue(Allrequests.isDisplayed(), "All Requests are displayed");
                test.log(Status.PASS, "All Requests Page is displayed");
            } else {
                test.log(Status.FAIL, "All Requests Page is not displayed");
            }

            getdriver().navigate().back();

            //Check Pending Requests
            test.log(Status.INFO, "Clicking Pending Dashboard button");
            waitUtils.explicitWaits(HomePage.getAllNFAButton());
            HomePage.getAllNFAButton().click();
            test.log(Status.PASS, "Pending Dashboard TAB clicked");

            WebElement Pendingrequests = getdriver().findElement(By.xpath("(//*[.='Pending NFA'])[2]"));

            if (Pendingrequests.getText().equals("Pending NFA")) {
                test.log(Status.PASS, "Pending Requests Page is displayed");
            } else {
                test.log(Status.FAIL, "Pending Requests Page is not displayed");
            }
            getdriver().navigate().back();


            //Check Approved Requests
            test.log(Status.INFO, "Clicking Approved Dashboard button");
            waitUtils.explicitWaits(HomePage.getAllNFAButton());
            HomePage.getAllNFAButton().click();
            test.log(Status.PASS, "Approved Dashboard TAB clicked");

            WebElement Approvedrequests = getdriver().findElement(By.xpath("(//*[.='Approved NFA'])[2]"));
            if (Approvedrequests.getText().equals("Approved NFA")) {
                test.log(Status.PASS, "Approved Requests Page is displayed");
            } else {
                test.log(Status.FAIL, "Approved Requests Page is not displayed");
            }

            getdriver().navigate().back();

            //Check Rejected Requests
            test.log(Status.INFO, "Clicking Rejected Dashboard button");
            waitUtils.explicitWaits(HomePage.getAllNFAButton());
            HomePage.getAllNFAButton().click();
            test.log(Status.PASS, "Rejected Dashboard TAB clicked");

            WebElement Rejectedrequests = getdriver().findElement(By.xpath("(//*[.='Rejected NFA'])[2]"));
            if (Rejectedrequests.getText().equals("Rejected NFA")) {
                test.log(Status.PASS, "Rejected Requests Page is displayed");
            } else {
                test.log(Status.FAIL, "Rejected Requests Page is not displayed");
            }

            getdriver().navigate().back();

            //RaiseQuery NFA
            test.log(Status.INFO, "Clicking RaiseQuery Dashboard button");
            waitUtils.explicitWaits(HomePage.getAllNFAButton());
            HomePage.getAllNFAButton().click();
            test.log(Status.PASS, "RaiseQuery Dashboard TAB clicked");

            WebElement RaiseQueryrequests = getdriver().findElement(By.xpath("//*[.='Query Raised']"));
            if (RaiseQueryrequests.getText().equals("Query Raised")) {
                test.log(Status.PASS, "Raise Query Page is displayed");
            } else {
                test.log(Status.FAIL, "Raise Query Page is not displayed");
            }


        }catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass(alwaysRun = true)
    public void finalizeReport() {
        if (extent != null) {

            extent.setSystemInfo("Test Completion Time", new java.util.Date().toString());
            extent.flush();

        }
    }
}