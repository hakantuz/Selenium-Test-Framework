package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class ApiTesti {

    @Test
    public void hayaletSizmaTesti() {

        // 1. Yeni Hedef (Senin az önce gittiğin adres)
        String url = "https://jsonplaceholder.typicode.com/users/1";

        System.out.println("--- API SIZMA İŞLEMİ BAŞLIYOR ---");

        // 2. İstek At (Kamuflajlı)
        Response cevap = given()
                .relaxedHTTPSValidation()  // "Güvenliği boşver, kapıyı kır gir!"
                .header("User-Agent", "Mozilla/5.0")
                .when()
                .get(url);

        // 3. Sonuçları Görelim
        System.out.println("Bağlantı Durumu: " + cevap.getStatusCode());
        cevap.prettyPrint();

        // 4. Doğrulama (Assertion)
        assertEquals(cevap.getStatusCode(), 200, "Sunucuya girilemedi!");

        // --- DÜZELTİLEN KISIM BURASI ---
        // Artık 'data.first_name' değil, direkt 'name' çağırıyoruz.
        String isim = cevap.jsonPath().getString("name");

        System.out.println("Tespit Edilen Ajan: " + isim);

        // Janet değil, Leanne Graham bekliyoruz
        assertEquals(isim, "Leanne Graham", "Yanlış kişi tespit edildi!");

        System.out.println("--- OPERASYON BAŞARIYLA TAMAMLANDI ---");
    }
}
