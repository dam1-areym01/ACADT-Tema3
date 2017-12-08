package EJ9;

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
	 * Pide un entero y devuelve un entero con una cadena determinada
	 * 
	 * @param cadena
	 * @return
	 */
	public static int pedirInt(String cadena) {
		Scanner scan = new Scanner(System.in);
		System.out.print(cadena);
		return scan.nextInt();
	}

	/**
	 * Conecta con el gestor de base de datos y ejecuta los métodos creados.
	 */
	public static void getConexionBase() {
		try {

			String baseDatos = "mysql";
			int departamento = 0;
			
			Connection conexion = ConexionBBDD.conexionBBDD(baseDatos);
			DatabaseMetaData dbmd = conexion.getMetaData();
			
			String comprSQL = "SELECT * FROM departamentos WHERE dept_no = ?";
			PreparedStatement pst = conexion.prepareStatement(comprSQL);
			
			departamento = pedirInt("Departamento a consultar: ");
			pst.setInt(1, departamento);
			ResultSet rs = pst.executeQuery();
			
			if (rs != null) {
			consultaPreparada(conexion, departamento);
			} else {
				System.out.println("No existe tal departamento");
			}
			conexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Consulta el nombre del departamento
	 * 
	 * @param conexion
	 * @param departamento
	 * @throws SQLException
	 */
	public static void consultaDepartamentos(Connection conexion, int departamento) throws SQLException {
		
		String sql = "SELECT dnombre FROM departamentos WHERE dept_no = ?";
		
		PreparedStatement pst = conexion.prepareStatement(sql);
		pst.setInt(1, departamento);
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {
			System.out.println("Departamento: " + rs.getString("dnombre"));
		}
		
		rs.close();
		pst.close();
		
	}

	public static void consultaPreparada (Connection conexion, int departamento) throws SQLException {
		
		String sql = "SELECT apellido, salario, oficio FROM empleados WHERE dept_no = ?";
		
		PreparedStatement pst = conexion.prepareStatement(sql);
		pst.setInt(1, departamento);
		ResultSet rs = pst.executeQuery();
		
		int sumaSalarios = 0;
		int sumaEmpleados = 0;

		consultaDepartamentos(conexion, departamento);
		
		while (rs.next()) {
			System.out.println("\tEmpleado: " + rs.getString("apellido") + "\n\tSalario: " + rs.getInt("salario") + "\n\tOficio: " + rs.getString("oficio"));
			sumaEmpleados++;
			sumaSalarios += rs.getInt("salario");
		}
				
		int mediaSalarios = sumaSalarios / sumaEmpleados;
		System.out.println("\t\tEl salario medio de los empleados es: " + mediaSalarios + " €.");
		System.out.println("\t\tEl número de empleados contabilizados es: " + sumaEmpleados + ".");	
		
		rs.close();
		pst.close();
		
	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		getConexionBase();
	}

}
