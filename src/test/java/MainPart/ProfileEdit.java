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
import org.openqa.selenium.support.ui.Select;
import util.CookieUtils;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileEdit {
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
    public void ProfileEdit() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        CookieUtils.loadCookies(webDriver, "cookies.data");
        Thread.sleep(2000);
        WebElement LoginHead = webDriver.findElement(By.id("user-profile-btn"));
        LoginHead.click();
        WebElement editprofile = webDriver.findElement(By.linkText("UREDI PROFIL"));
        editprofile.click();
        js.executeScript("window.scrollBy(0,650)");
        Thread.sleep(250);
        WebElement NameNSurname = webDriver.findElement(By.name("name"));
        NameNSurname.click();
        NameNSurname.clear();
        NameNSurname.sendKeys("test");
        //
        WebElement genderDropdown = webDriver.findElement(By.name("gender"));
        Select select1 = new Select(genderDropdown);
        select1.selectByIndex(2); // Musko
        //
        WebElement birthDropdown = webDriver.findElement(By.name("year"));
        Select select2 = new Select(birthDropdown);
        select2.selectByValue("2004"); // 2003 value - we can change to anything that is in the list
        //
        WebElement CountryDropdown = webDriver.findElement(By.name("country_id"));
        Select select3 = new Select(CountryDropdown);
        select3.selectByIndex(26); // Drzava
        Thread.sleep(2000);
        //
        WebElement CantonDropdown = webDriver.findElement(By.name("canton_id"));
        Select select4 = new Select(CantonDropdown);
        select4.selectByIndex(2); // Canton
        //
        Thread.sleep(4000);
        WebElement forumName = webDriver.findElement(By.id("input-forum_username"));
        forumName.click();
        forumName.clear();
        forumName.sendKeys("NullOne");
        //
        WebElement WWINname = webDriver.findElement(By.name("betting_username"));
        WWINname.click();
        WWINname.clear();
        WWINname.sendKeys("NullOne");
        //
        WebElement buttonByType = webDriver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        buttonByType.click();
        WebElement alert = webDriver.findElement(By.className("bg-green-100"));
        String alertText = alert.getText();
        String expectedText = "Uspjeh! Korisnički profil je uspješno ažuriran.";
        assertEquals(expectedText, alertText,"Failed");

    }
}
