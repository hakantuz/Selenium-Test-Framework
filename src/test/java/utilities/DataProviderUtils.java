package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class DataProviderUtils {

    // DİKKAT: Başka sınıftan çağrılacağı için metot "static" olmalı!
    @DataProvider(name = "amazonKelimeleri")
    public static Object[][] kelimeDeposu() {
        return new Object[][] {
                {"Laptop", "Laptop"},
                {"Mouse", "Mouse"},
                {"Klavye", "Klavye"},
                {"Monitör", "Monitör"}
        };
    }

    // İleride buraya negatif test verileri, şifreler vs. için
    // başka DataProvider'lar da ekleyeceğiz. Hepsi burada duracak.

    // YENİ NESİL SİLAH: Excel'den Veri Çeken DataProvider! 🚀
    @DataProvider(name = "excelVerileri")
    public static Object[][] exceldenVeriAl() {

        // 1. Excel dosyamızın tam adresi (Resources klasöründeki konumu)
        String dosyaYolu = "src/test/resources/amazon_data.xlsx";
        Object[][] veriler = null;

        try {
            // 2. Java'ya "Bu dosyayı okumaya başla" diyoruz (FileInputStream)
            FileInputStream fis = new FileInputStream(dosyaYolu);

            // 3. Apache POI devreye giriyor: Dosyayı Excel (Workbook) olarak açıyor
            Workbook workbook = WorkbookFactory.create(fis);

            // 4. İlk sayfayı (Sayfa1 / Sheet1) alıyoruz (İndeks 0'dan başlar)
            Sheet sheet = workbook.getSheetAt(0);

            // 5. Excel'de kaç satır ve sütun dolu olduğunu buluyoruz
            int satirSayisi = sheet.getPhysicalNumberOfRows();
            int sutunSayisi = sheet.getRow(0).getLastCellNum();

            // 6. TestNG'ye göndereceğimiz 2 boyutlu diziyi hazırlıyoruz.
            // DİKKAT: -1 yapıyoruz çünkü en üstteki başlık satırını (AranacakKelime) test etmek istemiyoruz!
            veriler = new Object[satirSayisi - 1][sutunSayisi];

            // 7. Hücreleri tek tek dolaşıp (iç içe for döngüsü ile) verileri dizimize kopyalıyoruz
            for (int i = 1; i < satirSayisi; i++) { // i=1'den başlıyor (Başlığı atlıyor)
                for (int j = 0; j < sutunSayisi; j++) {
                    veriler[i - 1][j] = sheet.getRow(i).getCell(j).toString();
                }
            }

            // 8. İşimiz bitti, kapıları kapatıyoruz (Hafıza şişmesin diye)
            workbook.close();
            fis.close();

        } catch (IOException e) {
            System.out.println("Excel dosyası bulunamadı veya bozuk! Yolu kontrol edin.");
            e.printStackTrace();
        }

        // 9. Mermileri TestNG'ye yolluyoruz!
        return veriler;
    }
}
