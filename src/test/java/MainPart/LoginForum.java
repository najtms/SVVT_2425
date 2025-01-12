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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginForum {
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
    public void testLogin() {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Prijava")));
        loginButton.click();
        WebElement username = webDriver.findElement(By.id("username"));
        WebElement password = webDriver.findElement(By.id("password"));
        username.sendKeys("studentdoo");
        password.sendKeys("Burch123");
        WebElement onlinestatus = webDriver.findElement(By.id("viewonline"));
        onlinestatus.click();
        WebElement login = webDriver.findElement(By.name("login"));
        login.click();
        WebElement usernameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.username")));
        assertEquals("studentdoo", usernameText.getText());
    }

    @Test
    public void testLoginWWrongPassword() {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Prijava")));
        loginButton.click();
        WebElement username = webDriver.findElement(By.id("username"));
        WebElement password = webDriver.findElement(By.id("password"));
        username.sendKeys("studentdoo");
        password.sendKeys("Burch12345");
        WebElement onlinestatus = webDriver.findElement(By.id("viewonline"));
        onlinestatus.click();
        WebElement login = webDriver.findElement(By.name("login"));
        login.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Upisao/la si netačnu šifru.",firstSentence);
    }

    @Test
    public void testLoginWNonExistingUsername() {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Prijava")));
        loginButton.click();
        WebElement username = webDriver.findElement(By.id("username"));
        WebElement password = webDriver.findElement(By.id("password"));
        username.sendKeys("burch203");
        password.sendKeys("Burch123");
        WebElement onlinestatus = webDriver.findElement(By.id("viewonline"));
        onlinestatus.click();
        WebElement login = webDriver.findElement(By.name("login"));
        login.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Upisao/la si netačno/neaktivno korisničko ime.",firstSentence);
    }


}
