import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Selenium {
    private static WebDriver webDriver;
    private static String baseUrl;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/muhamadassaad/chromedriver-mac-x64/chromedriver"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://sportsport.ba/";
    }
    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    // ovdde svoj test
    @Test
    public void testLoginwLogout() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));

        // Can I use an adblocker?Probobly not
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        try {
            WebElement SkipAdd = waitAdd.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#dismiss-button.btn.skip")));
            SkipAdd.click();
            System.out.println("Reklama Nestala");
        } catch (TimeoutException e) {
            System.out.println("Reklama nije bila");
        }
        WebElement email = webDriver.findElement(By.name("email"));
        WebElement password = webDriver.findElement(By.name("password"));
        email.sendKeys("muhamadassaad72@gmail.com");
        password.sendKeys("Burch123");
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        loginButton.click();
        WebElement LoginHead = webDriver.findElement(By.id("user-profile-btn"));
        LoginHead.click();
        WebElement Logout = webDriver.findElement(By.linkText("ODJAVA"));
        Logout.click();
    }
    @Test
    public void testWrongPassword() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));

        // Can I use an adblocker?Probobly not
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        try {
            WebElement SkipAdd = waitAdd.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#dismiss-button.btn.skip")));
            SkipAdd.click();
            System.out.println("Reklama Nestala");
        } catch (TimeoutException e) {
            System.out.println("Reklama nije bila");
        }
        WebElement email = webDriver.findElement(By.name("email"));
        WebElement password = webDriver.findElement(By.name("password"));
        email.sendKeys("IBU@ibu.com");
        password.sendKeys("Burch123");
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        loginButton.click();
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Korisničko ime ili lozinka nisu odgovarajući.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške. ");
    }
}


