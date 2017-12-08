package EJ6;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Alberto Rey Moreno
 */

public class ConexionBBDD {

	/**
	 * Recibo la base de datos mediante un string, y según su contenido cargo uno u otro controlador de la base de datos. 
	 * Devolviendo la conexión ya establecida.
	 * 
	 * @param bd
	 * @return
	 */
	public static Connection conexionBBDD(String bd) {

		Properties propiedades = new Properties();
		FileInputStream fis;
		Connection conexion = null;
		String database = bd.toLowerCase();

		try {
			fis = new FileInputStream("configuracion.properties");
			propiedades.load(fis);

			switch (database) {
			case "oracle":
				Class.forName(propiedades.getProperty("driver-" + database));
				conexion = DriverManager.getConnection(propiedades.getProperty("conexion-" + database));
				break;

			case "mysql":
				Class.forName(propiedades.getProperty("driver-" + database));
				conexion = DriverManager.getConnection(propiedades.getProperty("conexion-" + database),	propiedades.getProperty("usuario-" + database), "");
				break;
			
			case "sqlite":
				Class.forName(propiedades.getProperty("driver-" + database));
				conexion = DriverManager.getConnection(propiedades.getProperty("conexion-" + database));
				break;
			default:
				System.out.println("No existe la base de datos.");
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conexion;

	}
	
}
