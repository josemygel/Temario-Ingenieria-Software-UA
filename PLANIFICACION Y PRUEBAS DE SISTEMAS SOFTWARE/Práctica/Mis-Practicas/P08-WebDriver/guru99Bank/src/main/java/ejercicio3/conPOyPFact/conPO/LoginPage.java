package ejercicio3.conPOyPFact.conPO;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    @FindBy(name="uid") WebElement userID;
    @FindBy(name="password") WebElement password;
    @FindBy(name="btnLogin") WebElement login;
    @FindBy(className="barone") WebElement loginTitle;
    String url = "http://demo.guru99.com/V5/index.php";

    /**
     * Funcion que comprueba si se mantiene en la página de Login gracias
     * al Alert emergente, si hay alert y contiene el mensaje de error
     * indicado en la especificación, entonces NO se ha abandonado login,
     * en otro caso
     * */
    private boolean inLogin(){
        boolean result = false;
        Alert alert;

        try {
            alert = driver.switchTo().alert();
            result = alert.getText().compareToIgnoreCase("User is not valid")==0;
            alert.accept();
        }catch (NoAlertPresentException e){
            //Nothing
        }finally {
            return result;
        }
    }

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.driver.get(url);
    }
    public ManagerPage login(String user, String pass){
        userID.sendKeys(user);
        password.sendKeys(pass);
        login.click();

        //Comprobamos que estamos ahora en ManagerPage y si no, devolvemos NULL!
        return inLogin() ? null : PageFactory.initElements(driver, ManagerPage.class);
    }
    public String getLoginTitle(){
        return loginTitle.getText();
    }
}