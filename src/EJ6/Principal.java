package EJ6;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.jdbc.EscapeTokenizer;

/**
 * @author Alberto Rey Moreno
 */

public class Principal {

	/**
	 * Pide al usuario el GESTOR DE BASE DE DATOS
	 * 
	 * @return valor del gestor de BBDD
	 */
	public static String pedirBBDD() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Elija la BBDD [oracle] [mysql] [sqlite]");
		String base = scan.nextLine();
		return base;
	}

	/**
	 * Conecta con el gestor de base de datos y ejecuta los métodos creados.
	 */
	public static void getConexionBase() {
		try {

			String baseDatos = pedirBBDD();

			Connection conexion = ConexionBBDD.conexionBBDD(baseDatos);
			DatabaseMetaData dbmd = conexion.getMetaData();

			ResultSet rs = null;
			String nombre = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();

			System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS " + nombre + ":");
			System.out.println("============================================");
			System.out.println("Nombre: " + nombre);
			System.out.println("Driver: " + driver);
			System.out.println("URL: " + url);
			System.out.println("Usuario: " + usuario);
			System.out.println("");

			if (baseDatos.equalsIgnoreCase("oracle")) {
				rs = dbmd.getTables(null, "ACADT", null, null);
			} else {
				rs = dbmd.getTables(null, "acadt", null, null);
			}

			while (rs.next()) {
				String catalogo = rs.getString(1);
				String esquema = rs.getString(2);
				String tabla = rs.getString(3);
				String tipo = rs.getString(4);

				AplicacionesTabla.getColumnaTabla(nombre, esquema, tabla, conexion);
				AplicacionesTabla.getClaves(tipo, nombre, esquema, tabla, conexion);
			}
			System.out.println("\n");
			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		getConexionBase();
	}

}
