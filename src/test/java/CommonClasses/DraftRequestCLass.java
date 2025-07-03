package CommonClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DraftRequestCLass {

    WebDriver driver;

    public DraftRequestCLass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Draft Requests']")
    private WebElement DraftRequestsButton;

    @FindBy(xpath = "(//*[@title='Edit'])")
    private List<WebElement> EditDraftNFAButton;

    @FindBy(xpath = "//span[normalize-space()='Submit']")
    private WebElement SubmitButton;

    @FindBy(xpath = "//span[normalize-space()='Save as Draft']")
    private WebElement SaveAsDraftButton;

    @FindBy(xpath = "//textarea[@id='P9_REMARKS']")
    private WebElement draftremarkdescription;

    @FindBy(xpath = "//button[@id='CANCEL_BUTTON']")
    private WebElement CancelButton;

    public WebElement getDraftRequestsButton() {
        return DraftRequestsButton;

    }
    public List<WebElement> getEditDraftNFAButton() {
        return EditDraftNFAButton;
    }

    public WebElement getSubmitButton() {
        return SubmitButton;
    }

    public WebElement getSaveAsDraftButton() {
        return SaveAsDraftButton;
    }

    public WebElement getDraftRemarkDescription() {
        return draftremarkdescription;
    }

    public WebElement getCancelButton() {
        return CancelButton;
    }



}
