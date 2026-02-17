package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public abstract class TestBase {

    // "protected" yapıyoruz ki bu class'ı miras alanlar (child) görebilsin.
    protected WebDriver driver;
    protected Actions actions;

    // @BeforeMethod: Her @Test'ten ÖNCE otomatik çalışır.
    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    // @AfterMethod: Her @Test'ten SONRA otomatik çalışır.
    @AfterMethod
    public void tearDown() {
        // Driver.closeDriver(); // Şimdilik kapalı tutuyoruz, sonucu görelim diye.
    }
}
