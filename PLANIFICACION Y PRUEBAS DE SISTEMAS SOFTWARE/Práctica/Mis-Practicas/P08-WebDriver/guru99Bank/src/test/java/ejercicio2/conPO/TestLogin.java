package ejercicio2.conPO;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;




public class TestLogin {
    WebDriver driver;
    WebDriverWait wait;
    LoginPage poLogin;
    ManagerPage poManagerPage;
    String usuario, password;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        poLogin = new LoginPage(driver);
    }

    @AfterEach
    public void cerrar() { driver.close(); }

    @Test
    public void test_Login_Correct() {
        usuario = "mngr270679";
        password = "sYbUbuh";

        String loginPageTitle = poLogin.getPageTitle();
        Assertions.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
        poLogin.login(usuario,password);
        poManagerPage = new ManagerPage(driver);

        Assertions.assertTrue(poManagerPage.getHomePageDashboardUserName()
                .toLowerCase().contains("welcome to manager's page of guru99 bank"));
    }

    @Test
    void test_login_Incorrect(){
        usuario = "FAKEPPSS";
        password = "FAKEPPSS";

        String loginPageTitle = poLogin.getPageTitle();
        Assertions.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
        poLogin.login(usuario,password);

        //Operaciones sobre ventanas de alerta
        //cambiamos el foco a la ventana de alerta
        Alert alert = driver.switchTo().alert();

        //Comprobamos el mensaje de la ventana
        Assertions.assertEquals("User is not valid", alert.getText());

        //pulsamos sobre el botón OK/ACCEPT
        alert.accept();

        //En la página hay dos elementos con el mismo enlace
        List<WebElement> barones=driver.findElements(By.className("barone"));
        //Comprobamos que seguimos en la pagina login
        Assertions.assertEquals("Guru99 Bank", barones.get(0).getText());
        Assertions.assertEquals("Steps To Generate Access", barones.get(1).getText());
    }
}
