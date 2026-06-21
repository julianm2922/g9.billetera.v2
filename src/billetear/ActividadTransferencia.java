package billetear;

public class ActividadTransferencia extends Actividad {
	protected Cuenta destino;
	
	public ActividadTransferencia(Cuenta origen, Cuenta destino, double monto, boolean estaAprobado) {
		super(origen, monto, estaAprobado);

		this.destino = destino; 
	}
	
	public Cuenta destino() {
		return destino;
	}
	
	
	public StringBuilder prepararToString() {
        return super.prepararToString()
    		.append("\"; dniDestino: \"")
            .append(destino.dniPropietario())
    		.append("\"; cvuDestino: \"")
            .append(destino.cvu());
    }

    @Override
    public String toString() {
        return prepararToString()
            .append("\")")
            .toString();
    }

}
