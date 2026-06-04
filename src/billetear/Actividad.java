package billetear;
import java.time.LocalDate; 

public class Actividad {
	private static int idActividad = 0;
	private LocalDate fecha;
	private String dniOrigen;
	private String cvuOrigen;
	private double monto;
	private boolean estaAprobado;
	
	public Actividad (String dniOrigen, String cvuOrigen, double monto, boolean estaAprobado) {
		idActividad = idActividad++;
		this.fecha = LocalDate.now();
		this.dniOrigen = dniOrigen;
		this.cvuOrigen = cvuOrigen;
		this.monto = monto;
		this.estaAprobado = estaAprobado;
	}
	
	
	public int getIdActividad() {
		
		return idActividad;

	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	
	public String getCvuOrigen() {
		return cvuOrigen;
	}
	
	public String getDniOrigen() {
		return dniOrigen;
	}
	
	
	
	public boolean getEstaAprobado() {

		return estaAprobado;
		
		}
	
	public StringBuilder __toString() {
        return new StringBuilder()
        	.append(this.getClass())          
        	.append(" (idActividad: \"")
            .append(idActividad)
            .append("\"; fecha: \"")
            .append(fecha)
            .append("\"; dniOrigen: \"")
            .append(dniOrigen)
            .append("\"; origen: \"")
            .append(cvuOrigen)
            .append("\"; monto: \"")
            .append(monto)
            .append("\"; estaAprobado: \"")
            .append(estaAprobado);
    }
    
	
}