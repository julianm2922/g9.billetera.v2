package billetear;

public class Persona extends Usuario {
    private String dni;

    public Persona(String nombre, String numeroTelefono, String dni, String email) {
        super(nombre, numeroTelefono, email);
        this.dni = dni;
    }

    public String dni() {
        return dni;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(nombre: \"").append(nombre())
            .append("\"; dni: \"").append(dni)
            .append("\")")
            .toString();
    }
}