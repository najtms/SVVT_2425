package MainPart;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import util.CookieUtils;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchEngine {
    private static WebDriver webDriver;
    private static String baseUrl;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/muhamadassaad/chromedriver-mac-x64/chromedriver"); // Loading ChromeDriver
        ChromeOptions options = new ChromeOptions(); // Creating Option instances
        baseUrl = "https://sportsport.ba/"; // Link we use!
        options.addArguments("user-data-dir=/Users/muhamadassaad/Library/Application Support/Google/Chrome"); // Location of ChromeProfiles
        options.addArguments("--profile-directory=Profile 1"); // Locating/Selecting profile we will use
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-first-run"); // Disables the first time run, kao necemo imat welcome to chrome,adblocker loading...
        options.addArguments("--disable-extensions-ui"); // prevents popups mainly od Adblockera
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
    }
    @Test
    public void testLoadCookies() {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();

        // Load cookies
        CookieUtils.loadCookies(webDriver, "cookies.data");

        // Perform your test steps after cookies are loaded
        System.out.println("Cookies loaded successfully!");
    }


    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NEW LINE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void SearchEngineBasic() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("KK Igman ");
        Thread.sleep(500); // -- We wait so the Search engine can suggest the correc thing
        searchbar.sendKeys("Burch");
        Thread.sleep(2000);
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "KK Igman Burch - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
    }
    @Test
    public void SearchEngineCapitaleletters() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("kK iGMan ");
        Thread.sleep(500); // -- We wait so the Search engine can suggest the correc thing
        searchbar.sendKeys("buRCH");
        Thread.sleep(2000);
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "KK Igman Burch - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
    }
    @Test
    public void SearchEnginePaste() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        WebElement searchButton = webDriver.findElement(By.cssSelector("button[type=submit]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK Sarajevo");
        Thread.sleep(500);
        searchButton.click();
        Thread.sleep(2000);
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "FK Sarajevo - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
    }
    @Test
    public void SearchEnginePhone() throws InterruptedException{
        webDriver.manage().window().maximize();
        webDriver.manage().window().setSize(new Dimension(400, 701));
        webDriver.get(baseUrl);
        Thread.sleep(20000);
        WebElement hambugrgerbar = webDriver.findElement(By.id("hamburger-btn-open"));
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        WebElement searchButton = webDriver.findElement(By.cssSelector("button[type=submit]"));
        hambugrgerbar.click();
        Thread.sleep(1500);
        searchbar.click();
        searchbar.sendKeys("FK Sarajevo");
        Thread.sleep(500);
        searchButton.click();
        Thread.sleep(2000); // -- Waiting so the site can load ( we will change to wait command )
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "FK Sarajevo - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
        //Failed test when we make the screen smaller the Search engine bar DISAPEARS and not able to use it

    }
}
