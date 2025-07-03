package CommonClasses;

import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//button[@id='t_Button_navControl']")
    private WebElement sideMenuButton;

    @FindBy(xpath = "(//*[.='Create NFA'])[3]")
    private WebElement createNFAButton;

    @Getter
    @FindBy(xpath = "(//*[@class='a-CardView-fullLink'])[1]")
    private static WebElement allNFAButton;

    @FindBy(xpath = "(//*[@class='a-CardView-fullLink'])[2]")
    private WebElement PendingNFAButton;

    @FindBy(xpath = "(//*[@class='a-CardView-fullLink'])[3]")
    private WebElement RejectedNFAButton;

    @FindBy(xpath = "(//*[@class='a-CardView-fullLink'])[4]")
    private WebElement ApprovedNFAButton;

    @FindBy(xpath = "(//*[@class='a-CardView-fullLink'])[5]")
    private WebElement QueryNFAButton;

    @FindBy(xpath = "//*[.='Sign Out']")
    private WebElement logoutButton;

    @FindBy(css="[title='Search Report']")
    private WebElement dashboardnfasearchbutton;

    @FindBy(id ="R74588447889603924954_search_button")
    private WebElement searchgobutton;

    @FindBy(xpath = "//table/tbody/tr[2]/td[2]")
    private WebElement InitatorName;

    public WebElement getInitatorName() {
        return InitatorName;
    }

    public WebElement getDashboardnfasearchbutton() {
        return dashboardnfasearchbutton;

    }

    public WebElement getSearchgobutton() {
        return searchgobutton;

    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getCreateNFAButton() {
        return createNFAButton;
    }

    public WebElement getPendingNFAButton() {
        return PendingNFAButton;
    }

    public WebElement getRejectedNFAButton() {
        return RejectedNFAButton;
    }

    public WebElement getApprovedNFAButton() {

        return ApprovedNFAButton;

    }

    public WebElement getQueryNFAButton() {

        return QueryNFAButton;

    }

    public WebElement getSideMenuButton() {

        return sideMenuButton;

    }

    public void nfasearchbutton() {

        dashboardnfasearchbutton.sendKeys("!@@(!@*U(!923912~!@!ejnsdwqejifkdnr", Keys.ENTER);

    }

    public void searchgobutton() {
        searchgobutton.click();

    }

    public static WebElement getAllNFAButton() {
        return allNFAButton;
    }

    public void logoutButton() {
        logoutButton.click();

    }



    public void nfaallbutton() {

        allNFAButton.click();
    }

    public void nfapendingbutton() {

        PendingNFAButton.click();
    }

    public void nfarejectedbutton() {

        RejectedNFAButton.click();
    }

    public void nfaapprovedbutton() {

        ApprovedNFAButton.click();
    }


}
