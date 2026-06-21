package billetear;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String numeroTelefono;
    protected ArrayList<Cuenta> cuentas;

    public Usuario(String nombre, String numeroTelefono, String email) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.cuentas = new ArrayList<Cuenta>();
    }

    public String nombre() {
    	return nombre;
    }

    public void agregarCuenta(Cuenta cuenta) {
    	cuentas.add(cuenta);
    }

    public List<Cuenta> cuentas() {
    	return cuentas;
    }

    public Cuenta buscarCuenta(String cvu) {
    	for (Cuenta cuenta : cuentas) {
    		if (cuenta.cvu().equals(cvu)) {
    			return cuenta;
    		}
    	}
    	return null;
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
