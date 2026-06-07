package billetear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Actividades {
	private static ArrayList<Actividad> actividades;
	
	public static void inicializar() {
		actividades = new ArrayList<Actividad>();
	}
	
	public static void registrarInversionFondoLiquidez(Inversion i) {
		registrarInversion("FondoLiquidezEmpresarial", i); 
	}
	
	public static void registrarInversionVinculadaADivisa(Inversion i) {
		registrarInversion("VinculadaADivisa", i); 
	}
	
	public static void registrarInversionRentaFija(Inversion i) {
		registrarInversion("RentaFija", i); 
	}
	
	public static void registrarTransferencia(Cuenta ctaOg, Cuenta ctaDes, double monto) {
		actividades.add(new ActividadTransferenciaSaliente(ctaOg, ctaDes, monto, true));
		actividades.add(new ActividadTransferenciaEntrante(ctaOg, ctaDes, monto, true));
	}
	
	private static void registrarInversion(String tipo, Inversion i) {
		actividades.add(new ActividadInversion(tipo, i.origen(), i.monto(), i.estaAprobada(), i.plazo()));
	}
	
	public static ArrayList<Actividad> obtenerTodas() {
		return actividades;
	}
	
	public static ArrayList<String> obtenerLista() {
    	ArrayList<String> lista = new ArrayList<String>();
    	
    	for (Actividad act : actividades) {
    		lista.add(act.toString());
    	}
    	
    	return lista;
	}
	
	public static ArrayList<String> obtenerListaPorCvu(String cvu) {
		ArrayList<String> result = new ArrayList<String>();
    	for (Actividad act : actividades) {
    		if (act.titular().cvu().equals(cvu)) {
    			result.add(act.toString());
    		}
    	}
    	
    	return result;
	}
	
	public static ArrayList<String> obtenerListaPorDni(String dni) {
		ArrayList<String> result = new ArrayList<String>();
    	for (Actividad act : actividades) {
    		if (act.titular().dniPropietario().equals(dni)) {
    			result.add(act.toString());
    		}
    	}
    	
    	return result;
	}
	
	public static ArrayList<String> cuentasConMayorVolumen(int cantCtas) {
		HashMap<Cuenta, Integer> ctaCant = new HashMap<Cuenta, Integer>();
		
		for (Actividad act : actividades) {
			Cuenta cuenta = act.titular();
			int cant = ctaCant.containsKey(cuenta) ? ctaCant.get(cuenta) + 1 : 1; 
			ctaCant.put(cuenta, cant);
		}

		ArrayList<Map.Entry<Cuenta, Integer>> ctaCantOrden = new ArrayList<Map.Entry<Cuenta, Integer>>(ctaCant.entrySet()); 
	 
		int n = ctaCantOrden.size();
		for (int i = 0; i < n - 1; i++) {
		    for (int j = 0; j < n - i - 1; j++) {
		        Map.Entry<Cuenta, Integer> actual = ctaCantOrden.get(j);
		        Map.Entry<Cuenta, Integer> siguiente = ctaCantOrden.get(j + 1);
		         
		        if (actual.getValue() < siguiente.getValue()) {
		            Map.Entry<Cuenta, Integer> temp = actual;
		            ctaCantOrden.set(j, siguiente);
		            ctaCantOrden.set(j + 1, temp);
		        }
		    }
		}
		
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < cantCtas; i++) {
			Cuenta cuenta = ctaCantOrden.get(i).getKey();
			int cantidad = ctaCantOrden.get(i).getValue();
			result.add(cuenta.tipo() + ": " + cuenta.alias() + " (" + cuenta.cvu() + ") " + cantidad);
		}
		
		System.out.println(result);
		
		return result;
	}
}
