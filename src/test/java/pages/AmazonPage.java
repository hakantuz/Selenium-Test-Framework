package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.awt.*;

public class AmazonPage {

    // !!! EKSİK OLAN KISIM BURASI !!!
    // Bu Constructor (Kurucu Metot) olmazsa elementler NULL gelir.
    public AmazonPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Elementlerin burada durmaya devam etsin...
    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    public WebElement ddm;

    // Arama kutusunu buluyoruz
    @FindBy(id = "twotabsearchtextbox")
    public WebElement aramaKutusu;

    // Arama butonunu buluyoruz
    @FindBy(id = "nav-search-submit-button")
    public WebElement aramaButonu;

    // Arama yaptığımızda tırnak içinde çıkan "Java" yazısını yakalar.
    // Bu class genelde değişmez ve daha stabildir.
    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    public WebElement sonucYazisi;
}
