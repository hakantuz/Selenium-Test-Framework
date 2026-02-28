package tests;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelTest2 {

    @Test
    public void topluExcelOkuma() throws IOException {

        String dosyaYolu = "KullaniciVerileri.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sayfa = workbook.getSheet("Sayfa1");

        // 1. İSTİHBARAT SINIRINI BELİRLE
        // Excel'deki dolu olan son satırın indeksini buluyoruz.
        int sonSatirIndeksi = sayfa.getLastRowNum();

        System.out.println("--- TÜM AJAN LİSTESİ DÖKÜLÜYOR ---");

        // 2. DÖNGÜYÜ BAŞLAT (i=1'den başlıyoruz çünkü 0. satırda BAŞLIKLAR var)
        for (int i = 1; i <= sonSatirIndeksi; i++) {

            // O anki satırı (row) elimize alıyoruz
            Row siradakiSatir = sayfa.getRow(i);

            // A sütunundaki (0) Kullanıcı Adını al
            String kAdi = siradakiSatir.getCell(0).toString();

            // B sütunundaki (1) Şifreyi al
            String sifre = siradakiSatir.getCell(1).toString();

            // Raporla
            System.out.println("Sıra " + i + " -> Kullanıcı: " + kAdi + " | Şifre: " + sifre);
        }

        System.out.println("----------------------------------");

        workbook.close();
        fis.close();
    }
}
