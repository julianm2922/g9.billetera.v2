package billetear;

import java.time.LocalDate;

public abstract class Inversion  {
	private static int punteroId = 0;
	private int id;
	protected Cuenta origen;
	protected double monto;
	private boolean estaAprobada;
	protected LocalDate fechaCreacion;
	protected LocalDate fechaCierre;
	protected String desc; 
	private int plazo;
	protected boolean fuePrecancelado; 
	
	public Inversion (Cuenta origen, double monto, boolean estaAprobada, int plazo) {
		this.origen = origen;
		this.monto = monto;
		this.estaAprobada = estaAprobada;
		this.fuePrecancelado = false;
		this.plazo = plazo;
		this.fechaCreacion = LocalDate.now();
		this.id = punteroId++;
	}
	
	public Cuenta origen() {
		return origen;
	}
	public int id() {
		return id;
	}
	
	public int plazo() {
		return plazo;
	}
	
	public double monto() {
		return monto;
	}
	
	public boolean fuePrecancelado() {
		return fuePrecancelado;
	}
	
	public boolean estaAprobada() {
		return estaAprobada;
	}
	
	public void precancelar() {
		fechaCierre = Utilitarios.hoy();
		fuePrecancelado = true;
		monto = calcularSaldoFinal();
	}
	
	public double calcularSaldoFinal() {	
		return monto + calcularRendimientos();
	}

	protected long diasTranscurridos() {
		long inicio = fechaCreacion.toEpochDay();
		long fin = fechaCierre.toEpochDay();
		
		return fin - inicio;
	}
	
	protected abstract double calcularRendimientos();

    protected StringBuilder prepararToString() {
        return new StringBuilder()
            .append("(id: \"").append(id())
            .append("\"; desc: \"").append(desc)
            .append("\"; origen: \"").append(origen.cvu())
            .append("\"; monto: \"").append(monto)
            .append("\"; plazo: \"").append(plazo())
            .append("\"; estaAprobada: \"").append(estaAprobada())
            .append("\"; fuePrecancelado: \"").append(fuePrecancelado)
            .append("\"; fechaCreacion: \"").append(fechaCreacion)
            .append("\"; fechaCierre: \"").append(fechaCierre);
    }

    @Override
    public String toString() {
        return prepararToString()
            .append("\")")
            .toString();
    }
	// @Override
	/*
	public String getCvuTitular() {
		return cvu;
	}

	@Override
	public String getDniTitular() {
		return cvu;
	}*/
	
	
	
}

