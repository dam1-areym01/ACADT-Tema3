package EJ3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		consultaBD();
	}

	public static void consultaBD() {
		
		String query = "SELECT apellido, oficio, salario FROM empleados WHERE emp_no = 10";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/acadt", "root", "");
			
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			System.out.println("Ejercicio 3 - EMP NO = 10 EMPLEADOS\n");
			System.out.println("+----------+------------+---------+");
			System.out.println("| APELLIDO | OFICIO     | SALARIO |");
			System.out.println("+----------+------------+---------+");
			
			while (rs.next()) {
				System.out.println("| " + rs.getString(1) + "    | " + rs.getString(2) + " | " + rs.getInt(3) + "    |");
			}
			
			System.out.println("+----------+------------+---------+");
			
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
