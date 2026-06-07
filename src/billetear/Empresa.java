package billetear;

import java.util.ArrayList;

public class Empresa extends Usuario {
    private String cuit;
    private String razonSocial;
    private ArrayList<String> dnisAutorizados;

    public Empresa(String razonSocial, String numeroTelefono, String cuit, String email, String nombreContacto) {
        super(nombreContacto, numeroTelefono, email);

        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.dnisAutorizados = new ArrayList<String>();
    }

    public String cuit() {
        return cuit;
    }
    
    public ArrayList<String> dnisAutorizados() {
    	return dnisAutorizados;
    }
    
    public void autorizarUsuario(String dniUsuario) {
    	if (dnisAutorizados.contains(dniUsuario))
    		throw new RuntimeException("El usuario ya se encuentra autorizado.");

    	dnisAutorizados.add(dniUsuario);
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("(nombreContacto: \"").append(nombre())
            .append("\"; razonSocial: \"").append(razonSocial)
            .append("\"; cuit: \"").append(cuit)
            .append("\"; dnisAutorizados: ").append(dnisAutorizados)
            .append(")")
            .toString();
    }

}