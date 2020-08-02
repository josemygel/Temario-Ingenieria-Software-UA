package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    WebDriver driver;
    WebElement userID;
    WebElement password;
    WebElement login;
    WebElement pTitle;

    //Sin Page Object...
    //Sin Page Object...
    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.driver.get("http://demo.guru99.com/V5/index.php");
        userID = driver.findElement(By.name("uid"));
        password = driver.findElement(By.name("password"));
        login = driver.findElement(By.name("btnLogin"));
        pTitle = driver.findElement(By.className("barone"));
    }

    public void login(String user, String pass){
        userID.sendKeys(user);
        password.sendKeys(pass);
        login.click();
    }

    public String getPageTitle(){
        return pTitle.getText();
    }
}