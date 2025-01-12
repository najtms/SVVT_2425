package MainPart;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CookieUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Utakmice {
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
    public void NumberTesting() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); // We could of use Java to simulate a user typing!
        searchbar.sendKeys("Sarajevo");
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='autocomplete-suggestion' and contains(., 'FK Sarajevo')]")));
        suggestion.click();
        Thread.sleep(2000);
        System.out.println(webDriver.getCurrentUrl());
        js.executeScript("window.scrollBy(0,5500)");
        WebElement numberTwo = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("2")));
        numberTwo.click();
        WebElement textOne = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Policijske snage ispred zgrade FS BiH, hoće li biti kvoruma?")));
        assertEquals("Policijske snage ispred zgrade FS BiH, hoće li biti kvoruma?", textOne.getText());
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,2300)");
        WebElement numberlast = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("427")));
        numberlast.click();
        WebElement textTwo = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Šilić &quot;srušio&quot; prvaka")));
        assertEquals("Šilić &quot;srušio&quot; prvaka",textTwo.getText());

    }

    @Test
    public void PlayerTesting() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); // We could of use Java to simulate a user typing!
        searchbar.sendKeys("Sarajevo");
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='autocomplete-suggestion' and contains(., 'FK Sarajevo')]")));
        suggestion.click();
        WebElement Igraci = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("IGRAČI")));
        Igraci.click();
        Select select = new Select(webDriver.findElement(By.name("season_id")));
        select.selectByValue("2021/2022");
        WebElement player = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Varešanović Dal")));
        assertEquals("Varešanović Dal", player.getText());
        player.click();
        WebElement playernews = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("VIJESTI")));
        playernews.click();
        WebElement getText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-5xl.font-bold.leading-tight.dark\\:text-white")));
        assertEquals("Dal Varesanovic", getText.getText());
    }

    @Test
    public void GamesTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK ");
        Thread.sleep(500); // We could of use Java to simulate a user typing!
        searchbar.sendKeys("Sarajevo");
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='autocomplete-suggestion' and contains(., 'FK Sarajevo')]")));
        suggestion.click();
        WebElement Games = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("UTAKMICE")));
        Games.click();
        Select select = new Select(webDriver.findElement(By.name("season_id")));
        select.selectByValue("2021/2022");
        WebElement table = webDriver.findElement(By.xpath("//tbody//tr[10]//td[7]"));
        table.click();
        assertEquals("https://sportsport.ba/utakmica/fk-sarajevo-fk-zeljeznicar-10-kolo-wwin-liga-bih-20212022/55370", webDriver.getCurrentUrl());

    }
}
