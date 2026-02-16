package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class AmazonPage {


    // Sayfadaki elemanları "tanıştırmak" için kullanılır.
    public AmazonPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Arama kutusunu buluyoruz
    @FindBy(id = "twotabsearchtextbox")
    public WebElement aramaKutusu;

    // Arama butonunu buluyoruz
    @FindBy(id = "nav-search-submit-button")
    public WebElement aramaButonu;
}
