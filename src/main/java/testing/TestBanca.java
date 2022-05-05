package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.ServiceBanca;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class TestBanca {
	@Autowired
	ServiceBanca service;
	
	@Test
	void validarCuenta() {
		assertEquals(1000, service.validarCuenta(1000).getNumeroCuenta());
	}
	
	@Test
	void ingreso() {
		assertEquals(true, service.ingreso(1000, 10));
	}
	
	@Test
	void retirada() {
		assertEquals(true, service.extraccion(1000, 10));
	}
	
	@Test
	void transferencia() {
		assertEquals(true, service.transferencia(1000, 2000, 50));
	}
	
	@Test
	void movimientosEntreFechas() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date f1 = format.parse("2019-03-30");
		Date f2 = format.parse("2019-04-01");
		assertEquals(25, service.movimientosEntreFecha(1000, f1, f2).get(0).getIdCuenta());
	}

}
