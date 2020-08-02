package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerPage {
    WebDriver driver;
    WebElement homePageUserName;
    WebElement newCustomer;
    WebElement logOut;

    public ManagerPage(WebDriver driver){
        this.driver = driver;
        homePageUserName = driver.findElement(By.tagName("marquee"));
        newCustomer = driver.findElement(By.linkText("New Customer"));
        logOut = driver.findElement(By.linkText("Log out"));
    }

    public String getHomePageDashboardUserName(){
        return homePageUserName.getText();
    }
}
