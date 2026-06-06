package billetear;

public class TransferenciaEntrante extends Transferencia {
	public TransferenciaEntrante(String dniOrigen, String cvuOrigen, String dniDestino, String cvuDestino, double monto,
			boolean estaAprobado) {
		super(dniOrigen, cvuOrigen, dniDestino, cvuDestino, monto, estaAprobado);
	}

	public String getCvuTitular() {
		return this.cvuDestino;
	}
	
	public String getDniTitular() {
		return this.dniDestino;
	}
}
