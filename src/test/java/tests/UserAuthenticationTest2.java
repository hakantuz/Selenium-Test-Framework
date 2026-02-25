package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HerokuAppPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class UserAuthenticationTest2 extends TestBaseRapor {


    // Bu metod bize 4 farklı kullanıcı adı ve şifre kombinasyonu sağlayacak.
    @DataProvider(name = "kullaniciSifreCephaneligi")
    public Object[][] veriGonder() {
        return new Object[][] {
                {"hako", "12345"},                      // 1.Yanlış)
                {"Kahin", "Operasyon2026"},             // 2.(Yanlış)
                {"hackerGiris", "admin123"},            // 3.(Yanlış)
                {"tomsmith", "SuperSecretPassword!"}    // 4.(Doğru Kullanıcı)
        };
    }


    // dataProvider parametresi ile yukarıdaki cephaneliğin adını veriyoruz.
    // Metodun içine iki tane String (kullanıcı ve şifre) alacağını belirtiyoruz.
    @Test(dataProvider = "kullaniciSifreCephaneligi")
    public void cokluSizmaTesti(String gonderilecekKullanici, String gonderilecekSifre) {

        extentTest = extentReports.createTest("Çoklu Sızma Testi", "Kullanıcı: " + gonderilecekKullanici);

        extentTest.info("Hedef URL'ye gidiliyor...");
        Driver.getDriver().get(ConfigReader.getProperty("testUrl"));

        HerokuAppPage herokuPage = new HerokuAppPage();

        extentTest.info("Kullanıcı adı giriliyor: " + gonderilecekKullanici);
        herokuPage.username.sendKeys(gonderilecekKullanici); // SABİT YAZI DEĞİL, ŞARJÖRDEN GELEN VERİ

        extentTest.info("Şifre giriliyor...");
        herokuPage.password.sendKeys(gonderilecekSifre);     // SABİT YAZI DEĞİL, ŞARJÖRDEN GELEN VERİ

        extentTest.info("Login butonuna basılıyor...");
        herokuPage.login.click();

        // Operasyonun başarılı olup olmadığını URL veya Başlık ile teyit et
        extentTest.info("Girişin başarılı olup olmadığı doğrulanıyor...");

        // Eğer doğru şifreyse ("tomsmith" ise) sayfa değişecek, değilse aynı sayfada kalacak.
        // Biz burada basitçe sistemin çökmediğini (hata vermediğini) test etmiş oluyoruz.
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("secure") ||
                        Driver.getDriver().getCurrentUrl().contains("login"),
                "Sistem beklenmeyen bir tepki verdi!");

        extentTest.pass("Mermi hedefe ulaştı ve test hatasız tamamlandı.");
    }
}
