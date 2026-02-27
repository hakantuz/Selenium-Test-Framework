package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class RegisterPage {

    // Karargahın dürbününü (Driver) bu sayfaya odaklıyoruz
    public RegisterPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Gerçek bir test sitesinin (örn: automationexercise.com) form kutuları
    @FindBy(xpath = "//input[@id='username']")
    public WebElement usernameKutusu;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement sifreKutusu;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    public WebElement sifreTekrarKutusu;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement registerButton;

    @FindBy(xpath = "//div[@id='flash']")
    public WebElement basariliKayitYazisi;

}
