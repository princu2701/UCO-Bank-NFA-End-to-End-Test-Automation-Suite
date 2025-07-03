package ExternalUsers.ReferredUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ReferredUserFormPage {

    WebDriver driver;
    public ReferredUserFormPage(WebDriver driver){
        this.driver=driver;

        PageFactory.initElements(driver,this);
    }
}
