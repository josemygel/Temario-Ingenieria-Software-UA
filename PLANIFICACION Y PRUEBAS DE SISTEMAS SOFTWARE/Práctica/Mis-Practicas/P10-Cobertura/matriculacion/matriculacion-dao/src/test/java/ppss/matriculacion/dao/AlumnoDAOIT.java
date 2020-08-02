package ppss.matriculacion.dao;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.*;
import ppss.matriculacion.to.AlumnoTO;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Integracion-fase1")
class AlumnoDAOIT {

    private IAlumnoDAO alumnoDAO; //SUT
    private IDatabaseTester databaseTester;
    private IDatabaseConnection connection;
    private Calendar cal;

    @BeforeEach
    public void setUp() throws Exception {

        String cadena_conexionDB = "jdbc:mysql://localhost:3306/matriculacion?useSSL=false";
        databaseTester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                cadena_conexionDB, "root","");
        //obtenemos la conexción con la BD
        connection = databaseTester.getConnection();

        //para evitar el warning al acceder a la BD
        connection.getConfig()
                .setProperty("http://www.dbunit.org/properties/datatypeFactory",
                        new MySqlDataTypeFactory());

        alumnoDAO = new FactoriaDAO().getAlumnoDAO();
        cal = Calendar.getInstance();
    }

    @Test
    @DisplayName("Ejercicio 2; Apartado F; testA1")
    void testA1() throws Exception {
        //CREAMOS EL OBJETO A INSERTAS/MODIFICAR
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("33333333C");
        alumno.setNombre("Elena Aguirre Juarez");
        cal.set(Calendar.YEAR, 1985);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumno.setFechaNacimiento(cal.getTime());

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();
        //invocamos a nuestro SUT
        Assertions.assertDoesNotThrow(()->alumnoDAO.addAlumno(alumno));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        //Comparamos los resultados
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @DisplayName("Ejercicio 2; Apartado F; testA2")
    void testA2() throws Exception {
        //CREAMOS EL OBJETO A INSERTAS/MODIFICAR
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("11111111A");
        alumno.setNombre("Alfonso Ramirez Ruiz");
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumno.setFechaNacimiento(cal.getTime());

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        assertThrows(DAOException.class, ()->alumnoDAO.addAlumno(alumno));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        //Comprobamos que NO cambio nada (por que hubo una excepcion)
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @DisplayName("Ejercicio 2; Apartado F; testA3")
    void testA3() throws Exception {
        //CREAMOS EL OBJETO A INSERTAS/MODIFICAR
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif("44444444D");
        alumno.setNombre(null);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumno.setFechaNacimiento(cal.getTime());

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        assertThrows(DAOException.class, ()->alumnoDAO.addAlumno(alumno));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        //Comprobamos que NO cambio nada (por que hubo una excepcion)
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @DisplayName("Ejercicio 2; Apartado F; testA4")
    void testA4() throws Exception {
        //CREAMOS EL OBJETO A INSERTAS/MODIFICAR
        AlumnoTO alumno = null;

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        assertThrows(DAOException.class, ()->alumnoDAO.addAlumno(alumno));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        //Comprobamos que NO cambio nada (por que hubo una excepcion)
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @DisplayName("Ejercicio 2; Apartado F; testA5")
    void testA5() throws Exception {
        //CREAMOS EL OBJETO A INSERTAS/MODIFICAR
        AlumnoTO alumno = new AlumnoTO();
        alumno.setNif(null);
        alumno.setNombre("Pedro Garcia Lopez");
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1); //Nota: en la clase Calendar, el primer mes es 0
        cal.set(Calendar.DATE, 22);
        alumno.setFechaNacimiento(cal.getTime());

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        assertThrows(DAOException.class, ()->alumnoDAO.addAlumno(alumno));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        //Comprobamos que NO cambio nada (por que hubo una excepcion)
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @DisplayName("Ejercicio 2; Apartado F; testB1")
    void testB1() throws Exception {
        String nifEntrada = "11111111A";

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();
        //invocamos a nuestro SUT
        Assertions.assertDoesNotThrow(()->alumnoDAO.delAlumno(nifEntrada));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        //Comparamos los resultados
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    @DisplayName("Ejercicio 2; Apartado F; testB2")
    void testB2() throws Exception {
        String nifEntrada = "33333333C";

        //Inicializamos el dataSet con los datos iniciales de la tabla cliente
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        //Inyectamos el dataset en el objeto databaseTester
        databaseTester.setDataSet(dataSet);
        //inicializamos la base de datos con los contenidos del dataset
        databaseTester.onSetup();

        //invocamos a nuestro SUT
        assertThrows(DAOException.class, ()->alumnoDAO.delAlumno(nifEntrada));

        //recuperamos los datos de la BD después de invocar al SUT
        IDataSet databaseDataSet = connection.createDataSet();
        //Recuperamos los datos de la tabla cliente
        ITable actualTable = databaseDataSet.getTable("alumnos");

        //creamos el dataset con el resultado esperado
        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        ITable expectedTable = expectedDataSet.getTable("alumnos");

        //Comprobamos que NO cambio nada (por que hubo una excepcion)
        Assertion.assertEquals(expectedTable, actualTable);
    }
}