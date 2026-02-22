package tests;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriteTest {

    @Test
    public void excelYazdirmaTesti() throws IOException {
        // 1. Dosyamızın yerini gösteriyoruz
        String dosyaYolu = "src/test/resources/amazon_data.xlsx";

        // 2. Dosyayı önce OKUMA modunda açıyoruz (Çünkü mevcut yapıyı bozmadan üstüne ekleme yapacağız)
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);

        // 3. SİLAH DOLUYOR: İlk satıra (Index 0) gidip, 3. sütunu (Index 2) oluşturup başlık atıyoruz.
        // Unutma: Java'da sayma işlemi 0'dan başlar! (0=A, 1=B, 2=C sütunu)
        sheet.getRow(0).createCell(2).setCellValue("Test Sonucu");

        // 4. İkinci satıra (Index 1 - "Java" kelimesi var) gidip "PASSED" yazdırıyoruz
        sheet.getRow(1).createCell(2).setCellValue("PASSED");

        // 5. Üçüncü satıra (Index 2 - "Selenium" kelimesi var) gidip "FAILED" yazdırıyoruz
        sheet.getRow(2).createCell(2).setCellValue("FAILED");

        // 6. ATEŞLEME MEKANİZMASI: Dosyayı YAZMA (Kaydetme) modunda açıyoruz
        FileOutputStream fos = new FileOutputStream(dosyaYolu);

        // 7. Hafızada yaptığımız tüm bu "PASSED/FAILED" değişikliklerini gerçek dosyaya mühürlüyoruz
        workbook.write(fos);

        // 8. TEMİZLİK: Dosyaları kapatıyoruz. Bu yapılmazsa dosya bozulur veya kilitli kalır!
        fis.close();
        fos.close();
        workbook.close();

        System.out.println("Komutanım, Excel'e yazma işlemi başarıyla tamamlandı! Lütfen dosyayı kontrol edin.");
    }
}
