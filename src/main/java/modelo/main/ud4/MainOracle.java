package modelo.main.ud4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.util.ConnectionManager;
import connection.util.MyDataSource;
import modelo.OracleDepartamento;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class MainOracle {

	public static void main(String[] args) {
		OracleDataSource ods = null;
		Connection conn = null;
		try {
			ods = new OracleDataSource();
			MyDataSource mds = ConnectionManager.getDataSource("src/main/resources/db.properties");

			// String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			ods.setURL(mds.getUrl());
			ods.setUser(mds.getUser());
			ods.setPassword(mds.getPwd());
			conn = ods.getConnection();

			// Create Oracle DatabaseMetaData object
			DatabaseMetaData meta = conn.getMetaData();

			// gets driver info:
			System.out.println("JDBC driver version is " + meta.getDriverVersion());

			PreparedStatement stmt = conn.prepareStatement("SELECT VALUE(d) FROM DEPT_TABLE d");
			OracleResultSet rs = (OracleResultSet) stmt.executeQuery();
			while (rs.next()) {

				Object s = rs.getObject(1, OracleDepartamento.getOracleDataFactory());

				if (s != null) {
					if (s instanceof OracleDepartamento) {

						System.out.println(s);
					}

					else {
						System.out.println("Unknown type");
						System.out.println(s);
					}
				}
			}

		} catch (SQLException e) {
			System.err.println("Ha ocurrido una exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.err.println("Ha ocurrido una exception cerrando la conexión: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

}
