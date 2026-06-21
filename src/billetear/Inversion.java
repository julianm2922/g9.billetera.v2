package billetear;

import java.time.LocalDate;

public abstract class Inversion extends Actividad {
	protected LocalDate fechaCreacion;
	protected LocalDate fechaCierre;
	protected String desc;
	private int plazo;
	protected boolean fuePrecancelado;

	public Inversion (Cuenta origen, double monto, boolean estaAprobada, int plazo) {
		super(origen, monto, estaAprobada);
		this.fuePrecancelado = false;
		this.plazo = plazo;
		this.fechaCreacion = Utilitarios.hoy();
	}

	public Cuenta origen() {
		return origen;
	}
	public int id() {
		return idActividad;
	}

	public int plazo() {
		return plazo;
	}

	public boolean fuePrecancelado() {
		return fuePrecancelado;
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

    public StringBuilder prepararToString() {
        return super.prepararToString()
            .append("\"; desc: \"").append(desc)
            .append("\"; plazo: \"").append(plazo)
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

}
