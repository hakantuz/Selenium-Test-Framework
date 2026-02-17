package tests; // Paket ismin neyse o

import org.testng.annotations.Test;
import pages.AmazonPage; // Sayfa klasörün
import utilities.ConfigReader;
import utilities.TestBase; // Yeni yazdığımız sınıf

// extends TestBase diyerek "Senin baban TestBase'dir, onun özelliklerini kullan" dedik.
public class AmazonTest extends TestBase {

    @Test
    public void test01() {
        // Artık driver = ... demene gerek yok! TestBase halletti.
        // Direkt sürüyoruz:

        driver.get(ConfigReader.getProperty("amazonUrl")); // ConfigReader'ı da kullandık

        AmazonPage amazonPage = new AmazonPage();
        amazonPage.aramaKutusu.sendKeys(ConfigReader.getProperty("aramaKelimesi")); // Samsung
        amazonPage.aramaButonu.click();
    }
}
