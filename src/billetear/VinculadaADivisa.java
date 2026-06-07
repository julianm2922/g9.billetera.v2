package billetear;

public class VinculadaADivisa extends Inversion {
	private String divisa;
	private double tasa;
	private double montoInicialDivisa;
	
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
    protected StringBuilder prepararToString() {
        return super.prepararToString()
            .append("\"; divisa: \"").append(divisa)
            .append("\"; tasa: \"").append(tasa)
            .append("\"; montoInicialDivisa: \"").append(montoInicialDivisa);
    }

    @Override
    public String toString() {
        return prepararToString()
            .append("\")")
            .toString();
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
