package EJ5;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {
	
	/**
	 * Conexion de Oracle
	 */
	public static void oracleBBDD() {

		try {

			Class.forName("oracle.jdbc.OracleDriver");

			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:acadt/12345@//localhost/XE");
			DatabaseMetaData dbmd = conexion.getMetaData();

			ResultSet rs = null;
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();

			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS - ORACLE:");
			System.out.println("============================================");
			System.out.println("Nombre: " + nombre);
			System.out.println("Driver: " + driver);
			System.out.println("URL: " + url);
			System.out.println("Usuario: " + usuario);
			System.out.println("");
			
			rs = dbmd.getTables(null, "ACADT", null, null);
			
			while (rs.next()) {
				String catalogo = rs.getString(1);
				String esquema = rs.getString(2);
				String tabla = rs.getString(3);
				String tipo = rs.getString(4);
				System.out.println(tipo + " - Catalogo: " + catalogo + ", Esquema: " + esquema + ", Nombre: " + tabla);
			}
			System.out.println("\n");
			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Conexion de sqlite
	 */
	public static void sqliteBBDD() {

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conexion = DriverManager.getConnection(
					"jdbc:sqlite:ACADT.db", "root", "");
			DatabaseMetaData dbmd = conexion.getMetaData();

			ResultSet rs = null;
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();

			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS - SQLITE:");
			System.out.println("============================================");
			System.out.println("Nombre: " + nombre);
			System.out.println("Driver: " + driver);
			System.out.println("URL: " + url);
			System.out.println("Usuario: " + usuario);
			System.out.println("");

			rs = dbmd.getTables(null, "acadt", null, null);
			
			while (rs.next()) {
				String catalogo = rs.getString(1);
				String esquema = rs.getString(2);
				String tabla = rs.getString(3);
				String tipo = rs.getString(4);
				System.out.println(tipo + " - Catalogo: " + catalogo + ", Esquema: " + esquema + ", Nombre: " + tabla);
			}
			System.out.println("\n");
			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
