package service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converters.ConversorEntityToDto;
import dao.ClienteDao;
import dao.CuentaDao;
import dao.MovimientoDao;
import dto.ClienteDto;
import dto.CuentaDto;
import dto.MovimientoDto;
import model.Cuenta;
import model.Movimiento;

@Service
public class ServiceBancaImpl implements ServiceBanca {

	@Autowired
	ConversorEntityToDto conversor;
	
	ClienteDao clientesDao;
	CuentaDao cuentasDao;
	MovimientoDao movimientoDao;
	
	public ServiceBancaImpl(@Autowired ClienteDao clientesDao, @Autowired CuentaDao cuentasDao, @Autowired MovimientoDao movimientoDao) {
		this.clientesDao = clientesDao;
		this.cuentasDao = cuentasDao;
		this.movimientoDao = movimientoDao;
	}

	@Override
	public CuentaDto validarCuenta(int numeroCuenta) {
		Optional<Cuenta> c = cuentasDao.findById(numeroCuenta);
		if(c.isPresent()) {
			return conversor.cuentaToDto(c.get());
		}
		return null;
	}

	@Override
	public List<ClienteDto> listaClientes() {
		return clientesDao.findAll().stream().map(x -> conversor.clienteToDto(x)).collect(Collectors.toList());
	}
	
	@Override
	public boolean ingreso(int numeroCuenta, int cantidad) {
		Optional<Cuenta> c = cuentasDao.findById(numeroCuenta);
		if(c.isPresent()) {
			Movimiento m = new Movimiento(numeroCuenta, new Date(), cantidad, "ingreso");
			movimientoDao.save(m);
			c.get().setSaldo(c.get().getSaldo() + cantidad);
			cuentasDao.save(c.get());
			return true;
		}
		return false;
	}

	@Override
	public boolean extraccion(int numeroCuenta, int cantidad) {
		Optional<Cuenta> c = cuentasDao.findById(numeroCuenta);
		if(c.isPresent()) {
			Movimiento m = new Movimiento(numeroCuenta, new Date(), cantidad, "extraccion");
			movimientoDao.save(m);
			c.get().setSaldo(c.get().getSaldo() - cantidad);
			cuentasDao.save(c.get());
			return true;
		}
		return false;
	}

	@Override
	public List<MovimientoDto> movimientosEntreFecha(int numeroCuenta, Date f1, Date f2) {
		List<MovimientoDto> m = movimientoDao.findByMovimientoFechas(numeroCuenta, f1, f2).stream().map(x -> conversor.movimientoToDto(x)).collect(Collectors.toList());
		return m;
	}

	@Override
	public boolean transferencia(int a, int desde, int cantidad) {
		Optional<Cuenta> origen = cuentasDao.findById(a);
		Optional<Cuenta> destino = cuentasDao.findById(desde);
		
		if(origen.isPresent() && destino.isPresent()) {
			Movimiento m = new Movimiento(a, new Date(), cantidad, "transferencia");
			movimientoDao.save(m);
			origen.get().setSaldo(origen.get().getSaldo() - cantidad);
			destino.get().setSaldo(destino.get().getSaldo() + cantidad);
			cuentasDao.save(origen.get());
			cuentasDao.save(destino.get());
			return true;
		}
		return false;
	}
}
