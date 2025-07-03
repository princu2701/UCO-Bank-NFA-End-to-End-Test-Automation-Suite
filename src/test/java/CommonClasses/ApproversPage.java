package CommonClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ApproversPage {

    WebDriver driver;

    public ApproversPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody/tr[2]/td[1]/a")
    private WebElement latestPendingNfa;

    @FindBy(xpath = "//textarea[@id='P19_REMARKS']")
    private WebElement remarksText;

    @FindBy(xpath = "//span[normalize-space()='Refer']")
    private WebElement referUserCase;

    // Inside Refer User case
    @FindBy(xpath = "(//*[contains(text(),'Refer')])[3]")
    private WebElement internalBoxReferOption;

    @FindBy(css = "#P31_DEPARTMENT_ID_lov_btn")
    private WebElement referDepartmentQueueIcon;

    @FindBy(css = "ul[aria-labelledby='il2_info'] > li")
    private List<WebElement> referListOfDepartments;

    @FindBy(xpath = "//*[.='Information Technology']")
    private WebElement referItDeptFieldSelection;

    @FindBy(css = "#P31_DESIGNATION_lov_btn")
    private WebElement referDesignationQueueIcon;

    @FindBy(css = "ul[aria-labelledby='il4_info'] > li")
    private List<WebElement> referDesignationItBasedListOptions;

    @FindBy(xpath = "(//*[.='Assistance General Manager'])[2]")
    private WebElement designationAgmSelection;

    @FindBy(css = "ul[aria-labelledby='il8_info'] > li")
    private WebElement referItSeniorManagerBasedListOptions;

    @FindBy(css = "#P31_USERS_lov_btn")
    private WebElement referUsersQueueClickingOption;

    @FindBy(css = "[id='B51523012765117569665']")
    private WebElement finalReferSubmitOption;

    @FindBy(css = "[id='B51860892616690738713']")
    private WebElement referInternalBoxCancellationOption;
    // Refer Inside Values Ends here

    @FindBy(css = "[id='B80318481893618499649']")
    private WebElement forwardOption;

    // Inside Forward Elements
    @FindBy(xpath = "(//*[.='Forward'])[3]")
    private WebElement dialogueBoxForwardOption;

    @FindBy(css = "[id='P27_DEPARTMENT_ID_lov_btn']")
    private WebElement dialogueBoxForwardDepartmentQueueIcon;

    @FindBy(css = "[id='P27_DESIGNATION_ID_lov_btn']")
    private WebElement dialogueBoxForwardDesignationQueueIcon;

    @FindBy(css = "[id='P27_USERS_lov_btn']")
    private WebElement dialogueBoxForwardUserQueueIcon;

    @FindBy(xpath = "//textarea[@id='P27_REMARKS']")
    private WebElement dialogueBoxForwardRemarksBox;

    @FindBy(css = "[data-otel-label='FORWARD']")
    private WebElement dialogueBoxForwardButton;

    @FindBy(css = "ul[aria-labelledby='il2_info'] > li")
    private List<WebElement> dialogueBoxForwardDepartmentList;

    @FindBy(css = "ul[aria-labelledby='il4_info'] > li")
    private List<WebElement> dialogueBoxForwardDesignationList;

    @FindBy(css = "ul[aria-labelledby='il8_info'] > li")
    private List<WebElement> dialogueBoxForwardAndReferUsersList;
    // Inside Forward Elements ends here

    @FindBy(xpath = "(//*[.='Reject'])[1]")
    private WebElement rejectOption;

    @FindBy(xpath = "(//*[.='Raise Query'])[1]")
    private WebElement raiseQueryOption;

    @FindBy(xpath = "(//*[.='Approve'])[1]")
    private WebElement approveOption;

    @FindBy(xpath = "(//*[.='Reject'])[3]")
    private WebElement rejectConfirmDialogueBox;

    @FindBy(xpath = "(//*[.='Raise Query'])[3]")
    private WebElement raiseQueryConfirmDialogueBox;

    @FindBy(xpath = "(//*[.='Approve'])[3]")
    private WebElement approveConfirmDialogueBox;


    public WebElement getCancelFormOption() {
        return cancelFormOption;
    }

    @FindBy(xpath = "//span[normalize-space()='Cancel']")
    private WebElement cancelFormOption;




    public WebElement getInternalBoxReferOption() {
        return internalBoxReferOption;
    }

    public WebElement getLatestPendingNfa() {
        return latestPendingNfa;
    }


    public WebElement getRemarksText() {
        return remarksText;
    }
    public WebElement getReferUserCase() {
        return referUserCase;
    }

    public WebElement getReferDepartmentQueueIcon() {
        return referDepartmentQueueIcon;
    }

    public List<WebElement> getReferListOfDepartments() {
        return referListOfDepartments;
    }

    public WebElement getReferItDeptFieldSelection() {
        return referItDeptFieldSelection;
    }

    public WebElement getReferDesignationQueueIcon() {
        return referDesignationQueueIcon;
    }

    public List<WebElement> getReferDesignationItBasedListOptions() {
        return referDesignationItBasedListOptions;
    }

    public WebElement getDesignationAgmSelection() {
        return designationAgmSelection;
    }

    public WebElement getReferItSeniorManagerBasedListOptions() {
        return referItSeniorManagerBasedListOptions;
    }

    public WebElement getReferUsersQueueClickingOption() {
        return referUsersQueueClickingOption;
    }

    public WebElement getFinalReferSubmitOption() {
        return finalReferSubmitOption;
    }

    public WebElement getReferInternalBoxCancellationOption() {
        return referInternalBoxCancellationOption;
    }

    public WebElement getForwardOption() {
        return forwardOption;
    }

    public WebElement getDialogueBoxForwardOption() {
        return dialogueBoxForwardOption;
    }

    public WebElement getDialogueBoxForwardDepartmentQueueIcon() {
        return dialogueBoxForwardDepartmentQueueIcon;
    }

    public WebElement getDialogueBoxForwardDesignationQueueIcon() {
        return dialogueBoxForwardDesignationQueueIcon;
    }

    public WebElement getDialogueBoxForwardUserQueueIcon() {
        return dialogueBoxForwardUserQueueIcon;
    }

    public WebElement getDialogueBoxForwardRemarksBox() {
        return dialogueBoxForwardRemarksBox;
    }

    public WebElement getDialogueBoxForwardButton() {
        return dialogueBoxForwardButton;
    }

    public List<WebElement> getDialogueBoxForwardDepartmentList() {
        return dialogueBoxForwardDepartmentList;
    }

    public List<WebElement> getDialogueBoxForwardDesignationList() {
        return dialogueBoxForwardDesignationList;
    }

    public List<WebElement> getDialogueBoxForwardAndReferUsersList() {
        return dialogueBoxForwardAndReferUsersList;
    }

    public WebElement getRejectOption() {
        return rejectOption;
    }

    public WebElement getRaiseQueryOption() {
        return raiseQueryOption;
    }

    public WebElement getApproveOption() {
        return approveOption;
    }

    public WebElement getRejectConfirmDialogueBox() {
        return rejectConfirmDialogueBox;
    }

    public WebElement getRaiseQueryConfirmDialogueBox() {
        return raiseQueryConfirmDialogueBox;
    }

    public WebElement getApproveConfirmDialogueBox() {
        return approveConfirmDialogueBox;
    }

    /*
     *************************  Methods All Below ****************************
     */

    public boolean selectDepartment(String department) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(referListOfDepartments));

            for (WebElement departs : referListOfDepartments) {
                if (departs.getText().trim().equalsIgnoreCase(department.trim())) {
                    departs.click();
                    return true;
                }
            }
            System.out.println("Department not found: " + department);
            return false;
        } catch (Exception e) {
            System.out.println("Exceptions: " + e.getMessage());
            return false;
        }
    }

    public boolean selectDesignation(String designation) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(referDesignationItBasedListOptions));

            for (WebElement design : referDesignationItBasedListOptions) {
                if (design.getText().trim().equalsIgnoreCase(designation.trim())) {
                    design.click();
                    return true;
                }
            }
            System.out.println("No Designation Found: " + designation);
            return false;
        } catch (Exception e) {
            System.out.println("Exceptions: " + e.getMessage());
            return false;
        }
    }

    public boolean selectUserFromList(String user) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(dialogueBoxForwardAndReferUsersList));

            for (WebElement usr : dialogueBoxForwardAndReferUsersList) {
                if (usr.getText().trim().equalsIgnoreCase(user.trim())) {
                    usr.click();
                    return true;
                }
            }

            System.out.println("User not found: " + user);
            return false;
        } catch (Exception e) {
            System.out.println("Exceptions: " + e.getMessage());
            return false;
        }
    }

    // ------------------------- Dynamic Pending NFA Selection Method   ----------------------------

    public void requirednfafromlist(int expectednumber) {
        try {
            String xpath = "//tbody/tr[" + expectednumber + "]/td[1]/a";
            WebElement targetRow = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", targetRow);
            targetRow.click();

            System.out.println("✅ Successfully clicked NFA row #" + expectednumber);
        } catch (Exception e) {
            System.out.println("❌ Exception while clicking NFA row #" + expectednumber + ": " + e.getMessage());
        }
    }



}