package billetear;

public class Persona extends Usuario {
    private String dni;
    private String cuitAutorizado;
    private boolean estaAutorizadoCuit;

    public Persona(String nombre, String numeroTelefono, String dni, String email) {
        super(nombre, numeroTelefono, email);

        this.dni = dni;
        this.estaAutorizadoCuit = false;
    }

    public void autorizarCuit(String cuit) {
        cuitAutorizado = cuit;
        estaAutorizadoCuit = true; 
    }

    public String dni() {
        return dni;
    }

    public String cuitAutorizado() {
        return cuitAutorizado;
    }

    public boolean estaAutorizado() {
        return estaAutorizadoCuit;
    }
}