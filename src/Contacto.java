/**
 * Representa un contacto individual de la agenda.
 * Solo guarda datos (nombre, telefono, email); la logica de colecciones vive en Agenda.
 */
public class Contacto {

    // Atributos privados: encapsulamiento, solo se accede por getters/setters
    private String nombre;
    private String telefono;
    private String email;

    // Constructor que recibe todos los atributos
    public Contacto(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // ----- Getters -----
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    // ----- Setters -----
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Formato: Nombre | Telefono: X | Email: Y
    @Override
    public String toString() {
        return nombre + " | Telefono: " + telefono + " | Email: " + email;
    }
}
