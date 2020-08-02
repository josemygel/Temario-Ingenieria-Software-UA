package ejercicio3.conPOyPFact.conPO;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;

public class NewCustomerPage {
    WebDriver driver;
    public int id;
    @FindBy(name = "name") WebElement inputName;
    @FindBy(name = "rad1") WebElement maleCheckBox;
    @FindBy(xpath = "//input[@value='f' and @name='rad1']") WebElement femaleCheckBox;
    @FindBy(name = "dob") WebElement inputDateOfBirthday;
    @FindBy(name = "addr") WebElement inputAddress;
    @FindBy(name = "city") WebElement inputCity;
    @FindBy(name = "state") WebElement inputState;
    @FindBy(name = "pinno") WebElement inputPIN;
    @FindBy(name = "telephoneno") WebElement inputTelephone;
    @FindBy(name = "emailid") WebElement inputEmail;
    @FindBy(name = "password") WebElement inputPassword;
    @FindBy(name = "sub") WebElement buttonSubmit;
    @FindBy(name = "res") WebElement buttonReset;
    //String myUrl = "";


    public NewCustomerPage(WebDriver driver){
        this.driver = driver;
    }

    private boolean inSamePage(){
        return driver.getCurrentUrl().contains("addcustomerpage");
    }

    /**
     * @return array de textos que contiene la pagina a la que se redirigirá
     */
    public ArrayList<String> CreateNewCustome(String name, boolean isMale, String date, String address, String city, String state,
                            String pin, String tlf, String email, String password) {
        if(isMale) maleCheckBox.click();
        else femaleCheckBox.click();

        inputName.sendKeys(name);
        inputDateOfBirthday.sendKeys(date);
        inputAddress.sendKeys(address);
        inputCity.sendKeys(city);
        inputState.sendKeys(state);
        inputPIN.sendKeys(pin);
        inputTelephone.sendKeys(tlf);
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);

        buttonSubmit.click();
        Alert alert;
        try {
            alert = driver.switchTo().alert();
            System.out.println("\tTEXTO DE LA ALERTA: "+alert.getText());
            if(alert.getText().toLowerCase().contains("error") || alert.getText().contains("Customer could not be added !!")) {
                alert.accept();
                id = -1;
                return new ArrayList<>();
            }else if(alert.getText().contains("Email Address Already Exist !!")){
                alert.accept();
                id = -999; //RESERVADO PARA IDENTIFICAR QUE EL EMAIL YA EXISTE
                return new ArrayList<>();
            }else if(inSamePage()){
                id = -1;
                return new ArrayList<>();
            }
        }catch (NoAlertPresentException e){
            //Si no se encuentra la alerta presente no alarmarse, simplemente fue correcto
        }

        //Busca y devuelve el ID
        List<WebElement> filasTabla = driver.findElements(By.xpath("//table//tbody//tr//td//table//tbody//tr//td"));
        ArrayList<String> textos = new ArrayList<>();

        for (int i = 0; i<filasTabla.size(); i++) {
            textos.add(filasTabla.get(i).getText());

            if (filasTabla.get(i).getText().compareToIgnoreCase("customer id") == 0)
                id = Integer.parseInt(filasTabla.get(i + 1).getText());
        }

        return textos;
    }

}