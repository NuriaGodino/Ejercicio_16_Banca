package service;

import java.util.Date;
import java.util.List;

import dto.ClienteDto;
import dto.CuentaDto;
import dto.MovimientoDto;

public interface ServiceBanca {
	CuentaDto validarCuenta(int numeroCuenta);
	void ingreso(MovimientoDto movimiento);
	void extraccion(MovimientoDto movimiento);
	List<MovimientoDto> movimientosEntreFecha(Date f1, Date f2);
	List<ClienteDto> listaClientes();
}
