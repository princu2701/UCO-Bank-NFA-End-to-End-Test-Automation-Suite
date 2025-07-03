package ExternalUsers.ForwardUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ForwardUsersFormpage {

    WebDriver driver;

    public ForwardUsersFormpage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
