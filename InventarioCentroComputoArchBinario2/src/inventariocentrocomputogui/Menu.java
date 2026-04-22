package inventariocentrocomputogui;
/**
 *
 * @author Lenovo
 */
import java.util.Scanner;
public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArchivoInventario inventario = new ArchivoInventario();

        int opcion;

        do {
            System.out.println("\n--- INVENTARIO CENTRO DE COMPUTO ---");
            System.out.println("1. Alta");
            System.out.println("2. Baja");
            System.out.println("3. Cambio");
            System.out.println("4. Consulta general");
            System.out.println("5. Buscar por ID");
            System.out.println("0. Salir");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Descripción: ");
                    String desc = sc.nextLine();

                    System.out.print("Existencia: ");
                    int ex = sc.nextInt();

                    inventario.agregarProducto(new Producto(id, nombre, desc, ex));
                    break;

                case 2:
                    System.out.print("ID a eliminar: ");
                    if (inventario.eliminar(sc.nextInt())) {
                        System.out.println("Eliminado.");
                    } else {
                        System.out.println("No encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("ID a modificar: ");
                    int idMod = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo nombre: ");
                    String n = sc.nextLine();

                    System.out.print("Nueva descripción: ");
                    String d = sc.nextLine();

                    System.out.print("Nueva existencia: ");
                    int e = sc.nextInt();

                    if (inventario.modificar(idMod, n, d, e)) {
                        System.out.println("Modificado.");
                    } else {
                        System.out.println("No encontrado.");
                    }
                    break;

                case 4:
                    inventario.listar();
                    break;

                case 5:
                    System.out.print("ID a buscar: ");
                    Producto p = inventario.buscar(sc.nextInt());
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        System.out.println("No encontrado.");
                    }
                    break;

            }

        } while (opcion != 0);

        sc.close();
    }
}