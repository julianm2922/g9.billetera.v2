package billetear;

import java.time.LocalDate;

public class FondoDeLiquidezEmpresarial extends Inversion {
	private double montoMinimo;
	
	public FondoDeLiquidezEmpresarial (String dniOrigen, String cvuOrigen, double monto, int plazo, boolean estaAprobado) {
		super (dniOrigen, cvuOrigen, monto, estaAprobado, plazo);
		this.desc = "FondoDeLiquidezEmpresarial";
		this.montoMinimo = 500000d;
	}

	@Override
	public double calcularSaldoFinal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCvuTitular() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDniTitular() {
		// TODO Auto-generated method stub
		return null;
	}

}
