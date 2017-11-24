package EJ5;

import java.sql.*;
import java.util.Scanner;

public class Principal {

	public static int pedirInt() {
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();

	}

	public static void menu() {

		int opc;

		do {

			System.out.println("-- Ejerc. 5 --");
			System.out.println("- 1 - Oracle -");
			System.out.println("- 2 - SQLite -");
			System.out.println("- 3 - Salir  -");
			System.out.print("Elija: ");
			opc = pedirInt();

			switch (opc) {
			case 1:
				oracleBBDD();
				break;
			case 2:
				sqliteBBDD();
				break;
			case 3:
				System.out.println("\n\n\n\n- Hasta la próxima -");
				break;
			default:
				System.out.println("\n- Elija correctamente -\n");
				break;
			}

		} while (opc != 3);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
	}

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

	public static void sqliteBBDD() {

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conexion = DriverManager.getConnection(
					"jdbc:sqlite:G:\\0 - DAM\\2 - 2DAM\\4 - ACCESO A DATOS\\TEMA3\\ACADT.db", "root", "");
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
