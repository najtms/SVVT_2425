import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CookieUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Selenium {
    private static WebDriver webDriver;
    private static String baseUrl;


    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/muhamadassaad/chromedriver-mac-x64/chromedriver"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        baseUrl = "https://sportsport.ba/";
        ChromeOptions chromeOptions = new ChromeOptions();
        options.addExtensions(new File("/Users/muhamadassaad/chromedriver-mac-x64/AdBlock.crx"));  // Linija za adblocker obiris jer nemas adblocker
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Takoder i ove linije obrisi
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));// Takoder i ove linije obrisi
        webDriver.manage().window().maximize();// Takoder i ove linije obrisi
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
    // ovdde svoj test
    @Test
    public void testLoginwLogout(){
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));

        // Can I use an adblocker?Probobly not
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
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

    //Register
    @Test
    public void testRegister() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        try {
            WebElement SkipAdd = waitAdd.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#dismiss-button.btn.skip")));
            SkipAdd.click();
            System.out.println("Reklama Nestala");
        } catch (TimeoutException e) {
            System.out.println("Reklama nije bila");
        }
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("Papajapoppins");
        Email.sendKeys("jimodew295@chosenx.com");
        Password.sendKeys("Burch123");
        RePeat.sendKeys("Burch123");
        Thread.sleep(2000);
        try {
            // Switch to reCAPTCHA iframe
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));

            // Click reCAPTCHA checkbox
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();

            // Switch back to the main content
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Unable to handle reCAPTCHA: " + e.getMessage());
        }
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Thread.sleep(4000);
        Registerbutton.click();
        Thread.sleep(20000); // 20 Second to go to temp email and get user!
        WebElement email = webDriver.findElement(By.name("email"));
        WebElement password = webDriver.findElement(By.name("password"));
        email.sendKeys("jimodew295@chosenx.com");
        password.sendKeys("Burch123");
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        loginButton.click();
    }

    @Test
    public void testRegisterwEmailExists() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        try {
            WebElement SkipAdd = waitAdd.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#dismiss-button.btn.skip")));
            SkipAdd.click();
            System.out.println("Reklama Nestala");
        } catch (TimeoutException e) {
            System.out.println("Reklama nije bila");
        }
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("IlovemyMK4Golf");
        Email.sendKeys("muhamadassaad72@gmail.com");
        Password.sendKeys("Papajaman123");
        RePeat.sendKeys("Papajaman123");
        Thread.sleep(2000);
        try {
            // Switch to reCAPTCHA iframe
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));

            // Click reCAPTCHA checkbox
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();

            // Switch back to the main content
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Unable to handle reCAPTCHA: " + e.getMessage());
        }
        Thread.sleep(10000);
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        /////////////////////
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Polje email već postoji.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
    }

    @Test
    public void testRegisterwUsernameExists() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        try {
            WebElement SkipAdd = waitAdd.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#dismiss-button.btn.skip")));
            SkipAdd.click();
            System.out.println("Reklama Nestala");
        } catch (TimeoutException e) {
            System.out.println("Reklama nije bila");
        }
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("studentdoo");
        Email.sendKeys("muhamadassaad73@gmail.com");
        Password.sendKeys("Papajaman123");
        RePeat.sendKeys("Papajaman123");
        Thread.sleep(2000);
        try {
            // Switch to reCAPTCHA iframe
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));

            // Click reCAPTCHA checkbox
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();

            // Switch back to the main content
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Unable to handle reCAPTCHA: " + e.getMessage());
        }
        Thread.sleep(10000);
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        /////////////////////
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Odabrano korisničko ime već postoji. Odaberite neko drugo.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
    }

    @Test
    public void testRegisterwPasswordnotmatching() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        try {
            WebElement SkipAdd = waitAdd.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#dismiss-button.btn.skip")));
            SkipAdd.click();
            System.out.println("Reklama Nestala");
        } catch (TimeoutException e) {
            System.out.println("Reklama nije bila");
        }
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("IlovemyMK4Golf");
        Email.sendKeys("muhamadassaad73@gmail.com");
        Password.sendKeys("Sarajevo003");
        RePeat.sendKeys("003Sarajevo");
        Thread.sleep(2000);
        try {
            // Switch to reCAPTCHA iframe
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));

            // Click reCAPTCHA checkbox
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();

            // Switch back to the main content
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Unable to handle reCAPTCHA: " + e.getMessage());
        }
        Thread.sleep(10000);
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        /////////////////////
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Potvrda polja password se ne poklapa.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
    }

    @Test
    public void testRegisterwUsernamedoesntfollowrules() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        try {
            WebElement SkipAdd = waitAdd.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#dismiss-button.btn.skip")));
            SkipAdd.click();
            System.out.println("Reklama Nestala");
        } catch (TimeoutException e) {
            System.out.println("Reklama nije bila");
        }
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("I love my MK4 Golf ????");
        Email.sendKeys("muhamadassaad73@gmail.com");
        Password.sendKeys("Papajaman123");
        RePeat.sendKeys("Papajaman123");
        Thread.sleep(2000);
        try {
            // Switch to reCAPTCHA iframe
            webDriver.switchTo().frame(webDriver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));

            // Click reCAPTCHA checkbox
            WebElement recaptchaCheckbox = webDriver.findElement(By.cssSelector(".recaptcha-checkbox-border"));
            recaptchaCheckbox.click();

            // Switch back to the main content
            webDriver.switchTo().defaultContent();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Unable to handle reCAPTCHA: " + e.getMessage());
        }
        Thread.sleep(10000);
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        /////////////////////
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Korisničko ime sadrži nedozvoljene znakove. Dozovljeni su: slova (a-z, A-Z), brojevi (0-9), te \".\" i \"_\".";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
    }
    @Test
    public void testRegisterwRECAHPTER() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebDriverWait waitAdd = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        WebElement LoginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='/login']"))); // Loading start of website
        LoginIcon.click();
        try {
            WebElement SkipAdd = waitAdd.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#dismiss-button.btn.skip")));
            SkipAdd.click();
            System.out.println("Reklama Nestala");
        } catch (TimeoutException e) {
            System.out.println("Reklama nije bila");
        }
        WebElement Register = webDriver.findElement(By.linkText("REGISTRACIJA"));
        Register.click();
        WebElement username = webDriver.findElement(By.name("username"));
        WebElement Email = webDriver.findElement(By.name("email"));
        WebElement Password = webDriver.findElement(By.name("password"));
        WebElement RePeat = webDriver.findElement(By.name("password_confirmation"));
        username.sendKeys("VWGOLF4Karavan");
        Email.sendKeys("muhamadassaad73@gmail.com");
        Password.sendKeys("Papajaman123");
        RePeat.sendKeys("Papajaman123");
        WebElement Registerbutton = webDriver.findElement(By.xpath("//button[@class=\"bg-black bg-opacity-5 w-full hover:bg-red-600 hover:text-white px-4 py-2 font-trebuchet rounded-md text-md uppercase font-normal dark:bg-white dark:bg-opacity-20 h-10.5 flex justify-center items-center transition-all\"]"));
        Registerbutton.click();
        /////////////////////
        WebElement notmatch = webDriver.findElement(By.cssSelector("li"));
        String Greska = notmatch.getText();
        String greskakojuzelimo = "Polje g-recaptcha-response je obavezno.";
        assertEquals(greskakojuzelimo, Greska,"Desile su se sljedeće greške.");
    }

    //Search Engine
    @Test
    public void SearchEngineBasic() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("KK Igman ");
        Thread.sleep(500); // -- We wait so the Search engine can suggest the correc thing
        searchbar.sendKeys("Burch");
        Thread.sleep(2000); // -- Waiting so the site can load ( we will change to wait command )
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "KK Igman Burch - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
    }
    @Test
    public void SearchEngineCapitaleletters() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("kK iGMan ");
        Thread.sleep(500); // -- We wait so the Search engine can suggest the correc thing
        searchbar.sendKeys("buRCH");
        Thread.sleep(2000); // -- Waiting so the site can load ( we will change to wait command )
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "KK Igman Burch - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
    }
    @Test
    public void SearchEnginePaste() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        WebElement searchButton = webDriver.findElement(By.cssSelector("button[type=submit]"));
        searchbar.click();
        searchbar.clear();
        searchbar.sendKeys("FK Sarajevo");
        Thread.sleep(500);
        searchButton.click();
        Thread.sleep(2000); // -- Waiting so the site can load ( we will change to wait command )
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "FK Sarajevo - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");

        // Figure out what to test
    }
    @Test
    public void SearchEnginePhone() throws InterruptedException{
        webDriver.manage().window().maximize();
        webDriver.manage().window().setSize(new Dimension(400, 701));
        webDriver.get(baseUrl);
        Thread.sleep(20000);
        WebElement hambugrgerbar = webDriver.findElement(By.id("hamburger-btn-open"));
        WebElement searchbar = webDriver.findElement(By.cssSelector("input[type=text]"));
        WebElement searchButton = webDriver.findElement(By.cssSelector("button[type=submit]"));
        hambugrgerbar.click();
        Thread.sleep(1500);
        searchbar.click();
        searchbar.sendKeys("FK Sarajevo");
        Thread.sleep(500);
        searchButton.click();
        Thread.sleep(2000); // -- Waiting so the site can load ( we will change to wait command )
        String chucknoris = webDriver.getTitle();
        String ExpectedOutcome = "FK Sarajevo - SportSport.ba";
        assertEquals(ExpectedOutcome,chucknoris,"Failed");
        //Failed test when we make the screen smaller the Search engine bar DISAPEARS and not able to use it

    }

    // Cookies
    @Test
    public void CookieSave() throws InterruptedException, IOException {

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
        // Get cookies and save them to a file
        Set<Cookie> cookies = webDriver.manage().getCookies();
        File file = new File("cookies.data");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Cookie cookie : cookies) {
            bufferedWriter.write(cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";" +
                    cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();
    }
    @Test
    public void CookieLoad() throws InterruptedException, IOException {
        webDriver.get(baseUrl); // Replace with the actual URL
        webDriver.manage().window().maximize();
        Thread.sleep(2000);

        // Define the date format for the expiry string
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        // Read cookies from file
        File file = new File("cookies.data");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(";");

            // Parse cookie fields
            String name = parts[0];
            String value = parts[1];
            String domain = parts[2];
            String path = parts[3];
            Date expiry = null;

            if (!parts[4].equals("null")) {
                try {
                    expiry = dateFormat.parse(parts[4]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            boolean isSecure = Boolean.parseBoolean(parts[5]);

            // Build and add the cookie
            Cookie cookie = new Cookie.Builder(name, value)
                    .domain(domain)
                    .path(path)
                    .expiresOn(expiry)
                    .isSecure(isSecure)
                    .build();

            webDriver.manage().addCookie(cookie);
        }

        bufferedReader.close();
        fileReader.close();

        // Refresh the page to apply cookies
        webDriver.navigate().refresh();
    }
    // later have to finish

    //Profile
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
        NameNSurname.sendKeys("Fehrudin Baja");
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

    @Test
    public void ProfileEditRe() throws InterruptedException {
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
        WebElement birthDropdown = webDriver.findElement(By.name("year"));
        Select select2 = new Select(birthDropdown);
        select2.selectByIndex(0); // 2003 value - we can change to anything that is in the list
        //
        WebElement CountryDropdown = webDriver.findElement(By.name("country_id"));
        Select select3 = new Select(CountryDropdown);
        select3.selectByIndex(0); // Drzava
        Thread.sleep(2000);
        //
        WebElement buttonByType = webDriver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        buttonByType.click();
        WebElement alert = webDriver.findElement(By.className("bg-green-100"));
        String alertText = alert.getText();
        String expectedText = "Uspjeh! Korisnički profil je uspješno ažuriran.";
        assertEquals(expectedText, alertText,"Failed");

    }
    // testing in progress

    //Checks is it https or http

    @Test
    public void HttpsORHttp() throws InterruptedException {
        // Navigate to the URL
        webDriver.get(baseUrl); // Replace with the actual URL you want to check

        // Get the current URL
        String currentUrl = webDriver.getCurrentUrl();

        // Check if the URL is using HTTPS or HTTP
        if (currentUrl.startsWith("https://")) {
            System.out.println("The site is using HTTPS.");
        } else if (currentUrl.startsWith("http://")) {
            System.out.println("The site is using HTTP.");
        } else {
            System.out.println("The URL does not follow the standard HTTP or HTTPS protocol.");
        }
    }

    //Forgot password
    @Test
    public void ForgotPassword()throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(4));

        // Can I use an adblocker?Probobly not
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
    // DONE


}


