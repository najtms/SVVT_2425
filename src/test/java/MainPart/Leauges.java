package MainPart;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CookieUtils;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leauges {
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
    public void testLeauges() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        CookieUtils.loadCookies(webDriver, "cookies.data");
        WebElement button = webDriver.findElement(By.id("user-profile-btn"));
        button.click();
        Thread.sleep(2000);
        WebElement LIGE = webDriver.findElement(By.linkText("MOJE LIGE"));
        LIGE.click();
        Thread.sleep(2000);
        Select Sport = new Select(webDriver.findElement(By.id("input-sport")));
        Sport.selectByVisibleText("Fudbal");
        Thread.sleep(2000);
        Select Drzava = new Select(webDriver.findElement(By.id("input-residence")));
        Drzava.selectByValue("52");
        Thread.sleep(2000);
        Select LIGA = new Select(webDriver.findElement(By.name("tournament_id")));
        LIGA.selectByValue("192");
        WebElement button1 = webDriver.findElement(By.xpath("//div[@class='flex items-center mt-10']"));
        button1.click();
    }

    @Test
    public void testLeaugesRemove() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        CookieUtils.loadCookies(webDriver, "cookies.data");
        WebElement button = webDriver.findElement(By.id("user-profile-btn"));
        button.click();
        Thread.sleep(2000);
        WebElement LIGE = webDriver.findElement(By.linkText("MOJE LIGE"));
        LIGE.click();
        Thread.sleep(2000);
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.remove-favourite")));
        removeButton.click();
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.bg-green-100")));
        assertEquals("Uspjeh! Uspješno ste uklonili favorita.",text.getText());
    }

    }
