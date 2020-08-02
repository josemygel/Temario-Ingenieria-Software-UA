package ppss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ClienteDAO implements IClienteDAO {
    //Los parametros de getConnection son tres: url de conexión con la BD, usuario, y password de la BD
    private final String cadena_conexionDB = "cadena de conexcion BD"; 
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Insertamos un cliente en la BD
    public void insert(Cliente cliente) throws SQLException {
        //Los parametros de getConnection son tres: url de conexión con la BD, usuario, y password de la BD
        Connection connection = DriverManager.getConnection(cadena_conexionDB, "login", "password");
        
        String sql = "insert into cliente (id, nombre, apellido, direccion, ciudad) values ("
                   + cliente.getId() + ", "
                   + "'" + cliente.getNombre() + "', "
                   + "'" + cliente.getApellido() + "', "
                   + "'" + cliente.getDireccion() + "', "
                   + "'" + cliente.getCiudad() + "'"
                   + ")";
        System.out.println("sql = "+sql);
        
        Statement stmt = connection.createStatement();
        stmt.execute(sql); 
        if (stmt.getUpdateCount() != 1) {
            throw new SQLException("Insert failed!");
        }
    }
    
    //Borramos un cliente de la BD
    public void delete (Cliente cliente) throws SQLException {
        Connection connection = DriverManager.getConnection(cadena_conexionDB, "login", "password");
        
        String sql = "delete from cliente where id= "
                   + cliente.getId();
        System.out.println("sql = "+sql);

        Statement stmt = connection.createStatement();
        stmt.execute(sql); 
        if (stmt.getUpdateCount() != 1) {
            throw new SQLException("Delete failed!");
        }
    }
    
    //Modificamos los datos de un cliente en la BD
    public void update(Cliente cliente) throws SQLException {
        Connection connection = DriverManager.getConnection(cadena_conexionDB, "login", "password");
        
        String sql = "UPDATE cliente SET "
                    + "nombre="+ "'" + cliente.getNombre() + "', "
                    + "apellido=" + "'" + cliente.getApellido() + "', "
                    + "direccion="    + "'" + cliente.getDireccion() + "', "
                    + "ciudad="    + "'" + cliente.getCiudad() + "'"
                    + " WHERE id=" + cliente.getId();
        System.out.println("sql = "+sql);

        Statement stmt = connection.createStatement();
        stmt.execute(sql); 
        if (stmt.getUpdateCount() != 1) {
            throw new SQLException("Update failed!");
        }
    }
    
    //ModRecuperamos los datos de un cliente en la BD mediante su id
    public Cliente retrieve(int clienteID) throws SQLException {
        Connection connection = DriverManager.getConnection(cadena_conexionDB, "login", "password");
        
        String sql = "SELECT id, nombre, apellido, direccion, ciudad from cliente "
                    + "WHERE id=" + "'" + clienteID+ "'";
        System.out.println("sql = "+sql);

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql); 
        Cliente cliente = null;
        if (rs.next()){
          //Retrieve by column name
          int id  = rs.getInt("id");
          String nombre = rs.getString("nombre");
          String apellido = rs.getString("apellido");
          String direccion = rs.getString("direccion");
          String ciudad = rs.getString("ciudad");
          cliente = new Cliente(id,nombre,apellido);
          cliente.setDireccion(direccion);
          cliente.setCiudad(ciudad);
          rs.close();
        } 
        return cliente;
    }   
}
