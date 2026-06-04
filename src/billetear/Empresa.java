package billetear;

import java.util.ArrayList;

public class Empresa extends Usuario {
    private String cuit;
    private String razonSocial;
    private String email;
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
    
}