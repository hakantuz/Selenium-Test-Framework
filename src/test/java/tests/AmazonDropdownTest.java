package tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBase;

public class AmazonDropdownTest extends TestBase {

    @Test
    public void testDropdown() {
        // 1. Amazon'a git
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));

        // 2. Sayfa objesini oluştur
        AmazonPage amazonPage = new AmazonPage();

        // 3. Dropdown menüyü (ddm) seçmemiz lazım.
        // Selenium'da Dropdownlar "Select" class'ı ile yönetilir.
        Select select = new Select(amazonPage.ddm);

        // 4. "Books" (veya Kitaplar) seçeneğini seçelim
        // (Site İngilizceyse "Books", Türkçeyse "Kitaplar" yazmalısın index ile yapalım garanti olsun)
        // Index 5 genelde Elektronik veya Kitap olur, rastgele bir seçim yapıyoruz.
        select.selectByIndex(5);

        // 5. Seçtiğimiz şeyi yazdıralım (Konsolda görelim)
        System.out.println("Seçilen Kategori: " + select.getFirstSelectedOption().getText());

        // 6. KENDİ YAZDIĞIMIZ METODU KULLANALIM (Hard Wait)
        // Burada 3 saniye bekleyecek, hatasız, tertemiz.
        ReusableMethods.bekle(3);

        // 7. Arama kutusuna "Java" yazıp aratalım
        amazonPage.aramaKutusu.sendKeys("Java");
        amazonPage.aramaButonu.click();

        // 8. Sonuç yazısını doğrula (Assert)
        Assert.assertTrue(amazonPage.sonucYazisi.getText().contains("Java"));
    }
}
