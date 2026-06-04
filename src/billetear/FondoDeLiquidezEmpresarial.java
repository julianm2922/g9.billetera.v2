package billetear;

public class FondoDeLiquidezEmpresarial extends Inversion {
	private int montoMinimo ;
	
	public FondoDeLiquidezEmpresarial (int idActividad, LocalDate fecha, String origen, String desc, double monto, LocalDate Plazo, boolean estaAprobado, int montoMinimo) {
		super (idActividad, fecha, origen, monto, estaAprobada);
		this.montoMinimo = montoMinimo;
	}

}
