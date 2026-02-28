package tests;

import org.junit.Test;
import utilities.ConfigReader;
import utilities.Driver;

public class FirstTest {

    @Test
    public void amazonTesti() {
        // Adresi artık properties dosyasından çağırıyoruz
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));

        System.out.println("Site başlığı: " + Driver.getDriver().getTitle());

        Driver.closeDriver();
    }
    }

