package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.modelo.Huesped;

public class HuespedDAO {
	private Connection connection;

	public HuespedDAO(Connection connection) {
		this.connection = connection;
	}
	

	public void guardar(Huesped huesped) {
		try {
			String sql = "INSERT INTO huespedes(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
			final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setString(3, huesped.getFechaNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva());
				
				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
					}
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = connection
                    .prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES");
    
            try (statement) {
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                        	     resultSet.getInt("ID"),
                        	     resultSet.getString("NOMBRE"),                                       
                                resultSet.getString("APELLIDO"),
                                resultSet.getString("FECHA_NACIMIENTO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getString("TELEFONO"),
                                resultSet.getInt("ID_RESERVA")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
	
    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");

            
            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int modificar(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, Integer id) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE HUESPEDES SET "
                    + " NOMBRE = ?, "
                    + " APELLIDO = ?,"
                    + " FECHA_NACIMIENTO = ?, "
                    + " NACIONALIDAD = ?, "
                    + " TELEFONO = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, fechaNacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Huesped> buscarHuesped(String lastname) {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = connection
                    .prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES WHERE APELLIDO LIKE '%' ? '%'");
    
            try (statement) {
            	statement.setString(1, lastname);
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                        	     resultSet.getInt("ID"),
                        	     resultSet.getString("NOMBRE"),                                       
                                resultSet.getString("APELLIDO"),
                                resultSet.getString("FECHA_NACIMIENTO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getString("TELEFONO"),
                                resultSet.getInt("ID_RESERVA")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
    
}

