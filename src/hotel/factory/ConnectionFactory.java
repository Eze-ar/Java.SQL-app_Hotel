package hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private DataSource dataSource;

	public ConnectionFactory() {
		var comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel?useTimeZone=true&serverTimeZone=UTC");
		comboPooledDataSource.setUser("admin");
		comboPooledDataSource.setPassword("adm123");
		comboPooledDataSource.setMaxPoolSize(20);
		this.dataSource = comboPooledDataSource;

	}

	public Connection recuperaConexion() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
