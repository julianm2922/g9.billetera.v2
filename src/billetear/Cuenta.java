package billetear;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private String cvu;
    private String alias;
    protected String tipo;
    protected double saldo;
    protected String dniPropietario;
    protected ArrayList<Actividad> actividades;

    public Cuenta(String dniPropietario, String alias) {
        validarAlias(alias);

        this.alias = alias;
        this.dniPropietario = dniPropietario;
        this.saldo = 0;
        this.cvu = Utilitarios.generarSiguienteCvu();
        this.actividades = new ArrayList<Actividad>();
    }

    public void agregarActividad(Actividad actividad) {
    	actividades.add(actividad);
    }

    public List<Actividad> actividades() {
    	return actividades;
    }

    public String cvu() { 
        return cvu; 
    }
    
    public String alias() { 
        return alias; 
    }

    public String dniPropietario() { 
        return dniPropietario;
    }

    public double saldoDisponible() { 
        return saldo; 
    }
    
    public String tipo() {
    	return tipo;
    }

    public void extraer(double monto) throws IllegalStateException {
        if (monto > saldo) {
            throw new IllegalStateException("No hay fondos suficientes en la cuenta para realizar la extracción.");
        }

        saldo -= monto;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    /*
    public void agregarInversion();
    */

    protected StringBuilder prepararToString() {
        return new StringBuilder()
            .append(tipo)
            .append(" (cvu: \"").append(cvu)
            .append("\"; alias: \"").append(alias)
            .append("\"; saldo: \"").append(saldo)
            .append("\"; dniPropietario: \"").append(dniPropietario);
    }

    @Override
    public String toString() {
        return prepararToString()
            .append("\")")
            .toString();
    }
    
    /** PRIVADOS */
    private void validarAlias(String alias) throws RuntimeException {
        if (!(
            alias != null && 
            alias.matches("^[a-z0-9.]+$") && 
            alias.length() >= 5 && 
            alias.length() <= 20
        )) {
            throw new RuntimeException("Alias inválido.");
        }
    }


}
