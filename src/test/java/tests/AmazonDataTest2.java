package tests;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.DataProviderUtils;
import utilities.Driver;
import utilities.TestBase;

public class AmazonDataTest2 extends TestBase {

    // DEPO: Artık her satırda 2 mermi var! { "Aranacak Kelime", "Beklenen Başlık" }
    @DataProvider(name = "aranacakKelimeler")
    public Object[][] aranacakKelimeler() {
        return new Object[][] {
                {"Java", "Java"},
                {"Selenium", "Selenium"},
                {"Nutella", "Nutella"} // Tatlı bir kapanış olsun :)
        };
    }

    // TEST: Depodan 2 mermi geldiği için, parantez içine 2 değişken koyduk.
    @Test(dataProvider = "amazonKelimeleri", dataProviderClass = DataProviderUtils.class)
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
