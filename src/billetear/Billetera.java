package billetear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Billetera implements IBilletera{

	private HashMap<String, Usuario> usuarios;
	private HashMap<String, Empresa> empresas;

	public Billetera() {
		usuarios = new HashMap<String, Usuario>();
		empresas = new HashMap<String, Empresa>();
	}
	//-----------------------------------------------------------------------------------
	/**
     * [Nuevo]
     * 11) Registra una nueva empresa en el sistema.
     * Lanza error si la empresa ya esta registrada o algun campo es inválido.
     *
     * @param cuit           El CUIT de la empresa.
     * @param nombreFantasia El nombre de fantasía de la empresa.
     * @param telefono       El teléfono de contacto de la empresa.
     * @param email          El correo electrónico de la empresa.
     * @param nombreContacto El nombre de la persona de contacto.
     */
	public void registrarEmpresa(String cuit, String nombreFantasia, String telefono, String email, String nombreContacto){
		 Empresa empresa = new Empresa(nombreFantasia, telefono, cuit, email, nombreContacto);

		 empresas.put(cuit, empresa);
	 }
	 /**
	     * [Nuevo]
	     * 12) Agrega una persona autorizada a operar en nombre de una empresa.
	     * Lanza error si la empresa no existe o la persona ya está autorizada.
	     *
	     * @param cuitEmpresa   El CUIT de la empresa a la cual se agregará el
	     *                      autorizado.
	     * @param dniAutorizado El DNI de la persona autorizada (puede no estar
	     *                      registrado aún en el sistema).
	     */
	public void agregarPersonaAutorizada(String cuitEmpresa, String dniAutorizado) {
    	if (!empresas.containsKey(cuitEmpresa)) throw new RuntimeException("No existe la empresa.");

    	Empresa empresa = empresas.get(cuitEmpresa);
    	empresa.autorizarUsuario(dniAutorizado);
    }


	    /**
	     * 1) Registra un nuevo usuario en la plataforma.
	     * Lanza error si el usuario ya está registrado o algun campo es inválido.
	     *
	     * @param dni      El DNI del usuario.
	     * @param nombre   El nombre completo del usuario.
	     * @param telefono El número de teléfono del usuario.
	     * @param email    El correo electrónico del usuario.
	     */


    public void registrarUsuario(String dni, String nombre, String telefono, String email) {
    	Persona usuario = new Persona(nombre, telefono, dni, email);

    	usuarios.put(dni, usuario);
    }

	    /**
	     * 2) Crea una nueva cuenta de tipo regular para un usuario existente.
	     * Lanza error si el usuario no existe o el alias ya está registrado.
	     *
	     * @param dniUsuario El DNI del usuario titular de la cuenta.
	     * @param alias      El alias deseado para la cuenta.
	     * @return El CVU de la cuenta creada.
	     */

    public String crearCuentaRegular(String dniUsuario, String alias) {
	    	Usuario usuario = buscarUsuario(dniUsuario);
	    	CuentaRegular cta = new CuentaRegular(dniUsuario, alias);
	    	usuario.agregarCuenta(cta);
	    	return cta.cvu();
	    }

	    /**
	     * 2) Crea una nueva cuenta de tipo premium para un usuario existente.
	     * Lanza error si el usuario no existe o el alias ya está registrado.
	     *
	     * @param dniUsuario      El DNI del usuario titular de la cuenta.
	     * @param alias           El alias deseado para la cuenta.
	     * @param depositoInicial El monto inicial depositado en la cuenta (debe cumplir
	     *                        los requisitos mínimos).
	     * @return El CVU de la cuenta creada.
	     */
    public String crearCuentaPremium(String dniUsuario, String alias, double depositoInicial) {
	    	Usuario usuario = buscarUsuario(dniUsuario);
	    	CuentaPremium cta = new CuentaPremium(dniUsuario, alias, depositoInicial);
	    	usuario.agregarCuenta(cta);
	    	return cta.cvu();
	    }

	    /**
	     * 2) Crea una nueva cuenta de tipo corporativa vinculada a una empresa y a un
	     * usuario autorizado.
	     * Lanza error si el usuario no existe, el alias ya está registrado o la empresa
	     * no existe.
	     *
	     * @param dniUsuario  El DNI del usuario que será titular/administrador de la
	     *                    cuenta.
	     * @param alias       El alias deseado para la cuenta.
	     * @param cuitEmpresa El CUIT de la empresa a la que pertenece la cuenta.
	     * @return El CVU de la cuenta creada.
	     */
    public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
    	if (!empresas.containsKey(cuitEmpresa)) throw new RuntimeException("No existe la empresa.");
    	if (this.buscarCuentaPorAlias(alias) != null) throw new RuntimeException("Ya existe una cuenta con ese alias.");

    	Usuario usuario = buscarUsuario(dniUsuario);
    	CuentaCorporativa ctaCorp = new CuentaCorporativa(dniUsuario, alias, cuitEmpresa);
    	usuario.agregarCuenta(ctaCorp);
    	return ctaCorp.cvu();
    }

    /**
     * 3) Obtiene una lista con los identificadores (CVU o alias) de todas las
     * cuentas asociadas a un usuario.
     * Para cada cuenta en el listado se debe con el siguiente formato:
     * - "[Tipo]: [Alias] ([CVU])"
     *
     * Lanza error si el usuario no existe.
     *
     * @param dniUsuario El DNI del usuario.
     * @return Una lista de cadenas de texto representando las cuentas del usuario.
     */
    public List<String> obtenerCuentas(String dniUsuario) {
    	ArrayList<String> result = new ArrayList<String>();
    	Usuario usuario = buscarUsuario(dniUsuario);

    	for (Cuenta cuenta: usuario.cuentas()) {
			result.add(cuenta.tipo() + ": " + cuenta.alias() + " (" + cuenta.cvu() + ")");
    	}

    	return result;
    }

	    /**
	     * 4) Consulta el saldo disponible de una cuenta específica.
	     * Lanza error si la cuenta no existe.
	     *
	     * @param cvu El CVU de la cuenta a consultar.
	     * @return El monto correspondiente al saldo disponible.
	     */
    public double obtenerSaldoDisponible(String cvu) {
    	return buscarCuentaPorCvu(cvu).saldoDisponible();
    }

    /**
     * 5) Realiza una transferencia de dinero entre dos cuentas.
     * Lanza error si alguna de las cuentas no existe.
     *
     * @param cvuOrigen  El CVU de la cuenta origen desde la cual se debitará el
     *                   monto.
     * @param cvuDestino El CVU de la cuenta destino que recibirá el dinero.
     * @param monto      El importe a transferir.
     */

    public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
    	Cuenta cuentaOrigen = buscarCuentaPorCvu(cvuOrigen);
    	Cuenta cuentaDestino = buscarCuentaPorCvu(cvuDestino);

		cuentaOrigen.extraer(monto);
		cuentaDestino.depositar(monto);

		cuentaOrigen.agregarActividad(new ActividadTransferenciaSaliente(cuentaOrigen, cuentaDestino, monto, true));
		cuentaDestino.agregarActividad(new ActividadTransferenciaEntrante(cuentaOrigen, cuentaDestino, monto, true));
    }

    /**
     * 6) Genera una nueva inversión de renta fija desde una cuenta.
     * Lanza error si el usuario o la cuenta no existe, o si algun dato es inválido.
     *
     * @param dni       El DNI del usuario.
     * @param cvu       El CVU de la cuenta desde donde se invierte.
     * @param monto     El monto de dinero a invertir.
     * @param plazoDias El plazo en días de la inversión.
     * @return El identificador único de la inversión realizada.
     */
    public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {
    	Cuenta cuenta = buscarCuentaPorCvu(cvu);

    	cuenta.extraer(monto);

    	RentaFija inversion = new RentaFija(cuenta, monto, plazoDias, true);
    	cuenta.agregarActividad(inversion);

    	return inversion.id();
    }

    /**
     * 6) Genera una nueva inversión en divisas extranjeras desde una cuenta.
     * Lanza error si el usuario o la cuenta no existe, o si algun dato es inválido.
     *
     * @param dni       El DNI del usuario.
     * @param cvu       El CVU de la cuenta desde donde se invierte.
     * @param monto     El monto de dinero a invertir en moneda local.
     * @param plazoDias El plazo en días de la inversión.
     * @param divisa    El identificador de la divisa elegida.
     * @return El identificador único de la inversión realizada.
     */
    public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa, double tasa) {
    	Cuenta cuenta = buscarCuentaPorCvu(cvu);

    	cuenta.extraer(monto);

    	VinculadaADivisa inversion = new VinculadaADivisa(cuenta, monto, plazoDias, true, divisa, tasa);
    	cuenta.agregarActividad(inversion);

    	return inversion.id();
    }

    /**
     * 6) Genera una nueva inversión de liquidez (fondo común) desde una cuenta.
     * Lanza error si el usuario o la cuenta no existe, o si algun dato es inválido.
     *
     * @param dni       El DNI del usuario.
     * @param cvu       El CVU de la cuenta desde donde se invierte.
     * @param monto     El monto de dinero a invertir.
     * @param plazoDias El plazo estimado en días.
     * @return El identificador único de la inversión realizada.
     */
    public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {
    	Cuenta cuenta = buscarCuentaPorCvu(cvu);

    	if (!(cuenta instanceof CuentaCorporativa))
    		throw new IllegalArgumentException("La cuenta no es corporativa.");
    	CuentaCorporativa cuentaCorp = (CuentaCorporativa) cuenta;

    	Empresa empresa = empresas.get(cuentaCorp.cuit());

    	if (!empresa.dnisAutorizados().contains(dni))
    		throw new IllegalArgumentException("El usuario no está autorizado para operar esta cuenta corporativa.");

    	cuenta.extraer(monto);

    	FondoDeLiquidezEmpresarial inversion = new FondoDeLiquidezEmpresarial(cuenta, monto, plazoDias, true);
    	cuenta.agregarActividad(inversion);

    	return inversion.id();
    }

    /**
     * [Nuevo]
     * 13) Precancela una inversión activa de forma anticipada.
     * Lanza error si algun dato es inválido, la inversión no existe o no está
     * activa.
     *
     * @param dni         El DNI del usuario.
     * @param cvu         El CVU de la cuenta asociada a la inversión.
     * @param idInversion El identificador único de la inversión a cancelar.
     */
    public void precancelarInversion(String dni, String cvu, int idInversion) {
    	Cuenta cuenta = buscarCuentaPorCvu(cvu);
    	Inversion inversion = null;
    	for (Actividad actividad : cuenta.actividades()) {
    		if (actividad instanceof Inversion && ((Inversion) actividad).id() == idInversion) {
    			inversion = (Inversion) actividad;
    		}
    	}
    	if (inversion == null)
    		throw new IllegalArgumentException("Id de inversión inválido.");
    	if (!inversion.origen().cvu().equals(cvu) || !inversion.origen().dniPropietario().equals(dni))
    		throw new IllegalArgumentException("La inversión no le pertenece a la cuenta/usuario especificados.");

    	inversion.precancelar();
    	inversion.origen().depositar(inversion.monto());
    }

    /**
     * [Nuevo]
     * 14) Dado un alias consultar el CVU asociado.
     * Lanza error si el alias no está registrado.
     *
     * @param alias Alias para consultar el CVU.
     * @return cvu asociado al alias. Si el alias no está registrado debe lanzar una
     *         excepción.
     */
    public String consultarCvu(String alias) {
    	Cuenta cuenta = this.buscarCuentaPorAlias(alias);
    	if (cuenta == null) throw new IllegalArgumentException("No existe una cuenta con ese alias.");
    	return cuenta.cvu();
    }

    /**
     * 7) Obtiene el historial global de actividades del sistema.
     * Las actividades se deben mostrar con el siguiente formato:
     * - transferencia:
     * ```
     * origen: [dni] ([cvu])
     * destino: [dni] ([cvu])
     * monto: [monto]
     * [Aprovado/Rechazado]
     * ```
     * - inversion:
     * ```
     * origen: [dni] ([cvu])
     * desc: [tipo inversion]
     * monto: [monto]
     * plazo: [plazo]
     * [Aprovado/Rechazado]
     * ```
     *
     * @return Una lista con el detalle de las actividades globales.
     */
    public List<String> consultarHistorialGlobal(){
    	ArrayList<String> result = new ArrayList<String>();
    	for (Usuario usuario : usuarios.values()) {
    		for (Cuenta cuenta : usuario.cuentas()) {
    			for (Actividad actividad : cuenta.actividades()) {
    				result.add(actividad.toString());
    			}
    		}
    	}
    	return result;
    }

    /**
     * 8) Obtiene el historial de actividades asociado a una cuenta específica.
     * Con el mismo formato que en 7) historial global
     *
     * Lanza error si la cuenta no existe.
     *
     * @param cvu El CVU de la cuenta a consultar.
     * @return Una lista con los actividades realizados en la cuenta.
     */
    public List<String> consultarHistorialCuenta(String cvu){
    	Cuenta cuenta = buscarCuentaPorCvu(cvu);

    	ArrayList<String> result = new ArrayList<String>();
    	for (Actividad actividad : cuenta.actividades()) {
    		result.add(actividad.toString());
    	}
    	return result;
    }

    /**
     * 8) Obtiene el historial de actividades de un usuario a lo largo de todas
     * sus cuentas.
     * Con el mismo formato que en 7) historial global
     *
     * Lanza error si el usuario no existe.
     *
     * @param dniUsuario El DNI del usuario a consultar.
     * @return Una lista de los actividades realizados por el usuario.
     */
    public List<String> consultarHistorialUsuario(String dniUsuario){
    	Usuario usuario = buscarUsuario(dniUsuario);

    	ArrayList<String> result = new ArrayList<String>();
    	for (Cuenta cuenta : usuario.cuentas()) {
    		for (Actividad actividad : cuenta.actividades()) {
    			result.add(actividad.toString());
    		}
    	}
    	return result;
    }


    /**
     * 9) Calcula el monto total que un usuario tiene invertido considerando todas
     * sus cuentas.
     * Lanza error si el usuario no existe.
     *
     * @param dniUsuario El DNI del usuario a consultar.
     * @return El monto total invertido por el usuario.
     */
    public double obtenerTotalInvertido(String dniUsuario) {
    	double suma = 0;
    	Usuario usuario = buscarUsuario(dniUsuario);
    	for (Cuenta cuenta : usuario.cuentas()) {
    		for (Actividad actividad : cuenta.actividades()) {
    			if (actividad instanceof Inversion) {
    				Inversion inv = (Inversion) actividad;
    				if (!inv.fuePrecancelado()) {
    					suma += inv.monto();
    				}
    			}
    		}
    	}
    	return suma;
    }

    /**
     * 10) Obtiene las cuentas con la mayor cantidad de actividades registradas.
     * Se debe usar el mismo formato que el punto 3):
     * - "[Tipo]: [Alias] ([CVU])"
     *
     * Lanza error si cantidadTop no es positiva.
     *
     * @param cantidadTop El número de cuentas a retornar (Top N).
     * @return Una lista con el detalle de las cuentas con mayor volumen.
     */
    public List<String> cuentasConMayorVolumen(int cantidadTop){
    	ArrayList<Cuenta> cuentas = obtenerTodasLasCuentas();
    	ordenarPorCantidadDeActividades(cuentas);

    	ArrayList<String> result = new ArrayList<String>();
    	for (int posicion = 0; posicion < cantidadTop && posicion < cuentas.size(); posicion++) {
    		Cuenta cuenta = cuentas.get(posicion);
    		result.add(cuenta.tipo() + ": " + cuenta.alias() + " (" + cuenta.cvu() + ") " + cuenta.actividades().size());
    	}
    	return result;
    }
    
    /* MÉTODOS PRIVADOS **********************************************/

    private ArrayList<Cuenta> obtenerTodasLasCuentas() {
    	ArrayList<Cuenta> todas = new ArrayList<Cuenta>();
    	for (Usuario usuario : usuarios.values()) {
    		for (Cuenta cuenta : usuario.cuentas()) {
    			todas.add(cuenta);
    		}
    	}
    	return todas;
    }

    private void ordenarPorCantidadDeActividades(ArrayList<Cuenta> cuentas) {
    	int cantidad = cuentas.size();
    	for (int pasada = 0; pasada < cantidad - 1; pasada++) {
    	    for (int posicion = 0; posicion < cantidad - pasada - 1; posicion++) {
    	        Cuenta actual = cuentas.get(posicion);
    	        Cuenta siguiente = cuentas.get(posicion + 1);

    	        if (actual.actividades().size() < siguiente.actividades().size()) {
    	            cuentas.set(posicion, siguiente);
    	            cuentas.set(posicion + 1, actual);
    	        }
    	    }
    	}
    }

    /**
     * [Bonus Track]
     * 15) Procesa todas las inversiones que vencen el dia de hoy
     * y actualiza los saldos agregando los intereses generados segun el tipo de
     * inversion.
     * Sea por taza fija o por cotización de activos más tasa.
     *
     * El dia actual y las cotizaciones de los activos se deben consultar a
     * Utilitarios.
     *
     */
    // void procesarInversionesQueVencenHoy();
    private Usuario buscarUsuario(String dniUsuario) {
    	if (!usuarios.containsKey(dniUsuario)) throw new RuntimeException("El usuario no existe.");
    	return usuarios.get(dniUsuario);
    }

    private Cuenta buscarCuentaPorCvu(String cvu) {
    	for (Usuario usuario : usuarios.values()) {
    		Cuenta cuenta = usuario.buscarCuenta(cvu);
    		if (cuenta != null) {
    			return cuenta;
    		}
    	}
    	throw new RuntimeException("No existe la cuenta");
    }

    private Cuenta buscarCuentaPorAlias(String alias) {
    	for (Usuario usuario : usuarios.values()) {
    		for (Cuenta cuenta : usuario.cuentas()) {
    			if (cuenta.alias().equals(alias)) {
    				return cuenta;
    			}
    		}
    	}
    	return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("=== Usuarios ===\n");
        for (Usuario u : usuarios.values())
        	res.append("  ").append(u).append("\n");

        res.append("=== Empresas ===\n");
        for (Empresa e : empresas.values())
        	res.append("  ").append(e).append("\n");

        res.append("=== Cuentas ===\n");
        for (Usuario u : usuarios.values())
        	for (Cuenta c : u.cuentas())
        		res.append("  ").append(c).append("\n");

        res.append("=== Actividades ===\n");
        for (Usuario u : usuarios.values())
        	for (Cuenta c : u.cuentas())
        		for (Actividad a : c.actividades())
        			res.append("  ").append(a).append("\n");

        return res.toString();
    }

}
