package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ReusableMethods {

    // 1. Bekleme Metodu (Hard Wait)
    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // 2. Görünür Olana Kadar Bekle (Explicit Wait)
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(utilities.Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // 3. Tıklanabilir Olana Kadar Bekle ve Tıkla
    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (Exception e) {
                bekle(1);
            }
        }
    }

    // 4. JavaScript ile Tıklama (Selenium tıklayamazsa bu zorla tıklar!)
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) utilities.Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    // 5. Sayfayı Aşağı Kaydırma (Scroll Into View)
    public static void scrollIntoViewJS(WebElement element) {
        ((JavascriptExecutor) utilities.Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
