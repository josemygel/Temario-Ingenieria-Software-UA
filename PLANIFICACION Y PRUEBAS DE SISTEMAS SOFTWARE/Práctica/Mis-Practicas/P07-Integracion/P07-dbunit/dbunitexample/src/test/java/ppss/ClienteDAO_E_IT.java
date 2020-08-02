package ppss;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.apache.log4j.BasicConfigurator;

/* IMPORTANTE:
    Dado que prácticamente todos los métodos de dBUnit lanzan una excepción,
    vamos a usar "throws Esception" en los métodos, para que el código quede más
    legible sin necesidad de usar un try..catch o envolver cada sentencia dbUnit 
    con un assertDoesNotThrow()
*/
public class ClienteDAO_E_IT {

  private ClienteDAO clienteDAO; //SUT
  private IDatabaseTester databaseTester;
  private IDatabaseConnection connection;

  @BeforeEach
  public void setUp() throws Exception {

    String cadena_conexionDB = "jdbc:mysql://localhost:3306/DBUNIT?useSSL=false";
    databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
            cadena_conexionDB, "root","");
    //obtenemos la conexción con la BD
    connection = databaseTester.getConnection();

    //para evitar el warning al acceder a la BD
    connection.getConfig()
              .setProperty("http://www.dbunit.org/properties/datatypeFactory",
                    new MySqlDataTypeFactory());

    clienteDAO = new ClienteDAO();
  }

  @Test
  public void testInsert() throws Exception {
    Cliente cliente = new Cliente(1,"Will", "Smith");
    cliente.setDireccion("Calle Pintor Editor");
    cliente.setCiudad("Anycity");

    //Inicializamos el dataSet con los datos iniciales de la tabla cliente
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-init.xml");
    //Inyectamos el dataset en el objeto databaseTester
    databaseTester.setDataSet(dataSet);
    //inicializamos la base de datos con los contenidos del dataset
    databaseTester.onSetup();
     //invocamos a nuestro SUT
    Assertions.assertDoesNotThrow(()->clienteDAO.insert(cliente));

    //recuperamos los datos de la BD después de invocar al SUT
    IDataSet databaseDataSet = connection.createDataSet();
    //Recuperamos los datos de la tabla cliente
    ITable actualTable = databaseDataSet.getTable("cliente"); 

    //creamos el dataset con el resultado esperado
    IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/cliente-esperado-E.xml");
    ITable expectedTable = expectedDataSet.getTable("cliente");

    Assertion.assertEquals(expectedTable, actualTable);

   }

  @Test
  public void testDelete() throws Exception {
    Cliente cliente =  new Cliente(1,"Will", "Smith");
    cliente.setDireccion("Calle Pintor Editor");
    cliente.setCiudad("Anycity");

    //inicializamos la BD
    IDataSet dataSet = new FlatXmlDataFileLoader().load("/cliente-esperado-E.xml");
    databaseTester.setDataSet(dataSet);
    databaseTester.onSetup();

    //invocamos a nuestro SUT
    Assertions.assertDoesNotThrow(()->clienteDAO.delete(cliente));

    IDataSet databaseDataSet = connection.createDataSet();
    int rowCount = databaseDataSet.getTable("cliente").getRowCount();
    
    Assertions.assertEquals(0, rowCount);
  }

}
