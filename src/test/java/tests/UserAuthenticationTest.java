package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.HerokuAppPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;



public class UserAuthenticationTest extends TestBase {

    @Test
    public void basariliGirisTesti() {
        Driver.getDriver().get(ConfigReader.getProperty("testUrl"));

        HerokuAppPage herokuAppPage = new HerokuAppPage();

        // Elementleri bul ve hamle yap
        herokuAppPage.username.sendKeys("tomsmith");
        herokuAppPage.password.sendKeys("SuperSecretPassword!");
        herokuAppPage.login.click();

        String expectedTitle = "The Internet";
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertEquals(actualTitle, expectedTitle, "Başarısız! Başlıklar uyuşmuyor.");

    }

        @AfterMethod
        public void tearDown() {
            Driver.closeDriver();

            }
        }


