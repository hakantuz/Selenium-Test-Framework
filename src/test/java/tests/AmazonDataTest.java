package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBase;

public class AmazonDataTest extends TestBase {

    // 1. Veri Depomuz
    // Bu metot bize testte kullanacağımız kelimeleri sırayla verecek.
    @DataProvider(name = "aranacakKelimeler")
    public Object[][] aranacakKelimeler() {
        return new Object[][] {
                {"Java"},
                {"Selenium"},
                {"TestNG"}
        };
    }

    // 2. Asıl Testimiz
    // dataProvider parametresi ile yukarıdaki depoyu bu teste bağlıyoruz.
    @Test(dataProvider = "aranacakKelimeler")
    public void cokluAramaTesti(String aranacakKelime) {

        // 1. Amazon'a git
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));

        // 2. Sayfa objesini oluştur
        AmazonPage amazonPage = new AmazonPage();

        // 3. Arama kutusunu temizle (Önceki aramadan kalanları silmek için önemli!)
        amazonPage.aramaKutusu.clear();

        // 4. Bize gelen kelimeyi yaz ve ENTER'a bas
        amazonPage.aramaKutusu.sendKeys(aranacakKelime + Keys.ENTER);

        // 5. Sayfa başlığının aradığımız kelimeyi içerdiğini doğrula
        Assert.assertTrue(Driver.getDriver().getTitle().contains(aranacakKelime));

        // Konsola da yazdıralım ki çalıştığını görelim
        System.out.println("Aranan Kelime Test Edildi: " + aranacakKelime);
    }
}
