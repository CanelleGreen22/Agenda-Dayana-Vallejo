import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Gestiona contactos, grupos e historial usando el Java Collections Framework.
 * Regla de oro: se declara contra la interfaz (Map, Set, List)
 * y se instancia con la implementacion concreta (TreeMap, TreeSet, ArrayList).
 */
public class Agenda {

    // Map: clave unica (nombre) -> valor (Contacto). TreeMap = busqueda rapida + orden alfabetico.
    private Map<String, Contacto> contactos = new TreeMap<>();

    // Set: grupos sin repetir. TreeSet = ademas ordenados alfabeticamente.
    private Set<String> grupos = new TreeSet<>();

    // Map de Set: cada grupo -> conjunto de nombres de contactos (sin duplicados, ordenados).
    private Map<String, Set<String>> contactosPorGrupo = new TreeMap<>();

    // List: guarda el orden en que se agregaron (orden de insercion).
    private List<Contacto> historial = new ArrayList<>();

    // ================== CONTACTOS ==================

    public void agregarContacto(Contacto c) {
        // containsKey revisa si la clave (nombre) ya existe en el Map
        if (contactos.containsKey(c.getNombre())) {
            System.out.println("Error: Ya existe un contacto con el nombre '" + c.getNombre() + "'");
            return;
        }
        contactos.put(c.getNombre(), c); // Map usa put(), no add()
        historial.add(c);                // List usa add()
        System.out.println("Contacto agregado: " + c.getNombre());
    }

    public Contacto buscarContacto(String nombre) {
        // get() devuelve null si la clave no existe
        return contactos.get(nombre);
    }

    public boolean eliminarContacto(String nombre) {
        // remove() del Map devuelve el valor eliminado, o null si no existia
        Contacto eliminado = contactos.remove(nombre);
        if (eliminado == null) {
            return false;
        }

        // Quitar del historial usando un Iterator (no se puede borrar dentro de un for-each)
        Iterator<Contacto> it = historial.iterator();
        while (it.hasNext()) {
            if (it.next().getNombre().equals(nombre)) {
                it.remove();
            }
        }

        // Quitar el nombre de todos los grupos donde aparezca
        for (Set<String> miembros : contactosPorGrupo.values()) {
            miembros.remove(nombre);
        }
        return true;
    }

    public void listarContactos() {
        System.out.println("--- LISTA DE CONTACTOS (orden alfabetico) ---");
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos registrados");
            return;
        }
        // Al recorrer un TreeMap los valores salen ordenados por la clave
        for (Contacto c : contactos.values()) {
            System.out.println(c);
        }
    }

    public void listarHistorial() {
        System.out.println("--- HISTORIAL (orden de insercion) ---");
        if (historial.isEmpty()) {
            System.out.println("El historial esta vacio");
            return;
        }
        // El ArrayList conserva el orden en que se hizo add()
        for (Contacto c : historial) {
            System.out.println(c);
        }
    }

    // ================== GRUPOS ==================

    public void agregarGrupo(String nombreGrupo) {
        // add() de un Set devuelve false si el elemento ya estaba
        if (!grupos.add(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' ya existe");
            return;
        }
        contactosPorGrupo.put(nombreGrupo, new TreeSet<>());
        System.out.println("Grupo '" + nombreGrupo + "' creado");
    }

    public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
        if (!contactos.containsKey(nombreContacto)) {
            System.out.println("Error: El contacto '" + nombreContacto + "' no existe");
            return;
        }
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
            return;
        }
        contactosPorGrupo.get(nombreGrupo).add(nombreContacto);
        System.out.println("Contacto agregado al grupo '" + nombreGrupo + "'");
    }

    public void listarGrupos() {
        System.out.println("--- GRUPOS (orden alfabetico) ---");
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos registrados");
            return;
        }
        for (String g : grupos) {
            System.out.println("- " + g);
        }
    }

    public void mostrarContactosPorGrupo(String nombreGrupo) {
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: El grupo '" + nombreGrupo + "' no existe");
            return;
        }
        Set<String> miembros = contactosPorGrupo.get(nombreGrupo);
        if (miembros.isEmpty()) {
            System.out.println("El grupo '" + nombreGrupo + "' no tiene contactos");
            return;
        }
        System.out.println("Contactos en '" + nombreGrupo + "':");
        for (String nombre : miembros) {
            // El grupo guarda solo nombres; el Contacto completo se busca en el Map
            System.out.println("- " + contactos.get(nombre));
        }
    }
}
