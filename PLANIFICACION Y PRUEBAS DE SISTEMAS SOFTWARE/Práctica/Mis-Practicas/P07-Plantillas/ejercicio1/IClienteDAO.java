package ppss;

import java.sql.SQLException;

public interface IClienteDAO {

    public void insert(Cliente cliente) throws SQLException;
    public void delete (Cliente cliente) throws SQLException;
    public void update(Cliente cliente) throws SQLException;
    public Cliente retrieve(int customerID) throws SQLException;
}
