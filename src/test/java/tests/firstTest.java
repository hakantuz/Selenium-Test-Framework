package tests;

import org.junit.Test; // Kırmızı yanarsa üzerine gelip "Add JUnit 4 to classpath" de.
import utilities.Driver;

public class firstTest {

    @Test
    public void amazonTesti() {
        // 1. Driver'ı çağır ve Amazon'a git
        Driver.getDriver().get("https://www.amazon.com");

        // 2. Gittiğini konsola yazdır
        System.out.println("Site başlığı: " + Driver.getDriver().getTitle());

        // 3. Kapat
        Driver.closeDriver();
    }
}
