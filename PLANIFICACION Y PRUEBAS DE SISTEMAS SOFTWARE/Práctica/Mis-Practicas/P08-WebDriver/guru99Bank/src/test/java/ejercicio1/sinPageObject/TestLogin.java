package ejercicio1.sinPageObject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TestLogin {
    WebDriver driver;
    WebDriverWait wait;
    WebElement loginInput, passwordInput, loginButton, resetButton;
    String usuario, password;

    @BeforeEach
    void init() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        //1º Obtenemos la URL y los elementos a testear
        driver.get("http://demo.guru99.com/V5/index.php");
        loginInput = driver.findElement(By.name("uid"));
        passwordInput = driver.findElement(By.name("password"));
        loginButton = driver.findElement(By.name("btnLogin"));
        //Unused
        //resetButton = driver.findElement(By.name("btnReset"));
    }

    @AfterEach
    void cerrar() {
        driver.close();
    }

    @Test
    void test_login_Correct(){
        usuario = "mngr270679";
        password = "sYbUbuh";

        loginInput.sendKeys(usuario);
        passwordInput.sendKeys(password);
        loginButton.click();

        WebElement presentacion = driver.findElement(By.className("heading3"));
        Assertions.assertEquals("Welcome To Manager's Page of Guru99 Bank", presentacion.getText());
    }

    @Test
    void test_login_Incorrect(){
        usuario = "FAKEPPSS";
        password = "FAKEPPSS";

        loginInput.sendKeys(usuario);
        passwordInput.sendKeys(password);
        loginButton.click();

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
