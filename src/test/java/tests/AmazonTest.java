package tests;

import org.junit.Test;
import pages.AmazonPage;
import utilities.Driver;

public class AmazonTest {

    @Test
    public void test01() {
        // 1. Sayfaya git
        Driver.getDriver().get("https://www.amazon.com.tr");

        // 2. Page sınıfından bir obje oluştur
        AmazonPage amazonPage = new AmazonPage();

        // 3. Artık sadece "nokta" koyarak elemanlara ulaşıyoruz
        amazonPage.aramaKutusu.sendKeys("iPhone 15");
        amazonPage.aramaButonu.click();
    }
}
