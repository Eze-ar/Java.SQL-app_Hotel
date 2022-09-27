package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hotel.modelo.Reserva;


public class ReservaDAO {
	private Connection connection;

	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public int obtenerIdReserva() {
		
			int resultado = 0;
			try {
				
				String sql = "SELECT ID FROM RESERVAS";
				final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);;
				try (statement) {
					statement.execute();
					final ResultSet resulset = statement.getResultSet();
					try (resulset) {
						while (resulset.next()) {
							resultado = resulset.getInt("ID");
						}

					}
					
					return resultado;

				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			
		}
	}

	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas(fecha_entrada, fecha_salida, valor, forma_pago) VALUES (?, ?, ?, ?)";
			final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, reserva.getFechaEntrada());
				statement.setString(2, reserva.getFechaSalida());
				statement.setString(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.executeUpdate();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> listar() {
        List<Reserva> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = connection
                    .prepareStatement("SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM RESERVAS");
    
            try (statement) {
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Reserva(
                        	     resultSet.getString("FECHA_ENTRADA"),
                        	     resultSet.getString("FECHA_SALIDA"),                                       
                                resultSet.getString("VALOR"),
                                resultSet.getString("FORMA_PAGO"),
                                resultSet.getInt("ID")));
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
    
    public int modificar(String fecha_entrada, String fecha_salida, Integer valor, String forma_pago, Integer id) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE RESERVAS SET "
                    + " FECHA_ENTRADA = ?, "
                    + " FECHA_SALIDA = ?,"
                    + " VALOR = ?, "                   
                    + " FORMA_PAGO = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, fecha_entrada);
                statement.setString(2, fecha_salida);
                statement.setInt(3, valor);
                statement.setString(4, forma_pago);
                statement.setInt(5, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Reserva> buscarReserva(String numero) {
        List<Reserva> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = connection
                    .prepareStatement("SELECT ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO FROM RESERVAS WHERE ID LIKE '%' ? '%'");
    
            try (statement) {
            	statement.setString(1, numero);
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                	 while (resultSet.next()) {
                         resultado.add(new Reserva(
                         	     resultSet.getString("FECHA_ENTRADA"),
                         	     resultSet.getString("FECHA_SALIDA"),                                       
                                 resultSet.getString("VALOR"),
                                 resultSet.getString("FORMA_PAGO"),
                                 resultSet.getInt("ID")));
                     }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
}
