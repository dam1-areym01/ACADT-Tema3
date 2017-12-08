package EJ5;

import java.sql.*;
import java.util.Scanner;

/**
 * @author Alberto Rey Moreno
 */

public class Principal {

	/**
	 * Pido un int para el menu de opciones.
	 * 
	 * @return int
	 */
	public static int pedirInt() {
		Scanner scan = new Scanner(System.in);
		return scan.nextInt();

	}

	/**
	 *  Menu para returnar cual de las conexiones a realizar.
	 * 
	 */
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
				ConexionBBDD.oracleBBDD();
				break;
			case 2:
				ConexionBBDD.sqliteBBDD();
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

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
	}

}
