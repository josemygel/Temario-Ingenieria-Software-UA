package ppss.matriculacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ppss.matriculacion.to.AlumnoTO;


/**
 * Implementacion del acceso a los datos de los alumnos almacenados en una base de
 * datos.
 *
 */
public class JDBCAlumnoDAO implements IAlumnoDAO {

	public void addAlumno(AlumnoTO a) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FuenteDatosJDBC.getInstance().getConnection();
			ps = con.prepareStatement("INSERT INTO alumnos(nif, nombre, direccion, email, fechaNac) VALUES (?,?,?,?,?)");
			ps.setString(1, a.getNif());
			ps.setString(2, a.getNombre());
			ps.setString(3, a.getDireccion());
			ps.setString(4, a.getEmail());
			ps.setDate(5, new java.sql.Date(a.getFechaNacimiento().getTime()));
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Error al conectar con BD", e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) { }
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) { }
			}
		}
	}

	public void delAlumno(String nif) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = FuenteDatosJDBC.getInstance().getConnection();
			ps = con.prepareStatement("DELETE FROM alumnos WHERE nif=?");
			ps.setString(1, nif);			
			int rowCount = ps.executeUpdate();
			
			if(rowCount==0) {
				throw new DAOException("No se ha borrado ningun alumno");
			}
			
		} catch (SQLException e) {
			throw new DAOException("Error al conectar con BD", e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) { }
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) { }
			}
		}
	}

	public List<AlumnoTO> getAlumnos() throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = FuenteDatosJDBC.getInstance().getConnection();
			ps = con.prepareStatement("SELECT * FROM alumnos");			
			rs = ps.executeQuery();
			List<AlumnoTO> lista = new ArrayList<AlumnoTO>();
			while(rs.next()) {
				lista.add(this.creaAlumno(rs));
			}
			
			return lista;
		} catch (SQLException e) {
			throw new DAOException("Error al conectar con BD", e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) { }
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) { }
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) { }
			}
		}		
	}

	public AlumnoTO getAlumno(String nif) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = FuenteDatosJDBC.getInstance().getConnection();
			ps = con.prepareStatement("SELECT * FROM alumnos WHERE nif=?");			
			ps.setString(1, nif);			

			rs = ps.executeQuery();
			if(rs.next()) {
				return this.creaAlumno(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DAOException("Error al conectar con BD", e);
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) { }
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) { }
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) { }
			}
		}		
	}

	private AlumnoTO creaAlumno(ResultSet rs) throws SQLException {
		AlumnoTO alumno = new AlumnoTO();
		alumno.setNif(rs.getString("nif"));
		alumno.setNombre(rs.getString("nombre"));
		alumno.setDireccion(rs.getString("direccion"));
		alumno.setEmail(rs.getString("email"));
		alumno.setFechaNacimiento(rs.getDate("fechaNac"));
		return alumno;
	}
}
