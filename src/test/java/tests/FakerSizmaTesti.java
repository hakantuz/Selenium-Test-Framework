package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

public class FakerSizmaTesti extends TestBaseRapor {

    @Test
    public void hayaletAjanKayitOperasyonu() {
        // Raporlamayı başlatıyoruz
        extentTest = extentReports.createTest("Faker Sızma Operasyonu", "Sisteme hayalet verilerle yeni kayıt açma");

        // Silahlarımızı (Objelerimizi) elimize alıyoruz
        Faker faker = new Faker();
        RegisterPage registerPage = new RegisterPage();

        // 1. HAYALET KİMLİĞİ OLUŞTURMA (Her çalışmada değişecek!)
        String username = faker.name().firstName().toLowerCase().replaceAll("[^a-z]", "") + faker.number().digits(3);
        String sifre = faker.internet().password(8, 12, true, true); // Güçlü şifre!

        extentTest.info("Hedef kayıt noktasına ilerleniyor...");
        // Örnek test kayıt sayfamıza gidiyoruz
        Driver.getDriver().get(ConfigReader.getProperty("registerUrl"));

        ReusableMethods.scrollIntoViewJS(registerPage.registerButton);

        // Eskiden ("Hakan") yazardık, artık dinamik ajanIsim değişkenini gönderiyoruz!
        registerPage.usernameKutusu.sendKeys(username);
        registerPage.sifreKutusu.sendKeys(sifre);
        registerPage.sifreTekrarKutusu.sendKeys(sifre);

        extentTest.info("Kayıt ol butonuna basılarak sisteme sızılıyor...");
        registerPage.registerButton.click();

        Assert.assertTrue(registerPage.basariliKayitYazisi.isDisplayed(), "Sızma BAŞARISIZ! Site bizi içeri almadı.");

        // Eğer kod buraya kadar kızarmadan geldiyse, demek ki yazı göründü ve test gerçektir!
        extentTest.pass("Hedef başarıyla VURULDU! Yeni ajan sistemde %100 aktif.");

    }
}
