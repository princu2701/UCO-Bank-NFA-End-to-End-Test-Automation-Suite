package FinalApproverClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FinalAppHomepage {

    WebDriver driver;

    public FinalAppHomepage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
}
