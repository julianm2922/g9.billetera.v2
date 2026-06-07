package billetear;

public class ActividadTransferenciaEntrante extends ActividadTransferencia {
	public ActividadTransferenciaEntrante(Cuenta origen, Cuenta destino, double monto, boolean estaAprobado) {
		super(origen, destino, monto, estaAprobado);
	}

	public Cuenta titular() {
		return destino;
	}
}
