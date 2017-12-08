package EJ4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alberto Rey Moreno
 */

public class Principal {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		consultaBD();
	}

	/**
	 * Consulta donde se pide que muestre el empleado con el sueldo MAXIMO de la tabla.
	 */
	public static void consultaBD() {
		
		String query = "SELECT apellido, salario FROM empleados WHERE salario = (SELECT MAX(salario) FROM empleados)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt", "root", "");
			
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			System.out.println("Ejercicio 4 - MAX SUELDO EMPLEADOS\n");
			System.out.println("+----------+---------+");
			System.out.println("| APELLIDO | SALARIO |");
			System.out.println("+----------+---------+");

			while (rs.next()) {
				System.out.println("| " + rs.getString(1) + "     | " + rs.getInt(2) + "    |");
			}
			System.out.println("+----------+---------+");
			
			rs.close();
			st.close();
			conexion.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
