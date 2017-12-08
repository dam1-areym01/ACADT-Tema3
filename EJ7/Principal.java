package EJ7;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Alberto Rey Moreno
 */

public class Principal {
	/**
	 * Conecta con el gestor de base de datos y ejecuta los métodos creados.
	 */
	public static void getConexionBase() {
		try {

			String baseDatos = "sqlite";

			Connection conexion = ConexionBBDD.conexionBBDD(baseDatos);
			AplicacionesTabla.getColumnasInfo(conexion);
			
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
