package utilities;

import org.testng.annotations.DataProvider;

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
}
