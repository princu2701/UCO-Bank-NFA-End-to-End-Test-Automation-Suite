package Utils;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserLogins {

    WebDriver driver;

    public UserLogins(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='P9999_USERNAME']")
    private WebElement usernamedatabox;

    @FindBy(xpath = "//input[@id='P9999_PASSWORD']")
    private WebElement passworddatabox;

    @FindBy(xpath = "//button[@id='Login_Button']")
    private WebElement Signinbutton;

    public WebElement getSigninbutton() {
        return Signinbutton;
    }

    public WebElement getPassworddatabox() {
        return passworddatabox;
    }

    public WebElement getUsernamedatabox() {
        return usernamedatabox;
    }

    private static final String excelfile = new String("C:\Users\Prince\Desktop\New folder\New folder (3)\UCOSeleniumloginexcel.xlsx");

    private static final Map<String, String[]> logincredentials = new HashMap<>();

    private static void loadcredentials() throws IOException {

        if (!logincredentials.isEmpty()) {
            return;
        }

        FileInputStream fis = new FileInputStream(excelfile);
        Workbook workExcel = WorkbookFactory.create(fis);
        Sheet sheet = workExcel.getSheet("Sheet1");

        //loop will start form 2 as the row 1 is the header
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {

            // row i will be null if the row is empty
            Row row = sheet.getRow(i);

            // This condition will be true if the row is not empty
            if (row != null && row.getCell(1) != null && row.getCell(2) != null) {

                String loginusername = row.getCell(1).getStringCellValue();
                String loginpassword = row.getCell(2).getStringCellValue();

                String roles = switch (i) {
                            /*

                         || Login ||	  || PASSWORD ||
                            prince	         prince
                          FUDANKISKU	    FUDANKISKU
                         DEVENDERKALSI	    DEVENDERKALSI
                         VISHALSRIVASTAVA	VISHALSRIVASTAVA
                         MANOJKUMARSAHU	    MANOJKUMARSAHU
                         AVINASHSHUKLA	    AVINASHSHUKLA

                             */

                    // This cases will pick excel data to login as per line number and role
                    case 2 -> "admin";
                    case 3 -> "initiator";
                    case 4 -> "approver1";
                    case 5 -> "approver2";
                    case 6 -> "approver3";
                    case 7 -> "finalapprover";
                    case 8 -> "forwarduser";
                    case 9 -> "referreduser";
                    default -> throw new IllegalStateException("Unexpected value: " + i);
                };

                logincredentials.put(roles.toLowerCase(), new String[]{loginusername, loginpassword});

            }
        }

        workExcel.close();
        fis.close();

    }

    public static String[] getCredentials(String role) throws IOException {

        loadcredentials();
//        for (Map.Entry<String,String[]>entry: logincredentials.entrySet()){
//            String key=entry.getKey();
//            String[] value=entry.getValue();
//            System.out.println("Role"+key+"Value"+ Arrays.toString(value));
//        }
        String[] creds = logincredentials.get(role.toLowerCase());
        if (creds == null) {

            throw new IllegalArgumentException("Role " + role + " not found in the excel file");

        }
        return creds;
    }

    public void username(String username){

        usernamedatabox.clear();
        usernamedatabox.sendKeys(username);
    }

    public void password(String password) {

        passworddatabox.clear();
        passworddatabox.sendKeys(password);
    }

    public void signin() {

        Signinbutton.click();
    }

    public void login(String role) throws IOException {

        String[] creds = getCredentials(role);
        username(creds[0]);
        password(creds[1]);
        signin();
        System.out.println(creds);
    }
}
