package TestClasses;

import BaseClass.BaseTestAll;
import CommonClasses.ApproversPage;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.testng.AssertJUnit.assertTrue;

public class FullFlows extends BaseTestAll {

    String nfaNumber = "AB".concat(UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 5));
    private static final Logger logger = LogManager.getLogger(FullFlows.class);
    ExtentReports extent;
    ExtentTest test;



    //  ----------------------------   Report Initialized Below  ---------------------------------

    @BeforeClass
    public void reportinitialization() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\Prince\\Desktop\\IdeaProjects\\UCOBank_Selenium\\FlowReport_FullFlow.html");
        sparkReporter.config().setDocumentTitle("Uco Flows");
        sparkReporter.config().setReportName("Uco Flows");
        sparkReporter.config().setTimelineEnabled(true);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("utf-8");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "UCO Bank NFA System");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", System.getProperty("user.name"));
        logger.info("Extent Report Initialized");
    }

    //        -------------------------------- Initiator flow --------------------------------------------


    @Test(alwaysRun = true, description = "Creation of NFA form with not working topSheet along with Approval Matrix", priority = 1)
    public void initiatorFullFlow() {
        test = extent.createTest("NFA Creation", "Initiator Role to Create NFA");
        logger.info("Initiator flow started: Creating NFA");

        try {
            test.log(Status.INFO, "Logging in as Initiator user");
            userLogins.login("initiator");
            logger.info("Logged in as Initiator");
            test.log(Status.PASS, "Logged in as Initiator");

            waitUtils.explicitWaits(homePage.getCreateNFAButton());
            homePage.getCreateNFAButton().click();
            test.log(Status.PASS, "Clicked Create NFA button");

            waitUtils.explicitWaits(initiatorNFAPage.getNFATypeSelection());
            initiatorNFAPage.NFAFormImportanceSelection(docrole);
            test.log(Status.PASS, "Selected Importance");

            initiatorNFAPage.getNFANumbeerSelection().sendKeys(nfaNumber);
            initiatorNFAPage.getNFATypeSelection().click();
            test.log(Status.PASS, "Entered NFA Number and clicked NFA Type");

            Actions actions = new Actions(getdriver());
            waitUtils.explicitWaits(initiatorNFAPage.getNFATypeGeneralSelection());
            actions.moveToElement(initiatorNFAPage.getNFATypeGeneralSelection()).click().build().perform();
            test.log(Status.PASS, "Selected NFA Type General");

            Select select = new Select(initiatorNFAPage.getNFAConfidentialitySelection());
            waitUtils.explicitWaits(initiatorNFAPage.getNFAConfidentialitySelection());
            select.selectByVisibleText("No");
            test.log(Status.PASS, "Confidentiality selected as No");

            initiatorNFAPage.getNFASummarySheet().click();
            initiatorNFAPage.getSummarySheetAddbutton().click();
            WebElement summaryframe = getdriver().findElement(By.cssSelector("[title='Summary Questions and Answers']"));
            getdriver().switchTo().frame(summaryframe);
            test.log(Status.INFO, "Entered Summary Sheet Frame");

            Select qnaselect = new Select(initiatorNFAPage.getSummaryQuestionsAddbutton());
            qnaselect.selectByIndex(3);
            initiatorNFAPage.getSummaryAnswersAddbutton().sendKeys(nfaNumber);
            Assert.assertTrue(initiatorNFAPage.getSummaryAnswersAddbutton().getAttribute("value").contains(nfaNumber));
            jsclick(initiatorNFAPage.getSummartAnswersaddButton());
            jsclick(initiatorNFAPage.getSheetCloseButton());
            getdriver().switchTo().defaultContent();
            test.log(Status.PASS, "Summary Q&A added successfully");

            initiatorNFAPage.getSubjectSelection().sendKeys(nfaNumber);
            initiatorNFAPage.getFormDescription().sendKeys(nfaNumber);
            initiatorNFAPage.getNFAFormPageUploadDocumentButton().click();
            test.log(Status.PASS, "Subject & Description entered");

            WebElement fileuploadframe = getdriver().findElement(By.cssSelector("[title='Upload Supporting Document']"));
            getdriver().switchTo().frame(fileuploadframe);
            Select docselect = new Select(initiatorNFAPage.getDocumentTypeSelection());
            docselect.selectByIndex(2);
            ((JavascriptExecutor) getdriver()).executeScript("arguments[0].style.display = 'block';", initiatorNFAPage.getDocumentFilepathSelectionbutton());
            initiatorNFAPage.getDocumentFilepathSelectionbutton().sendKeys("C:\\Users\\Prince\\Desktop\\New folder\\blankpdf.pdf");
            Assert.assertTrue(initiatorNFAPage.getDocumentFilepathSelectionbutton().getAttribute("value").contains("blankpdf.pdf"));
            initiatorNFAPage.getUploadDocFileButton().click();
            getdriver().switchTo().defaultContent();
            test.log(Status.PASS, "Supporting doc uploaded");

            jsScrollIntoView(initiatorNFAPage.getRemarksBox());
            test.log(Status.PASS, "Scrolled to Remarks box");

            List<WebElement> checkboxes = initiatorNFAPage.getApprovalmatrixcheckboxes();
            for (WebElement checkbox : checkboxes) {
                try {
                    new WebDriverWait(getdriver(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(checkbox));
                    jsScrollIntoView(checkbox);
                    waitUtils.forceWait(300);
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                    }
                    test.log(Status.PASS, "Approval Matrix checkbox selected");
                } catch (Exception e) {
                    logger.warn("Fallback to JS click for checkbox");
                    ((JavascriptExecutor) getdriver()).executeScript("arguments[0].click();", checkbox);
                }
            }

            WebElement remarks = initiatorNFAPage.getRemarksBox();
            ((JavascriptExecutor) getdriver()).executeScript("arguments[0].value='AB!@#!@#';", remarks);
            Assert.assertTrue(remarks.getAttribute("value").contains("AB!@#!@#"));
            test.log(Status.PASS, "Remarks added");

            waitUtils.waitUntilClickable(initiatorNFAPage.getSubmitButton());
            actions.moveToElement(initiatorNFAPage.getSubmitButton()).build().perform();
            initiatorNFAPage.getSubmitButton().click();
            actions.moveToElement(initiatorNFAPage.getNFAFormPageSubmitButton()).build().perform();
            initiatorNFAPage.getNFAFormPageSubmitButton().click();
            test.log(Status.PASS, "Submit clicked");

            WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(30));
            String expectedRequestNumber = nfaNumber;
            String exactMessage = " NFA updated successfully with request number " + expectedRequestNumber;
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[.='" + exactMessage + "'])[2]")));
            assertTrue(successMessage.isDisplayed());
            test.log(Status.PASS, "NFA Creation confirmed with toast: " + exactMessage);
            logger.info("Initiator flow completed successfully");

        } catch (Exception e) {
            logger.error("Initiator flow failed: " + e.getMessage());
            test.log(Status.FAIL, "Exception occurred in initiatorFullFlow: " + e.getMessage());
        }
    }

 //             ---------------------------- Approvers FLow -----------------------------
    @Test(description = "Verification with Approver1 Selected as DevenderKalsi", priority = 2)
    public void NFaApprovalwithApprover1() {
        try {
            test = extent.createTest("NFaApprovalwithApprover1", "Verification with Approver1 Selected as DevenderKalsi");
            logger.info("Starting approval from Approver1");
            userLogins.login("approver1");
            logger.info("Logged in as Approver1");
            waitUtils.visibleElement(homePage.getAllNFAButton());
            homePage.getPendingNFAButton().click();
            jsclick(approversPage.getLatestPendingNfa());
            jsScrollIntoView(approversPage.getForwardOption());
            approversPage.getRemarksText().sendKeys("Approved By Approver1");
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            String expectedName = "VISHALSRIVASTAVA";
            String expectedMsg = "NFA assigned to next approver: " + expectedName;
            WebElement toast = new WebDriverWait(getdriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + expectedMsg + "']")));
            Assert.assertEquals(toast.getText().trim(), expectedMsg);
            logger.info("Approver1 successfully approved the NFA");
            test.log(Status.PASS, "Latest NFA Approved and Asserted Already By Approver1");
        } catch (Exception e) {
            logger.error("Approver1 approval failed: " + e.getMessage());
            test.log(Status.FAIL, "Exception: " + e.getMessage());
        }
    }

    @Test(description = "Verification with Approver2 Selected as DevenderKalsi", priority = 3)
    public void NFA_Approval_from2ndApprover() {
        try {
            test = extent.createTest("2nd Approver NFA Approval", "By VISHALSRIVASTAVA");
            logger.info("Starting approval from Approver2");
            userLogins.login("approver2");
            waitUtils.visibleElement(homePage.getAllNFAButton());
            homePage.getPendingNFAButton().click();
            jsScrollIntoView(approversPage.getLatestPendingNfa());
            jsclick(approversPage.getLatestPendingNfa());
            jsScrollIntoView(approversPage.getApproveOption());
            approversPage.getRemarksText().sendKeys("Approved By Approver2");
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            String expectedName = "MANOJKUMARSAHU";
            String expectedMsg = "NFA assigned to next approver: " + expectedName;
            WebElement toast = new WebDriverWait(getdriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + expectedMsg + "']")));
            Assert.assertEquals(toast.getText().trim(), expectedMsg);
            test.log(Status.PASS, "Successfully Approved NFA Form by Approver2");
            logger.info("Approver2 NFA Approval successful");
        } catch (Exception e) {
            logger.error("Approver2 approval failed: " + e.getMessage());
            test.log(Status.FAIL, "Exception: " + e.getMessage());
        }
    }

    @Test(description = "Verification with Approver3 Selected as ManojKumarSahu", priority = 4)
    public void NFA_Approval_from3rdApprover() {
        try {
            test = extent.createTest("NFA Approval from 3rd Approver", "By Manoj Kumar Sahu");
            logger.info("Starting Approver3 flow");
            userLogins.login("approver3");
            By headingLocator = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(headingLocator);
            WebElement heading = getdriver().findElement(headingLocator);
            if (heading.getText().trim().equalsIgnoreCase("Welcome back, MANOJ KUMAR SAHU")) {
                logger.info("Approver3 heading verified");
            } else {
                logger.warn("Heading mismatch for Approver3");
            }
            homePage.getPendingNFAButton().click();
            approversPage.getLatestPendingNfa().click();
            jsScrollIntoView(approversPage.getRemarksText());
            jsclick(approversPage.getRemarksText());
            approversPage.getRemarksText().sendKeys("Approved from Approver 3");
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            String expectedMsg = "NFA assigned to next approver: AVINASHSHUKLA";
            WebElement toast = new WebDriverWait(getdriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + expectedMsg + "']")));
            Assert.assertEquals(toast.getText().trim(), expectedMsg);
            test.log(Status.PASS, "Approver3 Approval completed");
        } catch (Exception e) {
            logger.error("Approver3 flow failed: " + e.getMessage());
            test.log(Status.FAIL, "Exception: " + e.getMessage());
        }
    }

    @Test(description = "Final Approval Touch through - AVINASH SHUKLA", priority = 5)
    public void finalNFA_Approval_Stage() {
        try {
            test = extent.createTest("Final Approval Process", "By Avinash Shukla");
            logger.info("Final approver logging in");
            userLogins.login("finalapprover");
            By headingby = By.xpath("(//h2)[2]");
            waitUtils.visiblewithByelement(headingby);
            WebElement heading = getdriver().findElement(headingby);
            if (heading.getText().trim().equalsIgnoreCase("Welcome back, AVINASH SHUKLA")) {
                logger.info("Final approver heading matched");
            } else {
                logger.warn("Final approver heading mismatch");
            }
            homePage.getPendingNFAButton().click();
            waitUtils.explicitWaits(approversPage.getLatestPendingNfa());
            approversPage.getLatestPendingNfa().click();
            jsScrollIntoView(approversPage.getRemarksText());
            approversPage.getRemarksText().sendKeys("Final Approval Done");
            approversPage.getApproveOption().click();
            approversPage.getApproveConfirmDialogueBox().click();
            WebElement msg = waitUtils.visiblewithByelement(By.xpath("//span[normalize-space()='Approved Successfully']"));
            if (msg.getText().equalsIgnoreCase("Approved Successfully")) {
                logger.info("Final approval success message displayed");
            } else {
                logger.warn("No success message after final approval");
            }
            test.log(Status.PASS, "Full NFA form approval flow completed");
        } catch (Exception e) {
            logger.error("Final approval stage failed: " + e.getMessage());
            test.log(Status.FAIL, "Exception: " + e.getMessage());
        }
    }



        //--------------------   Report Initialization n Some Private Methods Below   ------------------
    @AfterClass
    public void finalizeReport() {
        if (extent != null) {
            extent.flush();
            logger.info("Extent report flushed and saved");
            System.out.println("✅ Report Generated Successfully");
        }
    }

    private void jsclick(WebElement element){

        ((JavascriptExecutor)getdriver()).executeScript("arguments[0].click()",element);
    }

    private void jsScrollIntoView(WebElement element){

        ((JavascriptExecutor)getdriver()).executeScript("arguments[0].scrollIntoView({behaviour: 'smooth',block: 'center'});",element);
    }
}

