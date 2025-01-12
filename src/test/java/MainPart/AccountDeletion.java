package MainPart;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CookieUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountDeletion {
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
    public void testDeleteAccount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']")));
        LoginIcon.click();
        WebElement email = webDriver.findElement(By.name("email"));
        WebElement password = webDriver.findElement(By.name("password"));
        email.sendKeys("FKSarajevo99");
        password.sendKeys("Banana2309");
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]")));
        loginButton.click();
        WebElement LoginHead = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-profile-btn")));
        LoginHead.click();
        WebElement head = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("POSTAVKE")));
        head.click();
        WebElement obrisi = webDriver.findElement(By.id("remove-profile-btn"));
        obrisi.click();
        Alert alter = webDriver.switchTo().alert();
        alter.accept();
        Thread.sleep(600);
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bg-green-100")));
        WebElement alertText = alert.findElement(By.xpath(".//span[contains(text(), 'Uspješno ste obrisali svoj nalog')]"));
        assertEquals("Uspješno ste obrisali svoj nalog.", alertText.getText(), "Alert message does not match expected text.");
    }
}
