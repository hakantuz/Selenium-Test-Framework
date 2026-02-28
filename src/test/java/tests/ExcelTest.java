package tests;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelTest {

    @Test
    public void excelOkumaTesti() throws IOException {

        // 1. ADIM: DOSYA YOLUNU TANIMLA (İstihbaratın Yeri)
        // Dosyayı projenin ana klasörüne (2026) attığımız için yolu direkt ismidir.
        String dosyaYolu = "KullaniciVerileri.xlsx";

        // 2. ADIM: DOSYAYA GİDEN BİR TÜNEL AÇ (FileInputStream)
        FileInputStream fis = new FileInputStream(dosyaYolu);

        // 3. ADIM: JAVA'DA EXCEL DOSYASINI YARAT (Workbook)
        // Bu satır, o tünelden gelen veriyi Java'nın anlayacağı Excel kitabına çevirir.
        Workbook workbook = WorkbookFactory.create(fis);

        // 4. ADIM: SAYFAYI SEÇ (Sheet)
        // Excel'in altındaki "Sayfa1" (veya Sheet1) sekmesine gidiyoruz.
        // DİKKAT: Senin Excel'inde sayfa adı neyse onu yazmalısın! (Genelde Sayfa1'dir)
        Sheet sayfa = workbook.getSheet("Sayfa1");

        // 5. ADIM: SATIR VE HÜCREYİ BUL (Row & Cell)
        // Excel'de indeksler 0'dan başlar!
        // 1. Satır -> Row 0
        // 1. Sütun (A) -> Cell 0

        // Hedef: 2. Satır, 1. Sütun'daki (A2) veriyi okumak istiyoruz ("manager")
        Row satir = sayfa.getRow(1); // 2. satır (indeks 1)
        Cell hucre = satir.getCell(0); // A sütunu (indeks 0)

        // 6. ADIM: İSTİHBARATI YAZDIR
        System.out.println("--- GİZLİ EXCEL İSTİHBARATI ---");
        System.out.println("Bulunan Veri: " + hucre.toString());

        // Operasyon bitti, tüneli kapat.
        workbook.close();
        fis.close();
    }
}
