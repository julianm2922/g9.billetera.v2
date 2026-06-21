package billetear;

public class CuentaCorporativa extends Cuenta {
    private String cuit;

    public CuentaCorporativa(String dniPropietario, String alias, String cuit) {
        super(dniPropietario, alias);

        this.cuit = cuit;
        this.tipo = "CuentaCorporativa";
    }
    
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

}
