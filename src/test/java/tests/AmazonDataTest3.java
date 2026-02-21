package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.DataProviderUtils;
import utilities.Driver;

public class AmazonDataTest3 {

    @Test(dataProvider = "excelVerileri", dataProviderClass = DataProviderUtils.class)
    public void cokluAramaTesti(String aranacakKelime, String beklenenBaslik) {

        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        AmazonPage amazonPage = new AmazonPage();

        amazonPage.aramaKutusu.clear();
        amazonPage.aramaKutusu.sendKeys(aranacakKelime + Keys.ENTER);

        // Doğrulamayı da dinamik yaptık!
        Assert.assertTrue(Driver.getDriver().getTitle().contains(beklenenBaslik));

        System.out.println("Başarıyla Arandı ve Doğrulandı: " + aranacakKelime);
    }
}
