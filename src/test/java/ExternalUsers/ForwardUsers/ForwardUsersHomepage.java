package ExternalUsers.ForwardUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ForwardUsersHomepage {

    WebDriver driver;
    public ForwardUsersHomepage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
}
