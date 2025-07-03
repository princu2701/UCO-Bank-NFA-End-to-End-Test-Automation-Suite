package TestClasses;

import BaseClass.BaseTestAll;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ApproverTestings extends BaseTestAll {

    ExtentTest test;
    ExtentReports extent;
    ExtentSparkReporter sparkReporter;

    private static final Logger logger = LogManager.getLogger(ApproverTestings.class);

    @BeforeClass(alwaysRun = true)
    public void reportinitialize() {

        sparkReporter = new ExtentSparkReporter("C:\\Users\\Prince\\Desktop\\IdeaProjects\\UCOBank_Selenium\\ApproverTestReports.html");
        sparkReporter.config().setDocumentTitle("Uco Approver Tests");
        sparkReporter.config().setReportName("UCO Testings");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimelineEnabled(true);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "UCO Bank NFA System");
        extent.setSystemInfo("Environment", "QA Testing");
        extent.setSystemInfo("Tester", System.getProperty("user.name"));

        logger.info("Extent Report Initialized");
    }


    @Test(priority = 1, description = "Proceeding with Clicking Simultaneously on Approve button in Approver 1 NFA Approval Page without entering Remarks", groups = "approve")
    public void approver1() throws IOException {
        try {
            test = extent.createTest("Approver 1 Testing Begins");

            // Login as Approver
            userLogins.login("approver1");

            String approvername = "DEVENDER KALSI";
            By heading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(heading);
            WebElement welcometext = getdriver().findElement(heading);

            if (welcometext.getText().trim().equalsIgnoreCase("Welcome Back, DEVENDER KALSI")) {
                System.out.println("We are in Right Approver Account: " + welcometext.getText());
            } else {

                System.out.println("Some Wrong Here! -" + welcometext.getText());
            }

            test.log(Status.PASS, "Successfully logged in as Approver: " + approvername);
            homePage.getPendingNFAButton().click();
            approversPage.getLatestPendingNfa().click();
            jsScrollView(approversPage.getRemarksText());
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            test.log(Status.FAIL, "User have visited the error");
            for (int i = 0; i < 20; i++) {
                try {
                    test.log(Status.INFO, "Will Retry this time with 20 clicks on approve button without entering remarks");
                    WebElement approveBtn = approversPage.getApproveOption();
                    WebElement confirmBox = approversPage.getApproveConfirmDialogueBox();

                    if (approveBtn.isDisplayed()) {
                        approveBtn.click();
                        Thread.sleep(500); // Add explicit wait later instead of Thread.sleep

                        if (confirmBox.isDisplayed()) {
                            confirmBox.click();
                            System.out.println("Visible for: " + i + "th item");
                        } else {
                            System.out.println("Confirm box not shown at iteration: " + i);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception at iteration " + i + ": " + e.getMessage());
                }
                test.log(Status.PASS, "Checked clicking 20th time on element");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Login failed for Approver 1. Error: " + e.getMessage());
            System.out.println("Exception: " + e.getMessage());
            Assert.fail("Approver 1 login failed due to: " + e.getMessage());
        }

    }


    @Test(priority = 2, alwaysRun = true, description = "Proceeding with Clicking Simultaneously on Approve button in Approver 2 NFA Approval Page without entering Remarks", groups = "approve")
    public void approver2() throws IOException {
        try {
            test = extent.createTest("Approver 1 Testing Begins");

            // Login as Approver
            userLogins.login("approver2");

            String approvername = "VISHAL SRIVASTAVA";
            By heading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(heading);
            WebElement welcometext = getdriver().findElement(heading);

            if (welcometext.getText().trim().equalsIgnoreCase("Welcome Back, VISHAL SRIVASTAVA")) {
                System.out.println("We are in Right Approver Account: " + welcometext.getText());
            } else {

                System.out.println("Some Wrong Here! -" + welcometext.getText());
            }

            test.log(Status.PASS, "Successfully logged in as Approver: " + approvername);

            homePage.getPendingNFAButton().click();
            approversPage.getLatestPendingNfa().click();
            jsScrollView(approversPage.getRemarksText());
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();

            test.log(Status.FAIL, "User have visited the error");
            for (int i = 0; i <= 20; i++) {
                try {
                    test.log(Status.INFO, "Will Retry this time with 20 clicks on approve button without entering remarks");
                    WebElement approveBtn = approversPage.getApproveOption();
                    WebElement confirmBox = approversPage.getApproveConfirmDialogueBox();

                    if (approveBtn.isDisplayed()) {
                        approveBtn.click();
                        Thread.sleep(500); // Add explicit wait later instead of Thread.sleep

                        if (confirmBox.isDisplayed()) {
                            confirmBox.click();
                            System.out.println("Visible for: " + i + "th item");
                        } else {
                            System.out.println("Confirm box not shown at iteration: " + i);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception at iteration " + i + ": " + e.getMessage());
                }
                test.log(Status.PASS, "Checked clicking 20th time on element");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Login failed for Approver 2. Error: " + e.getMessage());
            System.out.println("Exception: " + e.getMessage());
            Assert.fail("Approver 2 login failed due to: " + e.getMessage());
        }

    }


    @Test(priority = 3, description = "Proceeding with Clicking Simultaneously on Approve button in Approver 3 NFA Approval Page without entering Remarks", groups = "approve")
    public void approver3() throws IOException {
        try {
            test = extent.createTest("Approver 1 Testing Begins");

            // Login as Approver
            userLogins.login("approver3");

            String approvername = "MANOJ KUMAR SAHU";
            By heading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(heading);
            WebElement welcometext = getdriver().findElement(heading);

            if (welcometext.getText().trim().equalsIgnoreCase("Welcome Back, MANOJ KUMAR SAHU")) {
                System.out.println("We are in Right Approver Account: " + welcometext.getText());
            } else {

                System.out.println("Some Wrong Here! -" + welcometext.getText());
            }

            test.log(Status.PASS, "Successfully logged in as Approver: " + approvername);

            homePage.getPendingNFAButton().click();
            approversPage.getLatestPendingNfa().click();
            jsScrollView(approversPage.getRemarksText());
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();

            test.log(Status.FAIL, "User have got some error");
            for (int i = 0; i <= 20; i++) {
                try {
                    test.log(Status.INFO, "Will Retry this time with 20 clicks on approve button without entering remarks");
                    WebElement approveBtn = approversPage.getApproveOption();
                    WebElement confirmBox = approversPage.getApproveConfirmDialogueBox();

                    if (approveBtn.isDisplayed()) {
                        approveBtn.click();
                        Thread.sleep(500); // Add explicit wait later instead of Thread.sleep

                        if (confirmBox.isDisplayed()) {
                            confirmBox.click();
                            System.out.println("Visible for: " + i + "th item");
                        } else {
                            System.out.println("Confirm box not shown at iteration: " + i);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception at iteration " + i + ": " + e.getMessage());
                }
                test.log(Status.PASS, "Checked clicking 20th time on element");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Login failed for Approver 3. Error: " + e.getMessage());
            System.out.println("Exception: " + e.getMessage());
            Assert.fail("Approver 3 login failed due to: " + e.getMessage());
        }

    }


    @Test(priority = 4, alwaysRun = true, description = "Proceeding with Clicking Simultaneously on Approve button in Approver 4 NFA Approval Page without entering Remarks", groups = "approve")
    public void approver4() throws IOException {
        try {
            test = extent.createTest("Approver 1 Testing Begins");

            // Login as Approver
            userLogins.login("finalapprover");

            String approvername = "AVINASH SHUKLA";
            By heading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(heading);
            WebElement welcometext = getdriver().findElement(heading);

            if (welcometext.getText().trim().equalsIgnoreCase("Welcome Back, AVINASH SHUKLA")) {
                System.out.println("We are in Right Approver Account: " + welcometext.getText());
            } else {

                System.out.println("Some Wrong Here! -" + welcometext.getText());
            }

            test.log(Status.PASS, "Successfully logged in as Approver: " + approvername);

            homePage.getPendingNFAButton().click();
            approversPage.getLatestPendingNfa().click();
            jsScrollView(approversPage.getRemarksText());
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();

            test.log(Status.FAIL, "User have got some error");
            for (int i = 0; i <= 20; i++) {
                try {
                    test.log(Status.INFO, "Will Retry this time with 20 clicks on  new Approver NFA Page approve button without entering remarks");
                    WebElement approveBtn = approversPage.getApproveOption();
                    WebElement confirmBox = approversPage.getApproveConfirmDialogueBox();

                    if (approveBtn.isDisplayed()) {
                        approveBtn.click();
                        Thread.sleep(500); // Add explicit wait later instead of Thread.sleep

                        if (confirmBox.isDisplayed()) {
                            confirmBox.click();
                            System.out.println("Visible for: " + i + "th item");
                        } else {
                            System.out.println("Confirm box not shown at iteration: " + i);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception at iteration " + i + ": " + e.getMessage());
                }
                test.log(Status.PASS, "Checked clicking 20th time on element");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Login failed for Approver 4. Error: " + e.getMessage());
            System.out.println("Exception: " + e.getMessage());
            Assert.fail("Approver 4 login failed due to: " + e.getMessage());

        }

    }

    // ----------------------- Remarks Box Whitespace Testing ----------------------------


    @Test(priority = 5, alwaysRun = true, description = "Will proceed with whitespace on remarks", groups = "remarks")
    public void approverremarks1() {

        try {
            test = extent.createTest("Starting Approver WhiteSpace procceeding with Remarks Box", "Approver 1 - DEVENDER KALSI");

            test.log(Status.INFO, "Approver 1 - Devender kalsi testing starting here");
            userLogins.login("approver1");
            By heading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(heading);
            WebElement hometext = getdriver().findElement(heading);
            if (hometext.getText().trim().equalsIgnoreCase("Welcome Back, Devender Kalsi")) {

                System.out.println("Heading Verified: " + hometext.getText());
            } else {
                System.out.println("Error in matching heading: " + hometext.getText());
            }
            test.log(Status.PASS, "Successfully logged into the DEVENdER KALSI and asserted its heading");

            test.log(Status.INFO, "Going to form Page for remarks testing");
            homePage.nfapendingbutton();
            approversPage.requirednfafromlist(9);
            jsScrollView(approversPage.getRemarksText());
            jsClick(approversPage.getRemarksText());
            approversPage.getRemarksText().sendKeys("       ");
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            test.log(Status.PASS, "Checked Remarks Box SuccessFully with Whitespace");

        } catch (Exception e) {

            System.out.println("Exceptions: " + e.getMessage());
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());

        }
    }


    @Test(priority = 6, alwaysRun = true, description = "Will proceed with whitespace on remarks", groups = "remarks")
    public void approverremarks2() {

        try {
            test = extent.createTest("Starting Approver WhiteSpace procceeding with Remarks Box", "Approver 1 - DEVENDER KALSI");

            test.log(Status.INFO, "Approver 2 - VISHL SRIVASTAVA testing starting here");
            userLogins.login("approver2");
            By heading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(heading);
            WebElement hometext = getdriver().findElement(heading);
            if (hometext.getText().trim().equalsIgnoreCase("Welcome Back, VISHAL SRIVASTAVA")) {

                System.out.println("Heading Verified: " + hometext.getText());
            } else {
                System.out.println("Error in matching heading: " + hometext.getText());
            }
            test.log(Status.PASS, "Successfully logged into the DEVENdER KALSI and asserted its heading");

            test.log(Status.INFO, "Going to form Page for remarks testing");
            homePage.nfapendingbutton();
            approversPage.requirednfafromlist(5);
            jsScrollView(approversPage.getRemarksText());
            jsClick(approversPage.getRemarksText());
            approversPage.getRemarksText().sendKeys("       ");
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(10));
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Remarks') and contains(text(),'mandatory')]")
            ));
            Assert.assertTrue(errorMsg.isDisplayed(), "Remarks validation error not shown");

            test.log(Status.PASS, "Checked Remarks Box SuccessFully with Whitespace");

        } catch (Exception e) {

            System.out.println("Exceptions: " + e.getMessage());
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());

        }
    }


    @Test(priority = 7, alwaysRun = true, description = "Will proceed with whitespace on remarks", groups = "remarks")
    public void approverremarks3() {

        try {
            test = extent.createTest("Starting Approver WhiteSpace procceeding with Remarks Box", "Approver 1 - DEVENDER KALSI");

            test.log(Status.INFO, "Approver 3 - MANOJ KUMAR SAHU testing starting here");
            userLogins.login("approver3");
            By heading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(heading);
            WebElement hometext = getdriver().findElement(heading);
            if (hometext.getText().trim().equalsIgnoreCase("Welcome Back, MANOJ KUMAR SAHU")) {

                System.out.println("Heading Verified: " + hometext.getText());
            } else {
                System.out.println("Error in matching heading: " + hometext.getText());
            }
            test.log(Status.PASS, "Successfully logged into the MANOJ KUMAR SAHU and asserted its heading");

            test.log(Status.INFO, "Going to form Page for remarks testing");
            homePage.nfapendingbutton();
            approversPage.requirednfafromlist(5);
            jsScrollView(approversPage.getRemarksText());
            jsClick(approversPage.getRemarksText());
            approversPage.getRemarksText().sendKeys("       ");
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(10));
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Remarks') and contains(text(),'mandatory')]")
            ));
            Assert.assertTrue(errorMsg.isDisplayed(), "Remarks validation error not shown");

            test.log(Status.PASS, "Checked Remarks Box SuccessFully with Whitespace");

        } catch (Exception e) {

            System.out.println("Exceptions: " + e.getMessage());
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());

        }
    }


    @Test(priority = 8, alwaysRun = true, description = "Will proceed with whitespace on remarks", groups = "remarks")
    public void approverremarks4() {

        try {
            test = extent.createTest("Starting Approver WhiteSpace procceeding with Remarks Box", "Approver 1 - DEVENDER KALSI");

            test.log(Status.INFO, "Approver 4 - AVINASH SHUKLA testing starting here");
            userLogins.login("finalapprover");
            By heading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(heading);
            WebElement hometext = getdriver().findElement(heading);
            if (hometext.getText().trim().equalsIgnoreCase("Welcome Back, AVINASH SHUKLA")) {

                System.out.println("Heading Verified: " + hometext.getText());
            } else {
                System.out.println("Error in matching heading: " + hometext.getText());
            }
            test.log(Status.PASS, "Successfully logged into the AVINASH SHUKLA and asserted its heading");

            test.log(Status.INFO, "Going to form Page for remarks testing");
            homePage.nfapendingbutton();
            approversPage.requirednfafromlist(5);
            jsScrollView(approversPage.getRemarksText());
            jsClick(approversPage.getRemarksText());
            approversPage.getRemarksText().sendKeys("       ");
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(10));
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Remarks') and contains(text(),'mandatory')]")
            ));
            Assert.assertTrue(errorMsg.isDisplayed(), "Remarks validation error not shown");

            test.log(Status.PASS, "Checked Remarks Box SuccessFully with Whitespace");

        } catch (Exception e) {

            System.out.println("Exceptions: " + e.getMessage());
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());

        }
    }

    // ---------------------------- || Playing with Upload Docs || --------------------------------

    @Test(priority = 9, alwaysRun = true, description = "Approver will upload wrong formats file and extra heavy", groups = "Documents")
    public void approveruploaddocs1() {

        try {
            test = extent.createTest("Approver Doc. Upload Feature Testing");
            test.log(Status.INFO, "Uploading Right Doc but with more that limit allowed");
            userLogins.login("approver1");
            By homeheading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(homeheading);
            WebElement headelement = getdriver().findElement(homeheading);
            if (headelement.getText().equalsIgnoreCase("Welcome back, DEVENDER KALSI")) {
                System.out.println("Heading Verfiied Successfully");
            } else {
                System.out.println("Some Error Occured in Verification of User Heading");
            }
            homePage.getPendingNFAButton().click();
            approversPage.requirednfafromlist(5);
            jsScrollView(initiatorNFAPage.getNFAFormPageUploadDocumentButton());
            initiatorNFAPage.getNFAFormPageUploadDocumentButton().click();
            WebElement fileuploadframe = getdriver().findElement(By.cssSelector("[title='Upload Supporting Document']"));
            getdriver().switchTo().frame(fileuploadframe);
            waitUtils.visibleElement(initiatorNFAPage.getDocumentTypeSelection());
            Select docimportance = new Select(initiatorNFAPage.getDocumentTypeSelection());
            docimportance.selectByIndex(2);
            ((JavascriptExecutor) getdriver()).executeScript("arguments[0].style.display='block'", initiatorNFAPage.getDocumentFilepathSelectionbutton());
            initiatorNFAPage.getDocumentFilepathSelectionbutton().sendKeys("C:\\Users\\Prince\\Desktop\\New folder\\chromedriver-win64\\chromedriver-win64");
            initiatorNFAPage.getUploadDocFileButton().click();
            getdriver().switchTo().defaultContent();


        } catch (Exception e) {

            System.out.println("Exception: " + e.getMessage());
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());

        }

    }


    @Test(priority = 10, alwaysRun = true, description = "Approver will upload wrong formats file and extra heavy", groups = "Documents")
    public void approveruploaddocs2() {

        try {

            test = extent.createTest("Approver Doc. Upload Feature Testing");
            test.log(Status.INFO, "Uploading Right Doc but with more that limit allowed");
            userLogins.login("approver2");
            By homeheading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(homeheading);
            WebElement headelement = getdriver().findElement(homeheading);
            if (headelement.getText().equalsIgnoreCase("Welcome back, VISHAL SRIVASTAVA")) {
                System.out.println("Heading Verfiied Successfully");
            } else {
                System.out.println("Some Error Occured in Verification of User Heading");
            }
            homePage.getPendingNFAButton().click();
            approversPage.requirednfafromlist(5);
            jsScrollView(initiatorNFAPage.getNFAFormPageUploadDocumentButton());
            initiatorNFAPage.getNFAFormPageUploadDocumentButton().click();
            WebElement fileuploadframe = getdriver().findElement(By.cssSelector("[title='Upload Supporting Document']"));
            getdriver().switchTo().frame(fileuploadframe);

            try {
                waitUtils.visibleElement(initiatorNFAPage.getDocumentTypeSelection());

                Select docImportance = new Select(initiatorNFAPage.getDocumentTypeSelection());
                docImportance.selectByIndex(2);

                JavascriptExecutor js = (JavascriptExecutor) getdriver();
                js.executeScript("arguments[0].style.display='block'", initiatorNFAPage.getDocumentFilepathSelectionbutton());

                // Upload file – ensure it's a valid test file (.exe or >3MB file)
                File file = new File("C:\\Users\\Prince\\Pictures\\532559.jpg");
                initiatorNFAPage.getDocumentFilepathSelectionbutton().sendKeys(file.getAbsolutePath());

                initiatorNFAPage.getUploadDocFileButton().click();

                WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(10));
                WebElement docError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-for='P17_DOCUMENT']")));

                if (docError.getText().contains("File is too large. Maximum file size is 3 MB.")) {
                    System.out.println("✅ Error Displayed Perfectly");
                    test.log(Status.PASS, "Error shown on uploading large/invalid document.");
                } else {
                    System.out.println("❌ Incorrect error or no error shown");
                    test.log(Status.FAIL, "Error not displayed as expected for invalid doc.");
                    Assert.fail("Expected file upload validation error not displayed.");
                }

            } catch (Exception e) {
                System.out.println("Exception during file upload test: " + e.getMessage());
                test.log(Status.FAIL, "Exception: " + e.getMessage());
                Assert.fail("File upload negative scenario failed due to exception.");
            }

            getdriver().switchTo().defaultContent();

        } catch (Exception e) {

            System.out.println("Exception: " + e.getMessage());
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());

        }

    }


    @Test(priority = 11, alwaysRun = true, description = "Approver will upload wrong formats file and extra heavy", groups = "Documents")
    public void approveruploaddocs3() {
        try {

            test = extent.createTest("Approver Doc. Upload Feature Testing");
            test.log(Status.INFO, "Uploading Right Doc but with more that limit allowed");
            userLogins.login("finalapprover");
            By homeheading = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(homeheading);
            WebElement headelement = getdriver().findElement(homeheading);
            if (headelement.getText().equalsIgnoreCase("Welcome back, AVINASH SHUKLA")) {
                System.out.println("Heading Verfiied Successfully");
            } else {
                System.out.println("Some Error Occured in Verification of User Heading");
            }
            homePage.getPendingNFAButton().click();
            approversPage.requirednfafromlist(5);
            jsScrollView(initiatorNFAPage.getNFAFormPageUploadDocumentButton());
            initiatorNFAPage.getNFAFormPageUploadDocumentButton().click();
            WebElement fileuploadframe = getdriver().findElement(By.cssSelector("[title='Upload Supporting Document']"));
            getdriver().switchTo().frame(fileuploadframe);
            try {
                waitUtils.visibleElement(initiatorNFAPage.getDocumentTypeSelection());

                Select docImportance = new Select(initiatorNFAPage.getDocumentTypeSelection());
                docImportance.selectByIndex(2);

                JavascriptExecutor js = (JavascriptExecutor) getdriver();
                js.executeScript("arguments[0].style.display='block'", initiatorNFAPage.getDocumentFilepathSelectionbutton());

                // Upload file – ensure it's a valid test file (.exe or >3MB file)
                File file = new File("C:\\Users\\Prince\\Pictures\\532559.jpg");
                initiatorNFAPage.getDocumentFilepathSelectionbutton().sendKeys(file.getAbsolutePath());

                initiatorNFAPage.getUploadDocFileButton().click();

                WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(10));
                WebElement docError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-for='P17_DOCUMENT']")));

                if (docError.getText().contains("File is too large. Maximum file size is 3 MB.")) {
                    System.out.println("✅ Error Displayed Perfectly");
                    test.log(Status.PASS, "Error shown on uploading large/invalid document.");
                } else {
                    System.out.println("❌ Incorrect error or no error shown");
                    test.log(Status.FAIL, "Error not displayed as expected for invalid doc.");
                    Assert.fail("Expected file upload validation error not displayed.");
                }

            } catch (Exception e) {
                System.out.println("Exception during file upload test: " + e.getMessage());
                test.log(Status.FAIL, "Exception: " + e.getMessage());
                Assert.fail("File upload negative scenario failed due to exception.");
            }

            getdriver().switchTo().defaultContent();

        } catch (Exception e) {

            System.out.println("Exception: " + e.getMessage());
            test.log(Status.FAIL, "Test failed due to: " + e.getMessage());

        }

    }















    @AfterClass
    public void reportend(){

        if (extent == null) {
            throw new IllegalStateException("ExtentReports was not initialized. Did you forget to run @BeforeClass?");
        }

        extent.flush();
        System.out.println("\nReport Status -> Report Generation Done");

    }

    private void jsClick(WebElement element){

        JavascriptExecutor js=(JavascriptExecutor) getdriver();
        js.executeScript("arguments[0].click();",element);

    }

    private void jsScrollView(WebElement element){

        JavascriptExecutor js=(JavascriptExecutor) getdriver();
        js.executeScript("arguments[0].scrollIntoView({behaviour: 'smooth', block: 'center'});",element);

    }

}
