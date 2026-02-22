package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class AmazonRaporTest extends TestBaseRapor {

    @Test
    public void basariliTest() {

        // 1. Raporun başlığını ve açıklamasını giriyoruz
        extentTest = extentReports.createTest("Başarılı Arama", "Amazon'da geçerli bir ürün aranmalı");

        // 2. Adım adım logluyoruz (Raporda saniye saniye görünecek)
        extentTest.info("Amazon anasayfasına gidiliyor.");
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));

        AmazonPage amazonPage = new AmazonPage();

        extentTest.info("Arama kutusuna 'Mekatronik' yazılıp ENTER'a basılıyor.");
        amazonPage.aramaKutusu.sendKeys("Mekatronik" + Keys.ENTER);

        extentTest.info("Sayfa başlığında 'Mekatronik' kelimesi doğrulandı.");
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Mekatronik"));

        // 3. Testin yeşil (Pass) bittiğini rapora bildiriyoruz
        extentTest.pass("Mekatronik araması başarıyla tamamlandı!");

    }

    @Test
    public void bilerekPatlatilanTest() {
        // Bu test, fotoğraf çekme yeteneğimizi kanıtlamak için tasarlandı!
        extentTest = extentReports.createTest("Hatalı Arama Simülasyonu", "Sistem patladığında fotoğraf çekebilmeli");

        extentTest.info("Amazon anasayfasına gidiliyor.");
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));

        extentTest.info("Arama yapmadan doğrudan saçma bir başlık bekleniyor (Test burada çökecek!).");

        // Amazon'un başlığında "Zehirlielma" yazmadığı için Assert patlayacak.
        // Patladığı an TestBaseRapor devreye girip fotoğrafı çekecek!
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Zehirlielma"));
    }
}
