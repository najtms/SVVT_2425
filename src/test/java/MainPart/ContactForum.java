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
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CookieUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactForum {
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
    public void testSend() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("test123@gmail.com");
        WebElement name = webDriver.findElement(By.id("name"));
        name.sendKeys("test123");
        WebElement subject = webDriver.findElement(By.id("subject"));
        subject.sendKeys("test123");
        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("test123test123test123test123test123test123test123test123test123test123");
        WebElement submit = webDriver.findElement(By.id("submit"));
        //submit.click();
    }

    @Test
    public void testContactwithouEmail() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        js.executeScript("window.scrollBy(0,400)");
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        name.sendKeys("test123");
        WebElement subject = webDriver.findElement(By.id("subject"));
        subject.sendKeys("test123");
        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("test123test123test123test123test123test123test123test123test123test123");
        WebElement submit = webDriver.findElement(By.className("button1"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Adresa e-maila koju si upisao/la je prekratka.",firstSentence);
    }

    @Test
    public void testContactwithouUsername() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        js.executeScript("window.scrollBy(0,400)");
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("test123@gmail.com");
        WebElement subject = webDriver.findElement(By.id("subject"));
        subject.sendKeys("test123");
        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("test123test123test123test123test123test123test123test123test123test123");
        WebElement submit = webDriver.findElement(By.className("button1"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Upiši ime pošiljatelja/ice.",firstSentence);
    }

    @Test
    public void testContactwithouSubject() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        js.executeScript("window.scrollBy(0,400)");
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        email.sendKeys("test123@gmail.com");
        WebElement name = webDriver.findElement(By.id("name"));
        name.sendKeys("test123");
        WebElement message = webDriver.findElement(By.id("message"));
        message.sendKeys("test123test123test123test123test123test123test123test123test123test123");
        WebElement submit = webDriver.findElement(By.className("button1"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Upiši naslov [predmet] poruke.",firstSentence);
    }

    @Test
    public void testContactwithouMessage() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/",webDriver.getCurrentUrl());
        WebElement Contact = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fa-envelope")));
        js.executeScript("arguments[0].scrollIntoView();", Contact);
        Contact.click();
        js.executeScript("window.scrollBy(0,400)");
        WebElement name = webDriver.findElement(By.id("name"));
        name.sendKeys("test123");
        WebElement subject = webDriver.findElement(By.id("subject"));
        subject.sendKeys("test123");
        WebElement submit = webDriver.findElement(By.className("button1"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Upiši tekst [sadržaj] poruke.",firstSentence);
    }




}
