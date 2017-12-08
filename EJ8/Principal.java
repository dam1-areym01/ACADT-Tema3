package EJ8;

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

	public static int pedirInt(String cadena) {
		Scanner scan = new Scanner(System.in);
		System.out.println(cadena);
		return scan.nextInt();
	}

	public static String pedirString(String cadena) {
		Scanner scan = new Scanner(System.in);
		System.out.println(cadena);
		return scan.nextLine();
	}

	/**
	 * Conecta con el gestor de base de datos y ejecuta los métodos creados.
	 */
	public static void getConexionBase() {
		try {
			String baseDatos = "mysql";
			Connection conexion = ConexionBBDD.conexionBBDD(baseDatos);
			insertarEmpleados(conexion);
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertarEmpleados(Connection conexion) throws SQLException {

		// INSERT INTO empleados (emp_no, apellido, oficio, fecha_alt, salario,
		// comision, dept_no) VALUES (1, 'LOPEZ', 'PROGRAMADOR', TO_DATE('03/07/2014'),
		// 1200, 100, 10);

		int emp_no = pedirInt("Nº Empleado: ");
		String apellido = pedirString("Apellido: ");
		String oficio = pedirString("Oficio: ");
		// Fecha
		int salario = pedirInt("Salario: ");
		int comision = pedirInt("Comisión: ");
		int dept_no = pedirInt("Nº Departamento: ");

		if ((numEmp(conexion, emp_no) == true) && (numDep(conexion, dept_no) == true)
				&& (comprSalario(salario) == true)) {
			
			Statement st = conexion.createStatement();
			String sql = "INSERT INTO empleados (emp_no, apellido, oficio, fecha_alt, salario, comision, dept_no) VALUES ("
					+ emp_no + ", '" + apellido + "', '" + oficio + "', sysdate(), " + salario + ", " + comision + ", " + dept_no + ")";
			
			System.out.println("Se va a ingresar la siguiente orden en la BBDD de ACADT en la tabla de EMPLEADOS: ");
			System.out.println("\n\t" + sql);
			
			st.executeUpdate(sql);
			
			System.out.println("\n - INSERCIÓN COMPLETADA - ");
		}
	}

	private static boolean comprSalario(int salario) {
		if (salario > 0) {
			return true;
		} else {
			System.out.println("No se insertará ningún registro ya que el salario es menor o igual a 0.");
			return false;
		}
	}

	private static boolean numEmp(Connection conexion, int emp_no) throws SQLException {
		Statement st = conexion.createStatement();
		String query = "SELECT emp_no FROM empleados WHERE emp_no=" + emp_no;

		if (st.executeQuery(query) != null) {
			return true;
		} else {
			System.out.println("No se insertará ningún registro ya que el ID de empleado ya se encuentra en la BBDD.");
			return false;
		}
	}

	private static boolean numDep(Connection conexion, int dept_no) throws SQLException {
		Statement st = conexion.createStatement();
		String query = "SELECT dept_no FROM departamentos WHERE dept_no=" + dept_no;

		if (st.executeQuery(query) != null) {
			return true;
		} else {
			System.out.println(
					"No se insertará ningún registro ya que el ID de departamento no se encuentra en la BBDD.");
			return false;
		}
	}

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		getConexionBase();
	}

}
