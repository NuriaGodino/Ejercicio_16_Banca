package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.ClienteDto;
import dto.MovimientoDto;
import service.ServiceBanca;

@CrossOrigin("*")
@Controller
public class BancaController {

	@Autowired
	ServiceBanca service;
	
	@GetMapping(value = "Login")
	public String login(@RequestParam("numeroCuenta") int numeroCuenta) {
		if(service.validarCuenta(numeroCuenta) == null) {
			return "error";
		}
		
		return "menu";
	}
	
	@GetMapping(value = "MovimientosFecha")
	public @ResponseBody List<MovimientoDto> buscarMovimientos(@RequestParam("f1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date f1, @RequestParam("f2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date f2){
		return service.movimientosEntreFecha(f1, f2);
	}
	
	@GetMapping(value = "TodosClientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ClienteDto> todosClientes(){
		return service.listaClientes();
	}
	
	
}
