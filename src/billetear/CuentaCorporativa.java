package billetear;

public class CuentaCorporativa extends Cuenta {
    private String cuit;

    public CuentaCorporativa(String dniPropietario, String alias, String cuit) {
        super(dniPropietario, alias);

        this.cuit = cuit;
        this.tipo = "CuentaCorporativa";
    }
    /*
    @Override
    public void extraer(double monto) {
        // verificarAcceso();
        super.extraer(monto);
    }

    @Override
    
    public void depositar(double monto) {
        // verificarAcceso();
        super.depositar(monto);
    }*/
    
    public String cuit() {
    	return cuit;
    }

    @Override
    public String toString() {
        return super.prepararToString()
            .append("\"; cuit: \"")
            .append(cuit)
            .append("\")")
            .toString();
    }

    /*
    private void verificarAcceso() throws RuntimeException {
    	if (super.propietario() instanceof Persona) {
    		Persona usuario = (Persona) super.propietario();
    		if (!(
	            usuario.estaAutorizado() && 
	            usuario.cuitAutorizado() == cuit
	        )) {
	            throw new RuntimeException("El usuario no está autorizado para operar en esta cuenta.");
	        }
    	}
        
    }*/
}
