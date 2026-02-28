package tests;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelLoginTesti {

    @Test
    public void cokluGirisTesti() throws IOException {

        // 1. İSTİHBARAT HAZIRLIĞI (EXCEL BAĞLANTISI)
        String dosyaYolu = "KullaniciVerileri.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sayfa = workbook.getSheet("Sayfa1"); // Excel'indeki sayfa adı!

        // Siteye intikal ediyoruz
        Driver.getDriver().get(ConfigReader.getProperty("expandtestingUrl"));

        // Toplam kaç ajan (satır) var?
        int sonSatir = sayfa.getLastRowNum();

        System.out.println("--- OTOMATİK GİRİŞ OPERASYONU BAŞLIYOR ---");

        // 2. DÖNGÜ (HER SATIR İÇİN BİR KEZ ÇALIŞACAK)
        // i=1'den başlıyoruz çünkü 0. satırda başlıklar (username/password) var.
        for (int i = 1; i <= sonSatir; i++) {

            // --- A) VERİYİ ÇEK ---
            Row satir = sayfa.getRow(i);
            String kAdi = satir.getCell(0).toString(); // A sütunu
            String sifre = satir.getCell(1).toString(); // B sütunu

            System.out.println(i + ". Ajan deneniyor: " + kAdi);

            // --- B) SİLAHLARI HEDEFLE ---
            // (Normalde Page sınıfı kullanırız ama hızlı olsun diye buraya yazdım)
            WebElement usernameKutusu = Driver.getDriver().findElement(By.id("username"));
            WebElement passwordKutusu = Driver.getDriver().findElement(By.id("password"));
            WebElement loginButonu = Driver.getDriver().findElement(By.xpath("//button[@type='submit']"));

            // --- C) ATEŞLE! ---
            usernameKutusu.clear(); // Önceki yazı varsa temizle
            usernameKutusu.sendKeys(kAdi); // Excel'den gelen ismi yaz

            passwordKutusu.clear();
            passwordKutusu.sendKeys(sifre); // Excel'den gelen şifreyi yaz

            // Bekle ki gözle görelim (ReusableMethods kullanıyoruz)
            ReusableMethods.bekle(1);

            // loginButonu.click();
            // NOT: Butona basarsak sayfa değişir, geri gelmekle uğraşmayalım.
            // Şimdilik sadece verileri kutulara yazıp sildiğimizi görelim.

            System.out.println("-> Kutulara yazıldı, sıradaki ajana geçiliyor...");
        }

        System.out.println("--- OPERASYON TAMAMLANDI ---");

        // Tünelleri kapat
        workbook.close();
        fis.close();
        Driver.closeDriver();
    }
}