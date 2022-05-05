package service;

import java.util.Date;
import java.util.List;

import dto.ClienteDto;
import dto.CuentaDto;
import dto.MovimientoDto;

public interface ServiceBanca {
	CuentaDto validarCuenta(int numeroCuenta);
	boolean ingreso(int numeroCuenta, int cantidad);
	boolean extraccion(int numeroCuenta, int cantidad);
	List<MovimientoDto> movimientosEntreFecha(int numeroCuenta, Date f1, Date f2);
	List<ClienteDto> listaClientes();
	boolean transferencia(int a, int desde, int cantidad);
}
