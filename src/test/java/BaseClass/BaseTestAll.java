    package BaseClass;

    import AdminClasses.AdminHomepage;
    import CommonClasses.*;
    import ExternalUsers.ForwardUsers.ForwardUsersFormpage;
    import ExternalUsers.ReferredUsers.ReferredUserFormPage;
    import ExternalUsers.ReferredUsers.ReferredUserHomepage;
    import InitiatorCLasses.InitiatorNFAPage;
    import Utils.UserLogins;
    import Utils.Propertyreader;
    import Utils.WaitUtils;
    import org.apache.commons.io.FileUtils;
    import org.openqa.selenium.*;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import org.openqa.selenium.firefox.FirefoxDriver;
    import org.openqa.selenium.firefox.FirefoxOptions;
    import org.openqa.selenium.remote.RemoteWebDriver;
    import org.testng.ITestResult;
    import org.testng.annotations.*;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.net.MalformedURLException;
    import java.net.URL;
                           /*
                               All importations are done here
                                                              */
    @Listeners(Utils.globalretryanalyzerclass.class)
    public class BaseTestAll extends Propertyreader {

        private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        protected String site;
        protected String execENV;
        protected  String gridurl;
        protected String docrole;
/*
                                     * Page Calls
                                     * From all Packages
*/
        protected AdminHomepage  adminHomepage;
        protected ConfidentialNFAPage approverConfidentialNFAPag;
        protected DraftRequestCLass draftRequestClass;
        protected ApproversFormPage approverFormPage;
        protected ForwardUsersFormpage forwardUsersFormpage;
        protected ReferredUserFormPage  referredUserFormPage;
        protected ReferredUserHomepage   referredUserHomepage;
        protected HomePage homePage;
        protected  UserLogins userLogins;
        protected WaitUtils waitUtils;
        protected InitiatorNFAPage initiatorNFAPage;
        protected  ApproversPage approversPage;


        @Parameters({"browser","platform"})
        @BeforeMethod(alwaysRun = true)
        public void setup(@Optional("chrome") String browser,@Optional("Windows") String platform) throws FileNotFoundException, MalformedURLException {


            /*
             Local or Remote Setups
             */
            Propertyreader propertyreader= new Propertyreader();
             site=propertyreader.get("siteurl");
              execENV=propertyreader.get("EXEC_ENV");
              gridurl=propertyreader.get("gridURL");
              docrole=propertyreader.get("importance");

             if (execENV.equalsIgnoreCase(("local"))) {
                 localMachine(browser);
             } else if (execENV.equalsIgnoreCase(("remote"))) {

                 remoteMachine(browser,platform);
             }

             getdriver().manage().window().maximize();
             getdriver().get(site);

              /*
             Pages Initialization
             */
            allpages();

        }

        @AfterMethod(alwaysRun = true)
        public void teardown(ITestResult result) throws IOException, InterruptedException {

            WebDriver drvr=getdriver();

            if (!result.isSuccess()){

                File src = ((TakesScreenshot) drvr).getScreenshotAs(OutputType.FILE);
                String path = System.getProperty("user.dir") + "/src/test/resources/Screenshots/" + result.getName() + ".png";
                FileUtils.copyFile(src, new File(path));
            }

            if (drvr != null) {
                Thread.sleep(5000);
                drvr.quit();
                driver.remove();
            }
        }


        /*
        Methods for proper separation of reusability
         */


        public  static WebDriver getdriver(){
            return  driver.get();
        }


         private void localMachine(String browser){
          if (browser.equalsIgnoreCase("chrome")){

              ChromeOptions chromeOptions = new ChromeOptions();
              chromeOptions.addArguments("--incognito");
              chromeOptions.addArguments("--disable-notifications");
              chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
              chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
              chromeOptions.setExperimentalOption("useAutomationExtension", false);
              driver.set(new ChromeDriver(chromeOptions));

           } else if(browser.equalsIgnoreCase("firefox")){

              System.setProperty("webdriver.gecko.driver", "C:\\Users\\Admin\\Documents\\Gecko\\geckodriver-v0.36.0-win64\\geckodriver.exe");

              FirefoxOptions options = new FirefoxOptions();

// Disable all automation detection
              options.setAcceptInsecureCerts(true);
              options.addArguments("-private");

              options.addPreference("dom.webdriver.enabled", false);
              options.addPreference("useAutomationExtension", false);
              options.addPreference("dom.webnotifications.enabled", false);
              options.addPreference("dom.disable_beforeunload", true);
              options.addPreference("media.navigator.enabled", false);
              options.addPreference("geo.enabled", false);
              options.addPreference("browser.safebrowsing.phishing.enabled", false);

// Block save password popup
              options.addPreference("signon.rememberSignons", false);
              options.addPreference("credentials_enable_service", false);
              options.addPreference("browser.formfill.enable", false);

// Realistic user agent
              options.addPreference("general.useragent.override",
                      "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:126.0) Gecko/20100101 Firefox/126.0");

              options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

// INIT DRIVER
              driver.set(new FirefoxDriver(options));


          } else {
                                       throw new IllegalArgumentException("Unsupported local browser: " + browser);
                                   }
         }
         private void remoteMachine(String browser, String platform) throws MalformedURLException {

            if (browser.equalsIgnoreCase("chrome")){
                ChromeOptions chromeOptions= new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                driver.set(new RemoteWebDriver(new URL(gridurl),chromeOptions));

            } else if(browser.equalsIgnoreCase("firefox")){
                FirefoxOptions firefoxOptions=new FirefoxOptions();

                firefoxOptions.addArguments("-private");
                firefoxOptions.setCapability("moz:webdriverClick", false);
                firefoxOptions.addPreference("signon.rememberSignons", false);
                firefoxOptions.addPreference("credentials_enable_service", false);
                firefoxOptions.addPreference("browser.formfill.enable", false);
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                firefoxOptions.addPreference("geo.enabled", false);
                firefoxOptions.addPreference("useAutomationExtension", false);
                firefoxOptions.addPreference("dom.webdriver.enabled", false);
                firefoxOptions.addPreference("dom.webdriver", false);

                driver.set(new RemoteWebDriver(new URL(gridurl),firefoxOptions));

            }else {

                throw new IllegalArgumentException("Unsupported local browser: "+browser+ "And platform: "+platform);


            }
        }

        private void allpages() {

            adminHomepage= new AdminHomepage(getdriver());
            approverConfidentialNFAPag= new ConfidentialNFAPage(getdriver());
            draftRequestClass= new DraftRequestCLass(getdriver());
            approverFormPage= new ApproversFormPage(getdriver());
            forwardUsersFormpage= new ForwardUsersFormpage(getdriver());
            referredUserFormPage= new ReferredUserFormPage(getdriver());
            referredUserHomepage= new ReferredUserHomepage(getdriver());
            homePage= new HomePage(getdriver());
            userLogins= new UserLogins(getdriver());
            waitUtils= new WaitUtils();
            initiatorNFAPage= new InitiatorNFAPage(getdriver());
            approversPage = new ApproversPage(getdriver());
        }

    }
