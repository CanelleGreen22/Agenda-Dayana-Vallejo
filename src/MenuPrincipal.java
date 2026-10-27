import java.util.Scanner;

/**
 * Menu interactivo de consola para la Agenda de Contactos.
 */
public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        boolean salir = false;

        // Bucle while: se repite hasta que el usuario elija "Salir"
        while (!salir) {
            mostrarMenu();

            // hasNextInt() valida ANTES de leer, para no romper el programa con letras
            if (!scanner.hasNextInt()) {
                scanner.nextLine(); // consumir la entrada invalida
                System.out.println("Opcion invalida, intenta de nuevo");
                continue;
            }
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el salto de linea que deja nextInt()
            System.out.println();

            switch (opcion) {
                case 1: {
                    System.out.println("--- AGREGAR CONTACTO ---");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine().trim();
                    System.out.print("Telefono: ");
                    String telefono = scanner.nextLine().trim();
                    System.out.print("Email: ");
                    String email = scanner.nextLine().trim();
                    agenda.agregarContacto(new Contacto(nombre, telefono, email));
                    break;
                }
                case 2: {
                    System.out.print("Nombre a buscar: ");
                    String nombre = scanner.nextLine().trim();
                    Contacto c = agenda.buscarContacto(nombre);
                    if (c != null) {
                        System.out.println(c);
                    } else {
                        System.out.println("Contacto no encontrado: " + nombre);
                    }
                    break;
                }
                case 3: {
                    System.out.print("Nombre a eliminar: ");
                    String nombre = scanner.nextLine().trim();
                    if (agenda.eliminarContacto(nombre)) {
                        System.out.println("Contacto eliminado: " + nombre);
                    } else {
                        System.out.println("Contacto no encontrado: " + nombre);
                    }
                    break;
                }
                case 4:
                    agenda.listarContactos();
                    break;
                case 5:
                    agenda.listarHistorial();
                    break;
                case 6: {
                    System.out.print("Nombre del grupo: ");
                    agenda.agregarGrupo(scanner.nextLine().trim());
                    break;
                }
                case 7: {
                    System.out.print("Nombre del contacto: ");
                    String nombreContacto = scanner.nextLine().trim();
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine().trim();
                    agenda.agregarContactoAGrupo(nombreContacto, nombreGrupo);
                    break;
                }
                case 8:
                    agenda.listarGrupos();
                    break;
                case 9: {
                    System.out.print("Nombre del grupo: ");
                    agenda.mostrarContactosPorGrupo(scanner.nextLine().trim());
                    break;
                }
                case 10:
                    System.out.println("Saliendo de la agenda...");
                    salir = true;
                    break;
                default:
                    // Numeros fuera del rango 1-10
                    System.out.println("Opcion invalida, intenta de nuevo");
            }
        }

        scanner.close(); // cerrar el Scanner al salir
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== AGENDA DE CONTACTOS ===");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Buscar contacto");
        System.out.println("3. Eliminar contacto");
        System.out.println("4. Listar contactos (orden alfabetico)");
        System.out.println("5. Listar historial (orden de insercion)");
        System.out.println("6. Agregar grupo");
        System.out.println("7. Agregar contacto a grupo");
        System.out.println("8. Listar grupos");
        System.out.println("9. Mostrar contactos por grupo");
        System.out.println("10. Salir");
        System.out.println("==========================");
        System.out.print("Elige una opcion: ");
    }
}
