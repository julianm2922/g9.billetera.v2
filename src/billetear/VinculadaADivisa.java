package billetear;

public class VinculadaADivisa extends Inversion {
	private String divisa;
	private double tasa;
	
	public VinculadaADivisa(String dniOrigen, String cvuOrigen, double monto, int plazo, boolean estaAprobado, String divisa, double tasa) {
		super(dniOrigen, cvuOrigen, monto, estaAprobado, plazo);
		
		this.desc = "VinculadaADivisa";
		this.divisa = divisa;
		this.tasa = tasa;
	}

	@Override
	public double calcularSaldoFinal() {
		// TODO Auto-generated method stub
		return 0;
	}
}
