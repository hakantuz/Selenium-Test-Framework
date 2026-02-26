package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerTest {

    @Test
    public void sinirsizMermiTesti() {
        // Faker silahımızı (objemizi) elimize alıyoruz
        Faker faker = new Faker();

        // Şimdi sistemin bize rastgele ürettiği sahte istihbaratları görelim:
        String sahteIsim = faker.name().firstName();
        String sahteSoyisim = faker.name().lastName();
        String sahteEmail = faker.internet().emailAddress();
        String sahteSifre = faker.internet().password();
        String sahteTelefon = faker.phoneNumber().cellPhone();
        String sahteSehir = faker.address().city();

        // Üretilen bu mermileri konsola yazdırıp görelim:
        System.out.println("--- 🕵️ GİZLİ AJAN KİMLİĞİ OLUŞTURULDU ---");
        System.out.println("Ajan Adı      : " + sahteIsim + " " + sahteSoyisim);
        System.out.println("Ajan E-Maili  : " + sahteEmail);
        System.out.println("Ajan Şifresi  : " + sahteSifre);
        System.out.println("Ajan Telefonu : " + sahteTelefon);
        System.out.println("Görev Yeri    : " + sahteSehir);
        System.out.println("------------------------------------------");

        // NORMALDE BUNU SİTEYE ŞÖYLE GÖNDERİRSİN:
        // registerPage.isimKutusu.sendKeys(faker.name().firstName());
    }
}

