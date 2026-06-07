package billetear;

public class Usuario {
    private String nombre;
    private String numeroTelefono;

    public Usuario(String nombre, String numeroTelefono, String email) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    public String nombre() {
    	return nombre;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(nombre: \"").append(nombre)
            .append("\"; numeroTelefono: \"").append(numeroTelefono)
            .append("\")")
            .toString();
    }
}
