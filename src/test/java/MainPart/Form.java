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
import util.CookieUtils;

import java.io.File;
import java.time.Duration;

public class Form {
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
    public void FormComplient() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        WebElement button = webDriver.findElement(By.xpath("//span[text()='Forum']"));
        button.click();
        js.executeScript("window.scrollBy(0,3100)");
        WebElement contact = webDriver.findElement(By.xpath("//a[span[text()='Kontakt']]"));
        contact.click();
        WebElement email = webDriver.findElement(By.name("email"));
        WebElement name = webDriver.findElement(By.name("name"));
        WebElement subject = webDriver.findElement(By.name("subject"));
        WebElement messages = webDriver.findElement(By.name("message"));


        email.sendKeys("muhamadassaad72@gmail.com");
        name.sendKeys("Muhamad");
        subject.sendKeys("Loris Doris");
        messages.sendKeys("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat");
        WebElement button2 = webDriver.findElement(By.xpath("//button[@type='submit']"));
        //button2.click()
    }
}
