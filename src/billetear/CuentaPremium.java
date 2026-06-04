package billetear;

public class CuentaPremium extends Cuenta {
    private final double depositoInicialMinimo = 500000.00d;
    
    public CuentaPremium(String dniPropietario, String alias, double depositoInicial) {
        super(dniPropietario, alias);
        validarDepositoInicial(depositoInicial);
        
        super.depositar(depositoInicial);
        this.tipo = "CuentaPremium";
    }

    private void validarDepositoInicial(double depositoInicial) throws IllegalArgumentException {
        if (depositoInicial < depositoInicialMinimo) {
            throw new IllegalArgumentException("El tipo de cuenta requiere un depósito inicial de " + depositoInicialMinimo);
        }
    }
}
