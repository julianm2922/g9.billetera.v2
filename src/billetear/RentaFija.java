package billetear;

public class RentaFija extends Inversion {
	public static final double TNA = 20.0d;
	
	public RentaFija(Cuenta origen, double monto, int plazo, boolean estaAprobado) {
		super(origen, monto, estaAprobado, plazo);
		
		this.desc = "RentaFija";
	}

	@Override
	public double calcularRendimientos() {
		int divisor = fuePrecancelado ? 2 : 1;
		return (monto * (TNA / 100 / 365) * diasTranscurridos()) / divisor;
	}
	
}
