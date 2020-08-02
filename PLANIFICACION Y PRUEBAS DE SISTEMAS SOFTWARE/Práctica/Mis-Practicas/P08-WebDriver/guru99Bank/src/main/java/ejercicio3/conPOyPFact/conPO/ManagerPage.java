package ejercicio3.conPOyPFact.conPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage {
    WebDriver driver;
    @FindBy(tagName = "marquee") WebElement homePageUserName;
    @FindBy(linkText="New Customer") WebElement newCustomer;
    @FindBy(linkText="Delete Customer") WebElement deleteCustomer;
    @FindBy(linkText="Log out") WebElement logOut;

    public ManagerPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHomePageDashboardUserName(){
        return homePageUserName.getText();
    }

    public NewCustomerPage goToNewCustomerPage(){
        newCustomer.click();
        return PageFactory.initElements(driver, NewCustomerPage.class);
    }

    public DeleteCustomerPage goToDeleteCustomerPage() {
        deleteCustomer.click();
        return PageFactory.initElements(driver, DeleteCustomerPage.class);
    }
}