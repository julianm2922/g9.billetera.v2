package billetear;

public class ActividadTransferenciaSaliente extends ActividadTransferencia {
	public ActividadTransferenciaSaliente(Cuenta origen, Cuenta destino, double monto, boolean estaAprobado) {
		super(origen, destino, monto, estaAprobado);
	}

	public Cuenta titular() {
		return origen;
	}
}
