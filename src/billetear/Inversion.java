package billetear;

import java.time.LocalDate;

public abstract class Inversion extends Actividad  {
	protected String desc; 
	private int plazo;
	private boolean fuePrecancelado; 
	
	public Inversion (String dniOrigen, String cvuOrigen, double monto, boolean estaAprobado, int plazo) {
		super(dniOrigen, cvuOrigen, monto, estaAprobado);
		
		this.fuePrecancelado = false;
		this.plazo = plazo;
		
	}

	public int getIdActividad() {
		return this.idActividad;
	}
	
	public boolean getFuePrecancelado () {
		return fuePrecancelado;
		
	}
	
	public int getPlazo() {
		return plazo;
	}
	
	@Override
	public String getCvuTitular() {
		return cvuOrigen;
	}

	@Override
	public String getDniTitular() {
		return dniOrigen;
	}
	
	public abstract double calcularSaldoFinal();
	
}

