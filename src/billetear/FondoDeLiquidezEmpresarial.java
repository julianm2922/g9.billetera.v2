package billetear;

public class FondoDeLiquidezEmpresarial extends VinculadaADivisa {
	private static double montoMinimo = 500000.00d;
	
	public FondoDeLiquidezEmpresarial (Cuenta origen, double monto, int plazo, boolean estaAprobado) {
		super(origen, monto, plazo, estaAprobado, "FLE", 0.08d);
		if (monto < montoMinimo) throw new IllegalArgumentException("Inversión inicial insuficiente.");
		this.desc = "FondoDeLiquidezEmpresarial";

	}

}
