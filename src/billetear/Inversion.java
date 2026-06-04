package billetear;

import java.time.LocalDate;

public abstract class Inversion extends Actividad  {
	private String desc; 
	private LocalDate plazo;
	private boolean fuePrecancelado; 
	
	public Inversion (String dniOrigen, String cvuOrigen, double monto, boolean estaAprobado, LocalDate plazo, String desc) {
		super(dniOrigen, cvuOrigen, monto, estaAprobado);
		
		this.fuePrecancelado = false;
		this.desc = desc ;
		this.plazo = plazo;
		
	}

	public boolean getFuePrecancelado () {
		return fuePrecancelado;
		
	}
	
	public LocalDate getPlazo() {
		return plazo;
		
	}
	
	public abstract double calcularSaldoFinal();
	
}

