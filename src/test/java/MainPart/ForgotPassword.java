package MainPart;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CookieUtils;

import java.io.File;
import java.time.Duration;

public class ForgotPassword {
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
    public void ForgotPassword()throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(4));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        WebElement forgotPassword = webDriver.findElement(By.linkText("Zaboravili ste lozinku?"));
        forgotPassword.click();
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        email.click();
        email.sendKeys("Muhamadassaad72@gmail.com");
        WebElement button = webDriver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        button.click();
        Thread.sleep(15000);
        webDriver.navigate().refresh();
        WebElement email1 = webDriver.findElement(By.id("email"));
        email1.click();
        email1.sendKeys("Muhamadassaad72@gmail.com");
        WebElement password = webDriver.findElement(By.name("password"));
        WebElement password_confirmation = webDriver.findElement(By.id("password_confirmation"));
        password.click();
        password.sendKeys("Burch200300400");
        password_confirmation.click();
        password.sendKeys("Burch200300400");
        WebElement button2 = webDriver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        button2.click();

    }

}
