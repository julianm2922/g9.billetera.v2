package billetear;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String numeroTelefono;
    private String email;
    private ArrayList<Cuenta> cuentas;

    public Usuario(String nombre, String numeroTelefono, String email) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }
    
    public ArrayList<Cuenta> cuentas() {
    	return cuentas;
    }
    
    public String nombre() {
    	return nombre;
    }
}
