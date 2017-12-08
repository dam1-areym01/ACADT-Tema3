package EJ7;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

public class AplicacionesTabla {

	/**
	 * Recibiendo los datos requeridos para su uso devuelvo el nombre, el tipo, el
	 * tamaño y si puede ser nulo o no de la base de datos.
	 * 
	 * @param bd
	 * @param esquema
	 * @param tabla
	 * @param conexion
	 */
	public static void getColumnaTabla(String bd, String esquema, String tabla, Connection conexion) {

		try {
			ResultSet columnas = null;
			DatabaseMetaData dbmd = conexion.getMetaData();

			System.out.println("\nCOLUMNAS DE LA TABLA " + tabla + ":");
			System.out.println("===================================");

			columnas = dbmd.getColumns(null, esquema, tabla, null);

			while (columnas.next()) {
				String nombreColumna = columnas.getString("COLUMN_NAME");
				String tipoColumna = columnas.getString("TYPE_NAME");
				String tamannoColumna = columnas.getString("COLUMN_SIZE");
				String nulaColumna = columnas.getString("IS_NULLABLE");

				System.out.println("Columna: " + nombreColumna + " | Tipo: " + tipoColumna + " | Tamaño: "
						+ tamannoColumna + " | ¿Puede ser nula? " + nulaColumna);
				;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Recibiendo los datos requeridos para su uso devuelvo las claves primarias y
	 * las referenciadas de las tablas de la base de datos.
	 * 
	 * @param tipo
	 * @param bd
	 * @param esquema
	 * @param tabla
	 * @param conexion
	 */
	public static void getClaves(String tipo, String bd, String esquema, String tabla, Connection conexion) {

		try {

			ResultSet pk = null;
			ResultSet fk = null;
			DatabaseMetaData dbmd = conexion.getMetaData();

			if (tipo.equalsIgnoreCase("PK")) {
				System.out.println("\nCLAVES PRIMARIAS DE LA TABLA " + tabla + ":");
				System.out.println("=============================================");
				pk = dbmd.getPrimaryKeys(null, esquema, tabla);
				String pk_table = "";
				String separador = "";

				while (pk.next()) {
					pk_table += separador + pk.getString("COLUMN_NAME");
					separador = "+";
				}
				System.out.println("Clave Primaria: " + pk_table);
				tipo = "FK";

			} else {

				System.out.println("\nCLAVES REFERENCIADAS DE LA TABLA " + tabla + ":");
				System.out.println("=================================================");
				fk = dbmd.getExportedKeys(null, esquema, tabla);

				while (fk.next()) {
					String fk_name = fk.getString("FKCOLUMN_NAME");
					String pk_name = fk.getString("PKCOLUMN_NAME");
					String pk_tablename = fk.getString("PKTABLE_NAME");
					String fk_tablename = fk.getString("FKTABLE_NAME");
					System.out.println("Tabla PK: " + pk_tablename + ", Clave Primaria: " + pk_name);
					System.out.println("Tabla FK: " + fk_tablename + ", Clave Ajena: " + fk_name);

				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Muestro la info de la tabla empleados de SQLITE
	 * @param conexion
	 */
	public static void getColumnasInfo(Connection conexion) {

		String query = "SELECT * FROM EMPLEADOS";

		try {
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			int nColumnas = rsmd.getColumnCount();
			String nulo;

			System.out.println("Número de columnas recuperadas: " + nColumnas);

			for (int i = 1; i <= nColumnas; i++) {
				System.out.println("Columna: " + i + ":");
				System.out.println("\tNombre: " + rsmd.getColumnName(i));
				System.out.println("\tTipo: " + rsmd.getColumnTypeName(i));

				if (rsmd.isNullable(i) == 0) {
					nulo = "NO";
				} else {
					nulo = "SI";
				}

				System.out.println("\t¿Puede ser nula? " + nulo);
				System.out.println("\tMáximo ancho de la columna: " + rsmd.getColumnDisplaySize(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
