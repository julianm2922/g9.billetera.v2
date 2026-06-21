package billetear;
import java.time.LocalDate; 

public abstract class Actividad {
	private static int punteroId = 0;
	protected int idActividad;
	private LocalDate fecha;
	protected Cuenta origen;
	protected double monto;
	private boolean estaAprobado;

	public Actividad (Cuenta origen, double monto, boolean estaAprobado) {
		this.idActividad = punteroId++;
		this.fecha = LocalDate.now();
		this.origen = origen;
		this.monto = monto;
		this.estaAprobado = estaAprobado;
	}

	public double monto() {
		return monto;
	}

	public boolean estaAprobado() {
		return estaAprobado;
	}

	public StringBuilder prepararToString() {
        return new StringBuilder()
        	.append("(idActividad: \"")
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

    @Override
    public String toString() {
        return prepararToString()
            .append("\")")
            .toString();
    }

}