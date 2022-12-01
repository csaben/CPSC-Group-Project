import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * selenium driver toolbox
 */
public class driver {

    //what we support in our project
    public static String[] browsersList = {"Chrome", "Headless"};
    public static WebDriver driver;
    public static String defaultBrowser = "Headless";
    public static String browser = defaultBrowser;
    //should there be a wait call ??

    public driver() {
        spawnDriver();
        //potentially give the driver a marked amount of time to perform or handle that elsewhere
    }

    public static void modifyBrowser(String Browser){
        //as of right now, we dont allow modifying the browser
        browser = Browser;
    }

    private void spawnDriver(){
        switch (this.browser) {
            case "Headless":
                System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("-width=1920");
                options.addArguments("-height=1080");
                driver = new ChromeDriver(options);
                //insert the URL of interest or pass the driver to a different module to use
//                driver.get("https://selenium.dev");
                break;
            case "Chrome":
                System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
                driver = new ChromeDriver();
                //insert the URL of interest or pass the driver to a different module to use
//                driver.get("https://selenium.dev");
                break;
        }
    }

    public void close() {
        driver.quit();
    }

}
