package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AmazonPage;
import pages.HerokuAppPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;



public class UserAuthenticationTest extends TestBase {

    @Test
    public void basariliGirisTesti() {
        Driver.getDriver().get(ConfigReader.getProperty("gizlikarargahUrl"));

        // Elementleri bul ve hamle yap
        HerokuAppPage.
    }
}
