package ejercicio3.conPOyPFact.conPO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteCustomerPage {
    WebDriver driver;
    Alert alert;
    @FindBy(name = "cusid") WebElement idInput;
    @FindBy(name = "AccSubmit") WebElement submitButton;
    @FindBy(name = "res") WebElement resetButton;
    @FindBy(linkText="Log out") WebElement logOut;

    public DeleteCustomerPage(WebDriver driver){
        this.driver = driver;
    }

    public void logOut(){
        logOut.click();
        alert = driver.switchTo().alert();
        if(alert.getText().contains("You Have Succesfully Logged Out!!"))
            alert.accept();
    }

    public boolean deleteCustomer(int id){
        Alert alert;
        boolean result = false;

        idInput.sendKeys(String.valueOf(id));

        //Do you really want to delete this Customer?
        try {
            submitButton.click();
            alert = driver.switchTo().alert();
            result = alert.getText().compareToIgnoreCase("Do you really want to delete this Customer?")==0;
            alert.accept();
            alert = driver.switchTo().alert();
            result = result && alert.getText().compareToIgnoreCase("Customer deleted Successfully")==0;
            alert.accept();
        }catch (NoAlertPresentException e){
            result = false;
        }finally {
            return result;
        }
    }
}