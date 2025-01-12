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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationForum {
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
    public void testRegistrationForum() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/", webDriver.getCurrentUrl());
        WebElement registerbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registracija")));
        registerbutton.click();
        js.executeScript("scroll(0,300)");
        WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.name("agreed")));
        accept.click();
        js.executeScript("scroll(0,900)");
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        username.sendKeys("Master_Yi_DiamondONE");
        WebElement email = webDriver.findElement(By.id("email"));
        email.sendKeys("University_test_email@yahooo.com");
        WebElement password = webDriver.findElement(By.id("new_password"));
        WebElement password_conf = webDriver.findElement(By.id("password_confirm"));
        password.sendKeys("Burch123");
        password_conf.sendKeys("Burch123");
        Select timezone = new Select(webDriver.findElement(By.id("tz_date")));
        timezone.selectByIndex(17);
        WebElement timezoneElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone")));
        Select timezoneCity = new Select(timezoneElement);
        timezoneCity.selectByValue("Asia/Qatar");
        WebElement bot = webDriver.findElement(By.id("pf_registracija"));
        bot.sendKeys("7");
        // This part would be done using some sort of AI, or just reenginer because of lack of time didn't make
        // an API but surely possible to bypass using NEWER tech AI or a SIMPLE API.
        //WebElement conformioncode = webDriver.findElement(By.id("confirm_code"));
        //conformioncode.sendKeys("NESTO");
        Thread.sleep(10000); // SO we can manually type it in
        WebElement submit = webDriver.findElement(By.id("submit"));
        submit.click();
        Thread.sleep(10000);

    }

    @Test
    public void testRegistrationForumInvalidUsername() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/", webDriver.getCurrentUrl());
        WebElement registerbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registracija")));
        registerbutton.click();
        js.executeScript("scroll(0,300)");
        WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.name("agreed")));
        accept.click();
        js.executeScript("scroll(0,900)");
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        username.sendKeys("Zed1998!!![najbolji]");
        WebElement email = webDriver.findElement(By.id("email"));
        email.sendKeys("University_test_email@yahooo.com");
        WebElement password = webDriver.findElement(By.id("new_password"));
        WebElement password_conf = webDriver.findElement(By.id("password_confirm"));
        password.sendKeys("Burch123");
        password_conf.sendKeys("Burch123");
        Select timezone = new Select(webDriver.findElement(By.id("tz_date")));
        timezone.selectByIndex(17);
        WebElement timezoneElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone")));
        Select timezoneCity = new Select(timezoneElement);
        timezoneCity.selectByValue("Asia/Qatar");
        WebElement bot = webDriver.findElement(By.id("pf_registracija"));
        bot.sendKeys("7");
        // This part would be done using some sort of AI, or just reenginer because of lack of time didn't make
        // an API but surely possible to bypass using NEWER tech AI or a SIMPLE API.
        //WebElement conformioncode = webDriver.findElement(By.id("confirm_code"));
        //conformioncode.sendKeys("NESTO");
        Thread.sleep(10000); // SO we can manually type it in
        WebElement submit = webDriver.findElement(By.id("submit"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Korisničko ime sadrži nedopuštene znakove.", firstSentence);

    }

    @Test
    public void testRegistrationForuUsernameAlreadyExists() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/", webDriver.getCurrentUrl());
        WebElement registerbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registracija")));
        registerbutton.click();
        js.executeScript("scroll(0,300)");
        WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.name("agreed")));
        accept.click();
        js.executeScript("scroll(0,900)");
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        username.sendKeys("Muhamed");
        WebElement email = webDriver.findElement(By.id("email"));
        email.sendKeys("University_test_email@yahooo.com");
        WebElement password = webDriver.findElement(By.id("new_password"));
        WebElement password_conf = webDriver.findElement(By.id("password_confirm"));
        password.sendKeys("Burch123");
        password_conf.sendKeys("Burch123");
        Select timezone = new Select(webDriver.findElement(By.id("tz_date")));
        timezone.selectByIndex(17);
        WebElement timezoneElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone")));
        Select timezoneCity = new Select(timezoneElement);
        timezoneCity.selectByValue("Asia/Qatar");
        WebElement bot = webDriver.findElement(By.id("pf_registracija"));
        bot.sendKeys("7");
        // This part would be done using some sort of AI, or just reenginer because of lack of time didn't make
        // an API but surely possible to bypass using NEWER tech AI or a SIMPLE API.
        //WebElement conformioncode = webDriver.findElement(By.id("confirm_code"));
        //conformioncode.sendKeys("NESTO");
        Thread.sleep(10000); // SO we can manually type it in
        WebElement submit = webDriver.findElement(By.id("submit"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Korisničko ime koje si upisao/la već postoji.", firstSentence);

    }

    @Test
    public void testRegistrationForumNonMatchingPasswords() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/", webDriver.getCurrentUrl());
        WebElement registerbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registracija")));
        registerbutton.click();
        js.executeScript("scroll(0,300)");
        WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.name("agreed")));
        accept.click();
        js.executeScript("scroll(0,900)");
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        username.sendKeys("Master_Yi_DiamondONE");
        WebElement email = webDriver.findElement(By.id("email"));
        email.sendKeys("University_test_email@yahooo.com");
        WebElement password = webDriver.findElement(By.id("new_password"));
        WebElement password_conf = webDriver.findElement(By.id("password_confirm"));
        password.sendKeys("Burch123");
        password_conf.sendKeys("Burch123456789");
        Select timezone = new Select(webDriver.findElement(By.id("tz_date")));
        timezone.selectByIndex(17);
        WebElement timezoneElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone")));
        Select timezoneCity = new Select(timezoneElement);
        timezoneCity.selectByValue("Asia/Qatar");
        WebElement bot = webDriver.findElement(By.id("pf_registracija"));
        bot.sendKeys("7");
        // This part would be done using some sort of AI, or just reenginer because of lack of time didn't make
        // an API but surely possible to bypass using NEWER tech AI or a SIMPLE API.
        //WebElement conformioncode = webDriver.findElement(By.id("confirm_code"));
        //conformioncode.sendKeys("NESTO");
        Thread.sleep(10000); // SO we can manually type it in
        WebElement submit = webDriver.findElement(By.id("submit"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Zaporke koje si upisao/la se ne podudaraju.", firstSentence);

    }

    @Test
    public void testRegistrationForumTooShortPassword() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/", webDriver.getCurrentUrl());
        WebElement registerbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registracija")));
        registerbutton.click();
        js.executeScript("scroll(0,300)");
        WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.name("agreed")));
        accept.click();
        js.executeScript("scroll(0,900)");
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        username.sendKeys("Master_Yi_DiamondONE");
        WebElement email = webDriver.findElement(By.id("email"));
        email.sendKeys("University_test_email@yahooo.com");
        WebElement password = webDriver.findElement(By.id("new_password"));
        WebElement password_conf = webDriver.findElement(By.id("password_confirm"));
        password.sendKeys("X123");
        password_conf.sendKeys("X123");
        Select timezone = new Select(webDriver.findElement(By.id("tz_date")));
        timezone.selectByIndex(17);
        WebElement timezoneElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone")));
        Select timezoneCity = new Select(timezoneElement);
        timezoneCity.selectByValue("Asia/Qatar");
        WebElement bot = webDriver.findElement(By.id("pf_registracija"));
        bot.sendKeys("7");
        // This part would be done using some sort of AI, or just reenginer because of lack of time didn't make
        // an API but surely possible to bypass using NEWER tech AI or a SIMPLE API.
        //WebElement conformioncode = webDriver.findElement(By.id("confirm_code"));
        //conformioncode.sendKeys("NESTO");
        Thread.sleep(10000); // SO we can manually type it in
        WebElement submit = webDriver.findElement(By.id("submit"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("'Potvrda' šifre koju si upisao/la je prekratka.", firstSentence);

    }

    @Test
    public void testRegistrationForumalreadyExistsEmail() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/", webDriver.getCurrentUrl());
        WebElement registerbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registracija")));
        registerbutton.click();
        js.executeScript("scroll(0,300)");
        WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.name("agreed")));
        accept.click();
        js.executeScript("scroll(0,900)");
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        username.sendKeys("Master_Yi_DiamondONE");
        WebElement email = webDriver.findElement(By.id("email"));
        email.sendKeys("muhamadassaad72@gmail.com");
        WebElement password = webDriver.findElement(By.id("new_password"));
        WebElement password_conf = webDriver.findElement(By.id("password_confirm"));
        password.sendKeys("Burch123");
        password_conf.sendKeys("Burch123");
        Select timezone = new Select(webDriver.findElement(By.id("tz_date")));
        timezone.selectByIndex(17);
        WebElement timezoneElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone")));
        Select timezoneCity = new Select(timezoneElement);
        timezoneCity.selectByValue("Asia/Qatar");
        WebElement bot = webDriver.findElement(By.id("pf_registracija"));
        bot.sendKeys("7");
        // This part would be done using some sort of AI, or just reenginer because of lack of time didn't make
        // an API but surely possible to bypass using NEWER tech AI or a SIMPLE API.
        //WebElement conformioncode = webDriver.findElement(By.id("confirm_code"));
        //conformioncode.sendKeys("NESTO");
        //
        Thread.sleep(10000); // SO we can manually type it in
        WebElement submit = webDriver.findElement(By.id("submit"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Adresu elektroničke pošte koristi [prije] registriran/a korisnik/ca.", firstSentence);

    }

    @Test
    public void testRegistrationForumCHAPTER() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement forumButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-tb-region='Btn:Forum']")));
        forumButton.click();
        assertEquals("https://forum.sportsport.ba/", webDriver.getCurrentUrl());
        WebElement registerbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Registracija")));
        registerbutton.click();
        js.executeScript("scroll(0,300)");
        WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.name("agreed")));
        accept.click();
        js.executeScript("scroll(0,900)");
        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        username.sendKeys("Master_Yi_DiamondONE");
        WebElement email = webDriver.findElement(By.id("email"));
        email.sendKeys("University_test_email@yahooo.com");
        WebElement password = webDriver.findElement(By.id("new_password"));
        WebElement password_conf = webDriver.findElement(By.id("password_confirm"));
        password.sendKeys("Burch123");
        password_conf.sendKeys("Burch123");
        Select timezone = new Select(webDriver.findElement(By.id("tz_date")));
        timezone.selectByIndex(17);
        WebElement timezoneElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("timezone")));
        Select timezoneCity = new Select(timezoneElement);
        timezoneCity.selectByValue("Asia/Qatar");
        WebElement bot = webDriver.findElement(By.id("pf_registracija"));
        bot.sendKeys("7");
        // This part would be done using some sort of AI, or just reenginer because of lack of time didn't make
        // an API but surely possible to bypass using NEWER tech AI or a SIMPLE API.
        //WebElement conformioncode = webDriver.findElement(By.id("confirm_code"));
        //conformioncode.sendKeys("NESTO");
        Thread.sleep(10000); // SO we can manually type it in
        WebElement submit = webDriver.findElement(By.id("submit"));
        submit.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        String fullText = error.getText();
        String firstSentence = fullText.split("\\.")[0] + ".";
        assertEquals("Potvrdni kod koji si upisao/la je netačan.", firstSentence);
    }
}
