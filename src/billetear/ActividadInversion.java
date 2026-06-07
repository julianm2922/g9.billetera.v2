package billetear;

public class ActividadInversion extends Actividad {
	private String desc;
	private int plazo;
	
	public ActividadInversion(String desc, Cuenta origen, double monto, boolean estaAprobado, int plazo) {
		super(origen, monto, estaAprobado);
		this.desc = desc;
		this.plazo = plazo;
	}

	@Override
	public Cuenta titular() {
		return origen;
	}
	
}
