package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBaseRapor {

    // Raporlama Motorları
    protected static ExtentReports extentReports; // Raporu ana hatlarıyla oluşturur
    protected static ExtentTest extentTest; // Test adımlarını (Pass/Fail) kaydeder
    protected static ExtentHtmlReporter extentHtmlReporter; // Raporu HTML'e çevirir

    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        extentReports = new ExtentReports();

        // Raporun kaydedileceği dosya yolu ve adı
        String tarih = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String dosyaYolu = System.getProperty("user.dir") + "/test-output/Rapor_" + tarih + ".html";

        extentHtmlReporter = new ExtentHtmlReporter(dosyaYolu);
        extentReports.attachReporter(extentHtmlReporter);

        // Raporda görünecek Şirket / Proje Bilgileri (Patronlar buraya bayılır)
        extentReports.setSystemInfo("Environment", "QA Ortamı");
        extentReports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("Otomasyon Mühendisi", "Hakan"); // İmzanı atıyorsun!
        extentHtmlReporter.config().setDocumentTitle("Amazon Otomasyon Raporu");
        extentHtmlReporter.config().setReportName("Regresyon Test Sonuçları");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Eğer test başarısız olursa:
            String screenshotLocation = ReusableMethods.getScreenshot(result.getName());
            extentTest.fail(result.getName()); // Rapora "Başarısız" yaz
            extentTest.addScreenCaptureFromPath(screenshotLocation); // Kanıtı (Ekran Görüntüsünü) rapora ekle
            extentTest.fail(result.getThrowable()); // Hata logunu rapora ekle
        } else if (result.getStatus() == ITestResult.SKIP) {
            // Eğer test atlanırsa:
            extentTest.skip("Test Atlandı: " + result.getName());
        }

        Driver.closeDriver();
    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        // Raporu bitir ve dosyaya kaydet (Bu satır olmazsa rapor boş çıkar!)
        extentReports.flush();
    }
}
