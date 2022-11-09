import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class EvgeniaVinnichekTest {

    // TC_1_1 - Тест кейс
    // //1. ОТкрыть страницу https://openweathermap.org/
    // //2. Набрать в строке поиска город Paris
    // //3. Нажать пункт меню Search
    // //4. Из выпадающего списка выбрать Paris , FR
    // //5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url); // открытие страницы браузера
        Thread.sleep(5000); // задержка браузера и закрытие

        WebElement searchCityField = driver.findElement( //находим нужный элемент
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        searchCityField.click(); // клик по нужному полю
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        Thread.sleep(2000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);


//        Thread.sleep(5000);


        driver.quit();
        //driver.close();
    }


    /* TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что title этой страницы
     * OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void testH2TagText_WhenSearchingGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResultUrl = "https://openweathermap.org/guide";

        driver.get(url); // открытие страницы браузера
        Thread.sleep(5000); // задержка браузера и закрытие

        WebElement guideElementMenu = driver.findElement( //находим нужный элемент
                By.xpath("//a[@href='/guide']")
        );
        guideElementMenu.click(); // клик по нужному полю
        String actualResultUrl = driver.getCurrentUrl();
        String actualResultTitle = driver.getTitle();

        Assert.assertEquals(actualResultUrl, expectedResultUrl);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);

        driver.quit();
//        driver.close();
    }


    /* TC_11_02
     * 1. Открыть базовую ссылку
     * 2. Нажать на единицы измерения Imperial: °F, mph
     * 3. Подтвердить, что температура для города показана в Фарингейтах
     */
    @Test
    public void testImoerialFaren() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "°F";
        String fTempSymbol = "°F";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000); // задержка браузера и закрытие

        WebElement menuImperial =
                driver.findElement(By.xpath(
                        "//div[@class='switch-container']/div[@class='option']/folowing-sibling::div"
                ));
        menuImperial.click();

        WebElement tempF = driver.findElement(By.xpath(
                "//div[@class='current-temp']/span"
        ));

//        Boolean isTemperatureInFarenheit = ;

        String tempInF = tempF.getText();

        String actualResult = tempInF.substring((tempInF.length() - 2));

        Assert.assertTrue(tempF.getText().contains(fTempSymbol));

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }


    /* TC_11_03
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to
     * work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
     * You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test
    public void testApproveTwoButtomsInPanel() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";

        driver.get(url);
        Thread.sleep(5000);

        WebElement textElement = driver.findElement(
                By.className("stick-footer-panel__description")
        );

        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//button[text()='Allow all']")
        );

        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@href='/cookies-settings']")
        );

        Assert.assertEquals(buttonAllowAll.getText(), "Allow all");
        Assert.assertEquals(buttonManageCookies.getText(), "Manage cookies");
        Assert.assertEquals(textElement.getText(), expectedResult);

        driver.quit();
    }


    /* TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */

    @Test
    public void testThreeSubmenusAreDisplayed_WhenClickingSupportMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String expectedSubmenu1 = "FAQ";
        String expectedSubmenu2 = "How to start";
        String expectedSubmenu3 = "Ask a question";

        driver.get(baseURL);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement supportMenu = driver.findElement(
                By.id("support-dropdown")
        );
        supportMenu.click();
        Thread.sleep(1000);

        Assert.assertEquals(driver.findElements(
                By.xpath("//ul[@id = 'support-dropdown-menu']/li")).size(), 3);

        String actualSubmenu1 = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']/li[1]")).getText();
        String actualSubmenu2 = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']/li[2]")).getText();
        String actualSubmenu3 = driver.findElement(
                By.xpath("//ul[@id = 'support-dropdown-menu']/li[3]")).getText();

        Assert.assertEquals(actualSubmenu1, expectedSubmenu1);
        Assert.assertEquals(actualSubmenu2, expectedSubmenu2);
        Assert.assertEquals(actualSubmenu3, expectedSubmenu3);

        driver.quit();
    }


    /* TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */
    @Test
    public void testAskAQuestionTabFail () throws InterruptedException {
        System.setProperty ("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String expectedResult = "reCAPTCHA verification failed, please try again.";
        String email = "google@gmail.com";
        String message = "test test test";

        driver.manage().window().maximize();
        driver.get(baseURL);
        Thread.sleep(5000);

        WebElement supportMenu = driver.findElement(
                By.xpath ("//li[@class='with-dropdown']") // "//div[@id ='support-dropdown']"
        );
        supportMenu.click();

        WebElement askQuestion = driver.findElement(
                By.xpath ("//ul[@class='dropdown-menu dropdown-visible']//a[@href='https://home.openweathermap.org/questions']")
        );
        askQuestion.click();

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles()); // открывает новую вкладку // не понимаю
        driver.switchTo().window(tabs2.get(1)); // не понимаю

        WebElement emailField = driver.findElement(
                By.xpath("//input[@class='form-control string email required']")
        );
        emailField.click();
        emailField.sendKeys(email);

        WebElement selectFields = driver.findElement(
                By.xpath("//select[@class='form-control select required']")
        );
        selectFields.click();

        WebElement selectFieldChoice = driver.findElement(
                By.xpath("//option[@value='Sales']")
        );
        selectFieldChoice.click();

        WebElement messageField = driver.findElement(
                By.xpath("//textarea[@class='form-control text required']")
        );
        messageField.sendKeys(message);

        WebElement submitBottom = driver.findElement(
                By.xpath("//input[@data-disable-with='Create Question form']")
        );
        submitBottom.click();
        Thread.sleep(2000);

        WebElement reCaptchaText = driver.findElement(
                By.xpath("//div[@class='help-block']")
        );
        String actualResult = reCaptchaText.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


    /* TC_11_06
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
     * 4. Оставить пустым поле Email
     * 5. Заполнить поля  Subject, Message
     * 6. Подтвердить CAPTCHA
     * 7. Нажать кнопку Submit
     * 8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
     */
    @Test
    public void testAskAQuestion_CantBeBlank () throws InterruptedException {
        System.setProperty ("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String subject = "Other";
        String message = "Wish you were here";
        String expectedResult = "can't be blank";

        driver.get(baseURL);
        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement clickOnSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        clickOnSupport.click();

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(4000);
        WebElement selectSubmenu_AskAQuestion = driver.findElement(By.xpath(
                "//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']"));
        selectSubmenu_AskAQuestion.click();

        Thread.sleep(4500);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(3000);

        WebElement enterSubject = driver.findElement(By.xpath(
                "//select[@class='form-control select required']"));

        enterSubject.click();
        enterSubject.sendKeys(subject);
        Thread.sleep(4000);

        WebElement enterMessage = driver.findElement(By.xpath(
                "//textarea[@class='form-control text required']"));
        enterMessage.click();
        enterMessage.sendKeys(message);
        Thread.sleep(5000);


        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")
        ));

        WebElement enterCaptcha = driver.findElement(By.xpath(
//                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked rc-anchor-checkbox recaptcha-checkbox-clearOutline']")
                  "//div[@class='rc-anchor-center-container']")
        );
        enterCaptcha.click();
        Thread.sleep(8000);

        driver.switchTo().window(window2);

        WebElement pressSubmit = driver.findElement(By.xpath(
                "//input[@data-disable-with='Create Question form']")
        );
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']")
        );

        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


    /* TC_11_07
     * 1. Открыть базовую ссылку
     * 2. Нажать на единицы измерения Imperial: °F, mph
     * 3. Нажать на единицы измерения Metric: °C, m/s
     * 4. Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */
    @Test
    public void testChangeTemperature () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://openweathermap.org/";
        String tempC = "°C";

        driver.get(baseURL);
        Thread.sleep(2000);

        WebElement changeTempToF = driver.findElement(
                By.xpath("//div[@class='option'][contains(text(),'°F')]")
        );
        changeTempToF.click();
        Thread.sleep(4000);

        WebElement changeTempToC = driver.findElement(
                By.xpath("//div[@class='option'][contains(text(),'Metric: °C')]")
        );
        changeTempToC.click();
        Thread.sleep(2000);

        String temperatureC = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(),'°C']")
        ).getText();
        boolean actualResult = temperatureC.contains(tempC);
//        boolean expectedResult = temperatureC.contains(tempC);
        Assert.assertTrue(actualResult);

        driver.quit();
    }


    /* TC_11_08
     * 1. Открыть базовую ссылку
     * 2. Нажать на лого компании
     * 3. Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
     */
    @Test
    public void testReloadLogo () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String baseURL = "https://openweathermap.org/";

        driver.get(baseURL);
        Thread.sleep(4000);

        WebElement logo = driver.findElement(
                By.xpath("//img[@src='/themes/openweathermap/assets/img/logo_white_cropped.png']")
        );
        logo.click();
        Thread.sleep(2000);

        String expectedResult = "https://openweathermap.org/";
        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


    /* TC_11_09
     * 1. Открыть базовую ссылку
     * 2. В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */
    @Test
    public void testRome () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String baseURL = "https://openweathermap.org/";
        String expectedresultCity = "Rome";
        String search1 = "find";
        String search2 = "Rome";

        driver.get(baseURL);
        Thread.sleep(2000);

        WebElement searchNavigationBar = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//input[@type='text']")
        );
        searchNavigationBar.click();
        searchNavigationBar.sendKeys(expectedresultCity);
        searchNavigationBar.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        String strUrl = driver.getCurrentUrl();

        Boolean actualResultStrUrlBul;
        if (strUrl.contains(search1) && strUrl.contains(search2)) {
            actualResultStrUrlBul = true;
        } else {
            actualResultStrUrlBul = false;
        }

        Boolean expectedResult = strUrl.contains(search1) && strUrl.contains(search2);
        Assert.assertEquals(actualResultStrUrlBul, expectedResult);

        String actualResultSearchBar = driver.findElement(
                By.xpath("//input[@class]")
        ).getAttribute("value");

        Assert.assertEquals(actualResultSearchBar, expectedresultCity);

        driver.quit();
    }


    /* TC_11_10
     * 1. Открыть базовую ссылку
     * 2. Нажать на пункт меню API
     * 3. Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */
    @Test
    public void testDeskTopMenuClickAPIFind30Buttons1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();

        //Arrange
        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();
        int expectedResult = 30;
        Thread.sleep(5000);

        WebElement element = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        element.click();

        int countButton = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        int actualResult = countButton;

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();

    }
}