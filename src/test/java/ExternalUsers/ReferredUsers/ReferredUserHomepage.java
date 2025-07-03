package ExternalUsers.ReferredUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ReferredUserHomepage {

    WebDriver driver;

    public ReferredUserHomepage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
