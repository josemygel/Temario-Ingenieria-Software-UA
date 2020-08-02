package ejercicio3.conPOyPFact;

import ejercicio3.conPOyPFact.conPO.DeleteCustomerPage;
import ejercicio3.conPOyPFact.conPO.LoginPage;
import ejercicio3.conPOyPFact.conPO.ManagerPage;
import ejercicio3.conPOyPFact.conPO.NewCustomerPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @TestMethorOrder sirve para dar orden a los test, ya que no es
 * lógico que pruebe a hacer un test de crear DOS VECES el mismo
 * usuario sin haber probado a crear y borrar uno solo...
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestNewClient {
    WebDriver driver;
    WebDriverWait wait;
    LoginPage poLogin;
    ManagerPage poManagerPage;
    NewCustomerPage newCostumerPage;
    DeleteCustomerPage deleteCustomerPage;
    String usuario, password;
    int id;

    //Mis datos
    final String name = "josemygel";
    boolean isMale = true;
    String day = "08";
    String month = "03";
    String year = "1995";
    String date1 = day+'-'+month+'-'+year;
    String date2 = year+'-'+month+'-'+day;
    String address = "Calle x";
    String city = "Alicante";
    String state = "Alicante";
    String pin = "123456";
    String tlf = "999999999";
    String email = "josemygel@alu.ua.es";
    String pass = "123456";

    @BeforeEach
    public void setup(){
        //ANTES DE CADA TEST INICIAMOS SESION COMO ADMINISTRADOR
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        poLogin = PageFactory.initElements(driver, LoginPage.class);

        //Credenciales de administracion para el proceso COMPLETO
        usuario = "mngr270679";
        password = "sYbUbuh";
        id = -1;
        assertTrue(poLogin.getLoginTitle().toLowerCase().contains("guru99 bank"));

        //Realizamos previo proceso de login
        System.out.println("INICIANDO SESIÓN");
        poManagerPage = poLogin.login(usuario,password);

        Assertions.assertNotNull(poManagerPage,"No se cambió a la pagina de administración (posible login incorrecto)");
        assertTrue(poManagerPage.getHomePageDashboardUserName()
                .toLowerCase().contains("welcome to manager's page of guru99 ban"));
    }

    //DESPUES DE CADA TEST CERRAMOS NAVEGADOR
    @AfterEach
    public void resetAndClose(){
        //Eliminaremos el usuario creado recientemente
        driver.findElement(By.linkText("Delete Customer"));

        //Comprobamos que tenemos el ID a borrar del test anterior!
        assertFalse(id<0);

        //Comprobamos que id es CORRECTO (y si lo es se elimina)
        deleteCustomerPage = poManagerPage.goToDeleteCustomerPage();
        System.out.println("\tELIMINANDO USUARIO CON ID "+id);
        assertTrue(deleteCustomerPage.deleteCustomer(id));
        System.out.println("CERRANDO SESIÓN");

        //Realizamos proceso de Logout
        deleteCustomerPage.logOut();

        //Cerramos el navegador
        driver.close();
    }

    @Test
    @Order(1)
    @DisplayName("Creando y eliminando al usuario "+name)
    public void testTestNewClientOk(){
        newCostumerPage = poManagerPage.goToNewCustomerPage();
        ArrayList<String> textos = new ArrayList<>();
        //Realizamos el registro
        textos = newCostumerPage.CreateNewCustome(name,isMale,date1,address,city,state,pin,tlf,email,pass);
        id = newCostumerPage.id;

        //Comprobamos que id es CORRECTO
        assertTrue(id >= 0);
        //Si textos NO esta vacío es porque se regitro correctamente
        assertFalse(textos.isEmpty());
        System.out.println("\tUSUARIO CON ID "+id);

        //Busca y devuelve el ID
        List<WebElement> filasTabla = driver.findElements(By.xpath("//table//tbody//tr//td//table//tbody//tr//td"));

        for (WebElement elem: filasTabla)
            if(!elem.getText().isEmpty())
                textos.add(elem.getText());

        //Si es correcto (se puede asumir que estamos en la pagina de información del usuario ya que no salimos todavía)
        final ArrayList<String> finalTextos = textos;
        assertAll("Testeando que la información introducida es correcta y se muestra",()->{
            assertTrue(finalTextos.contains(name));
            assertTrue(finalTextos.contains(name));
            assertTrue(finalTextos.contains(date2));
            assertTrue(finalTextos.contains(address));
            assertTrue(finalTextos.contains(city));
            assertTrue(finalTextos.contains(state));
            assertTrue(finalTextos.contains(pin));
            assertTrue(finalTextos.contains(tlf));
            assertTrue(finalTextos.contains(email));
        });

        //Click en el enlace de continuar
        assertTrue(driver.findElement(By.className("heading3")).getText()
                        .compareToIgnoreCase("Customer Registered Successfully!!!")==0,
                "Customer "+id+" Registered Successfully");
    }

    @Test
    @Order(2)
    @DisplayName("Intentando crear por segunda ver al usuario "+name)
    void testTestNewClientDuplicate(){
        ArrayList<String> textos = new ArrayList<>();

        //================= CREANDO EL PRIMER USUARIO =================
        {
            newCostumerPage = poManagerPage.goToNewCustomerPage();

            //Realizamos el registro
            textos = newCostumerPage.CreateNewCustome(name, isMale, date1, address, city, state, pin, tlf, email, pass);
            id = newCostumerPage.id;

            //Comprobamos que id es CORRECTO
            assertTrue(id >= 0);
            //Si textos NO esta vacío es porque se regitro correctamente
            assertFalse(textos.isEmpty());

            System.out.println("\tUSUARIO CON ID " + id);

            //Si es correcto (se puede asumir que estamos en la pagina de información del usuario ya que no salimos todavía)
            final ArrayList<String> finalTextos = textos;
            assertAll("Testeando que la información introducida es correcta y se muestra", () -> {
                assertTrue(finalTextos.contains(name));
                assertTrue(finalTextos.contains(name));
                assertTrue(finalTextos.contains(date2));
                assertTrue(finalTextos.contains(address));
                assertTrue(finalTextos.contains(city));
                assertTrue(finalTextos.contains(state));
                assertTrue(finalTextos.contains(pin));
                assertTrue(finalTextos.contains(tlf));
                assertTrue(finalTextos.contains(email));
            });

            //Click en el enlace de continuar
            assertTrue(driver.findElement(By.className("heading3")).getText()
                            .compareToIgnoreCase("Customer Registered Successfully!!!") == 0,
                    "Customer " + id + " Registered Successfully");
        }

        // ================= AHORA CREAMOS EL SEGUNDO USUARIO =================
        {

            newCostumerPage = poManagerPage.goToNewCustomerPage();

            //Realizamos el registro
            System.out.println("\tUSUARIO CON ID (2º VEZ)");
            //Diferentes datos salvo EMAIL! (no pasamos ID ya que este se sobreescribiría
            // y lo necesitamos para limpiar el usuario creado)
            textos = newCostumerPage.CreateNewCustome(name+"a", !isMale, "08-03-1994", address+"a", city+"a", state+"a", "654321", "888888888", email, "654321");

            //Si textos esta vacío es porque HUBO UN ERROR (que es lo que buscamos)
            assertTrue(textos.isEmpty());

            //id==-1 porque el usuario YA existe
            assertTrue(newCostumerPage.id == -999,"Comprobación de que el email YA existe");
        }
    }


}