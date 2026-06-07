package billetear;

public class Cuenta {
    private String cvu;
    private String alias;
    private double montoEnInversiones;
    protected String tipo;
    protected double saldo;
    protected String dniPropietario;

    public Cuenta(String dniPropietario, String alias) {
        validarAlias(alias);

        this.alias = alias;
        this.dniPropietario = dniPropietario;
        this.saldo = 0;
        this.cvu = Utilitarios.generarSiguienteCvu();
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

    public double dineroInvertido() {
        return montoEnInversiones;
    }
    
    /*
    public void agregarInversion();
    */

    public StringBuilder __toString() {
        return new StringBuilder()
        	.append(this.getClass())          
        	.append(" (cvu: \"")
            .append(cvu)
            .append("\"; alias: \"")
            .append(alias)
            .append("\"; saldo: \"")
            .append(saldo)
            .append("\"; montoEnInversiones: \"")
            .append(montoEnInversiones)
            .append("\"; dniPropietario: \"")
            .append(dniPropietario);
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
