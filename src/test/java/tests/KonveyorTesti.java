package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class KonveyorTesti {

    // --- PLC MANTIĞINI SİMÜLE EDEN METOT ---
    // Bu metot, sahadaki o Ladder Diyagramının (Network 40) Javacasıdır.
    public boolean konveyorCalisabilirMi(boolean yikamaMakinesiCalisiyorMu,
                                         boolean acilStopBasiliMi,
                                         double kantarAgirligi,
                                         boolean sensor13_DoluMu) {

        // 1. GÜVENLİK KİLİTLERİ (Safety Interlocks)
        // Ladder'daki kapalı kontaklar (!acilStopBasiliMi)
        if (acilStopBasiliMi) {
            System.out.println("HATA: Acil Stop basılı! Sistem kilitlendi.");
            return false;
        }

        if (!yikamaMakinesiCalisiyorMu) {
            System.out.println("UYARI: Yıkama makinesi çalışmıyor. Bant beklemeye alındı.");
            return false;
        }

        // 2. KANTAR KONTROLÜ (Critical Quality Check)
        // PLC'deki "== Real 0.0" bloğu
        if (kantarAgirligi > 0.0) {
            System.out.println("DUR: Kantarda malzeme var (" + kantarAgirligi + " kg). Yeni mal beslenemez!");
            return false;
        }

        // 3. SENSÖR TETİĞİ (Trigger)
        // PRX13TD sensörü malzeme gördü mü?
        if (sensor13_DoluMu) {
            System.out.println("ONAY: Tüm şartlar sağlandı. Motor Sürücüsüne (ABB) start veriliyor...");
            return true; // Motor Döner
        } else {
            System.out.println("BEKLEMEDE: Bant boş, malzeme bekleniyor.");
            return false;
        }
    }

    // --- TEST SENARYOLARI (QA Operasyonu) ---

    @Test
    public void pozitifSenaryo_HerseyYolunda() {
        System.out.println("--- SENARYO 1: Mükemmel Akış ---");
        // Şartlar: Yıkama çalışıyor, Acil Stop YOK, Kantar BOŞ (0.0), Sensör DOLU
        boolean sonuc = konveyorCalisabilirMi(true, false, 0.0, true);

        // Beklenti: True (Çalışmalı)
        Assert.assertTrue(sonuc, "Sistem çalışması gerekirken durdu!");
    }

    @Test
    public void negatifSenaryo_KantarDolu() {
        System.out.println("\n--- SENARYO 2: Kantar Dolu Hatası ---");
        // Şartlar: Yıkama çalışıyor ama KANTARDA 15.5 KG YÜK VAR!
        boolean sonuc = konveyorCalisabilirMi(true, false, 15.5, true);

        // Beklenti: False (Çalışmamalı)
        Assert.assertFalse(sonuc, "Kantar doluyken bant çalıştı! KAZA RİSKİ!");
    }

    @Test
    public void negatifSenaryo_AcilStop() {
        System.out.println("\n--- SENARYO 3: Acil Durum Testi ---");
        // Şartlar: Her şey mükemmel ama BİRİ BUTONA BASMIŞ!
        boolean sonuc = konveyorCalisabilirMi(true, true, 0.0, true);

        // Beklenti: False (Asla çalışmamalı)
        Assert.assertFalse(sonuc, "Acil stopa rağmen sistem durmadı! KRİTİK HATA!");
    }
}