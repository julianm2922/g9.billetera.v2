package billetear;

public abstract class ActividadTransferencia extends Actividad {
	protected Cuenta destino;
	
	public ActividadTransferencia(Cuenta origen, Cuenta destino, double monto, boolean estaAprobado) {
		super(origen, monto, estaAprobado);

		this.destino = destino; 
	}
	
	public Cuenta destino() {
		return destino;
	}
	
	/*
	@Override
	public boolean contieneCvu(String cvu) {
		return cvuOrigen.equals(cvu) || cvuDestino.equals(cvu);
	}*/
	
	public StringBuilder __toString() {
        return new StringBuilder(super.toString())
    		.append("\"; dniDestino: \"")
            .append(destino.dniPropietario())
    		.append("\"; cvuDestino: \"")
            .append(destino.cvu());
    }

}
