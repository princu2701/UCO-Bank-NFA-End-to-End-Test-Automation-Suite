package CommonClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfidentialNFAPage {

    WebDriver driver;

    public ConfidentialNFAPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


}
