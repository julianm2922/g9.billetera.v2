package billetear;

public class TransferenciaSaliente extends Transferencia {
	public TransferenciaSaliente(String dniOrigen, String cvuOrigen, String dniDestino, String cvuDestino, double monto,
			boolean estaAprobado) {
		super(dniOrigen, cvuOrigen, dniDestino, cvuDestino, monto, estaAprobado);
	}

	public String getCvuTitular() {
		return this.cvuOrigen;
	}
	
	public String getDniTitular() {
		return this.dniOrigen;
	}
}
