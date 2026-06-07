package billetear;

public class VinculadaADivisa extends Inversion {
	private String divisa;
	private double tasa;
	private double montoInicialDivisa;
	private double montoFinalDivisa;
	
	public VinculadaADivisa(Cuenta origen, double monto, int plazo, boolean estaAprobado, String divisa, double tasa) {
		super(origen, monto, estaAprobado, plazo);
		
		this.desc = "VinculadaADivisa";
		this.divisa = divisa;
		this.tasa = tasa;
		this.montoInicialDivisa = montoEnDivisa();
	}

	private double montoEnDivisa() {
		return monto / Utilitarios.consultarCotizacion(divisa);
	}
	
	@Override
	public void precancelar() {
		super.precancelar();
	}
	
	@Override
	public double calcularRendimientos() {
		int divisor = fuePrecancelado ? 2 : 1;
		return (montoInicialDivisa * (tasa / 365) * diasTranscurridos()) / 2;
	}
	
	@Override
	public double calcularSaldoFinal() {
		return (montoInicialDivisa + calcularRendimientos()) * Utilitarios.consultarCotizacion(divisa);
	}
}
