package billetear;

public class FondoDeLiquidezEmpresarial extends Inversion {
	private double montoMinimo;
	
	public FondoDeLiquidezEmpresarial (Cuenta origen, double monto, int plazo, boolean estaAprobado) {
		super (origen, monto, estaAprobado, plazo);
		this.desc = "FondoDeLiquidezEmpresarial";
		this.montoMinimo = 500000d;
	}

	@Override
	public double calcularSaldoFinal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected double calcularRendimientos() {
		// TODO Auto-generated method stub
		return 0;
	}

}
