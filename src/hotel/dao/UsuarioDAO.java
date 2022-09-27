package hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {
	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}
	
	public String obtenerUserName() {
		String userName="";
		
		try {
			
			String sql = "SELECT USER_NAME FROM USUARIOS";
			final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.execute();
				final ResultSet resulset = statement.getResultSet();
				try (resulset) {
					while (resulset.next()) {
						userName = resulset.getString("USER_NAME");
					}

				}
				
				return userName;

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		
	}
	}
	
	public String obtenerPassword() {
		String password="";
		
		try {
			
			String sql = "SELECT PASSWORD FROM USUARIOS";
			final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);;
			try (statement) {
				statement.execute();
				final ResultSet resulset = statement.getResultSet();
				try (resulset) {
					while (resulset.next()) {
						password = resulset.getString("PASSWORD");
					}

				}
				
				return password;

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		
	}
	}
}
