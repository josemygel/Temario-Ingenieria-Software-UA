package ppss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CustomerFactory {
    private final String cadena_conexionDB = "";
  
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static CustomerFactory getInstance()
    {
        return new CustomerFactory();
    }

    public Customer create(int id, String firstName, String lastName) {
        return new Customer(id, firstName, lastName);
    }
    
    //Insertamos un cliente en la BD
    public void insert(Customer customer) throws SQLException {
        //Los parametros de getConnection son tres: url de conexión con la BD, usuario, y password de la BD
        Connection connection = DriverManager.getConnection("cadena de conexion", "login", "password");
        String sql = "insert into customer (id, firstname, lastname, street, city) values ("
                   + customer.getId() + ", "
                   + "'" + customer.getFirstName() + "', "
                   + "'" + customer.getLastName() + "', "
                   + "'" + customer.getStreet() + "', "
                   + "'" + customer.getCity() + "'"
                   + ")";

        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        if (stmt.getUpdateCount() != 1) {
            throw new SQLException("Insert failed!");
        }
    }
    
    //Borramos un cliente de la BD
    public void delete (Customer customer) throws SQLException {
        Connection connection = DriverManager.getConnection("cadena de conexion", "login", "password");
        String sql = "delete from customer where id= " 
                   + customer.getId();

        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        if (stmt.getUpdateCount() != 1) {
            throw new SQLException("Delete failed!");
        }
    }
    
    //Modificamos los datos de un cliente en la BD
    public void update(Customer customer) throws SQLException {
        Connection connection = DriverManager.getConnection("cadena de conexion", "login", "password");
        String sql = "UPDATE customer SET "
        + "firstname="+ "'" + customer.getFirstName() + "', "
        + "lastname=" + "'" + customer.getLastName() + "', "
        + "street="    + "'" + customer.getStreet() + "', "
        + "city="    + "'" + customer.getCity() + "'"
        + " WHERE id=" + customer.getId();
        System.out.println("sql = "+sql);
        
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        if (stmt.getUpdateCount() != 1) {
            throw new SQLException("Update failed!");
        }
    }
    
    //Recuperamos los datos de un cliente en la BD mediante su id
    public Customer retrieve(int customerID) throws SQLException {
        Connection connection = DriverManager.getConnection("cadena de conexion", "login", "password");
        String sql = "SELECT id, firstname, lastname, street, city from customer "
        + "WHERE id=" + "'" + customerID+ "'";
        
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        Customer customer = null;
        if (rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String street = rs.getString("street");
            String city = rs.getString("city");
            customer = new Customer(id,firstname,lastname);
            customer.setStreet(street);
            customer.setCity(city);
            rs.close();
        }
        return customer;
    }

}
