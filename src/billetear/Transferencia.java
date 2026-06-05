package billetear;

import java.time.LocalDate;

public class Transferencia extends Actividad {
	private String dniDestino;
	private String cvuDestino;
	
	public Transferencia(String dniOrigen, String cvuOrigen, String dniDestino, String cvuDestino, double monto, boolean estaAprobado) {
		super(dniOrigen, cvuOrigen, monto, estaAprobado);

		this.dniDestino = dniDestino;
		this.cvuDestino = cvuDestino;
	}

	public String getCvuDestino() {
		return cvuDestino;
	}
	
	@Override
	public boolean contieneCvu(String cvu) {
		return cvuOrigen.equals(cvu) || cvuDestino.equals(cvu);
	}
	
	public StringBuilder __toString() {
        return new StringBuilder(super.toString())
    		.append("\"; dniDestino: \"")
            .append(dniDestino)
    		.append("\"; cvuDestino: \"")
            .append(cvuDestino);
    }

}
