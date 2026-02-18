package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.ReusableMethods;
import utilities.TestBase;

public class AmazonScrollTest extends TestBase {

    @Test
    public void testScrollAndClick() {
        // 1. Amazon'a git
        driver.get(ConfigReader.getProperty("amazonUrl"));

        // 2. Sayfanın en altına inmemiz lazım.
        // "Back to top" (Başa dön) yazısını veya footer'daki bir linki hedef alalım.
        // Genelde sayfa sonundaki 'Kariyer' (Careers) linkini bulalım.

        // Not: Normalde bu elementi Page sayfasına (@FindBy ile) koyarız ama
        // hızlıca denemek için burada buluyoruz.
        WebElement careersLink = driver.findElement(By.xpath("//a[text()='Careers' or text()='Kariyer']"));

        // 3. Oraya kadar KAYDIR (Scroll) - JS Metodu Devrede!
        // Sayfa aşağı doğru akacak...
        ReusableMethods.scrollIntoViewJS(careersLink);

        // Biraz bekle ki gözümüzle kaydığını görelim (Yoksa çok hızlı olur)
        ReusableMethods.bekle(3);

        // 4. JS ile TIKLA (Zorla Tıkla) - JS Metodu Devrede!
        // Normal click bazen footer'da çalışmaz, bu affetmez.
        ReusableMethods.clickWithJS(careersLink);

        // 5. Gittiğimiz sayfanın başlığını yazdıralım
        ReusableMethods.bekle(2);
        System.out.println("Yeni Sayfa Başlığı: " + driver.getTitle());
    }
}

