package billetear;

import java.time.LocalDate;

public class RentaFija extends Inversion {

	public RentaFija(String dniOrigen, String cvuOrigen, double monto, int plazo, boolean estaAprobado) {
		super(dniOrigen, cvuOrigen, monto, estaAprobado, plazo);
		
		this.desc = "RentaFija";
	}

	@Override
	public double calcularSaldoFinal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
