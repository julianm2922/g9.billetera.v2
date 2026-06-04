package billetear;

public class CuentaRegular extends Cuenta {
	private final double montoMaximo = 500000.00d;
	
    public CuentaRegular(String dniPropietario, String alias) {
		super(dniPropietario, alias);
		// TODO Auto-generated constructor stub
		this.tipo = "CuentaRegular";
	}

    @Override
    public void depositar(double monto) {
        if (monto + super.saldoDisponible() > montoMaximo) {
            throw new IllegalStateException("El monto a depositar excede el monto máximo asignado al tipo de cuenta.");
        }

        super.depositar(monto);
    }
}
