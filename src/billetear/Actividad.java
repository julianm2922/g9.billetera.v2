package billetear;
import java.time.LocalDate; 

public abstract class Actividad {
	protected static int idActividad = 0;
	private LocalDate fecha;
	protected Cuenta origen;
	private double monto;
	private boolean estaAprobado;
	
	public Actividad (Cuenta origen, double monto, boolean estaAprobado) {
		idActividad = idActividad++;
		this.fecha = LocalDate.now();
		this.origen = origen;
		this.monto = monto;
		this.estaAprobado = estaAprobado;
	}
	
	public abstract Cuenta titular();
	
	public int getIdActividad() {
		
		return idActividad;

	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public double getMonto() {
		return monto;
	}
	
	public String getCvuOrigen() {
		return origen.cvu();
	}
	
	public String getDniOrigen() {
		return origen.dniPropietario();
	}
	
	public boolean getEstaAprobado() {
		return estaAprobado;
	}
	
	/*
	public boolean contieneCvu(String cvu) {
		return origen.cvu().equals(cvu);
	}*/
	
	public StringBuilder __toString() {
        return new StringBuilder()
        	.append(this.getClass())          
        	.append(" (idActividad: \"")
            .append(idActividad)
            .append("\"; fecha: \"")
            .append(fecha)
            .append("\"; dniOrigen: \"")
            .append(origen.dniPropietario())
            .append("\"; cvuOrigen: \"")
            .append(origen.cvu())
            .append("\"; monto: \"")
            .append(monto)
            .append("\"; estaAprobado: \"")
            .append(estaAprobado);
    }
    
	
}