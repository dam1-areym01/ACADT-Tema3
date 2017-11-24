package EJ6;

import java.sql.*;
import java.util.Scanner;

import com.mysql.jdbc.EscapeTokenizer;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		conexionBBDD();
	}

	public static void conexionBBDD() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt", "root", "");
			DatabaseMetaData dbmd = conexion.getMetaData();

			ResultSet rs = null;
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();

			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS - MYSQL:");
			System.out.println("===========================================\n");
			System.out.println("Nombre: " + nombre);
			System.out.println("Driver: " + driver);
			System.out.println("URL: " + url);
			System.out.println("Usuario: " + usuario);

			while (rs.next()) {
				String catalogo = rs.getString(1);
				String esquema = rs.getString(2);
				String tabla = rs.getString(3);
				String tipo = rs.getString(4);

				getColumnaTabla("acadt", esquema, tabla);
				getClaves(Integer.parseInt(tipo), "acadt", esquema, tabla);
			}

			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void getColumnaTabla(String bd, String esquema, String tabla) {

		
		
	}

	public static void getClaves(int tipo, String bd, String esquema, String tabla) {

		
		
	}

}
