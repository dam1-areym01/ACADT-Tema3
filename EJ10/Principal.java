package EJ10;

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
