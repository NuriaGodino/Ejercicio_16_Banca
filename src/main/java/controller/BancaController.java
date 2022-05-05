package controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
			return "Login";
		}
		
		return "menu";
	}
	
	@GetMapping(value = "TodosClientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ClienteDto> todosClientes(){
		return service.listaClientes();
	}
	
	@PostMapping(value = "Ingreso")
	public String ingresar(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("cantidad") int cantidad) {
		if(service.ingreso(numeroCuenta, cantidad)) {
			return "menu";
		}
		return "Login";
	}
	
	@PostMapping(value = "Extraccion")
	public String extraer(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("cantidad") int cantidad) {
		if(service.extraccion(numeroCuenta, cantidad)) {
			return "menu";
		}
		
		return "Login";
	}
	
	@PostMapping(value = "Transferencia")
	public String transferencia(@RequestParam("origen") int a, @RequestParam("destino") int desde, @RequestParam("cantidad") int cantidad) {
		if(service.transferencia(a, desde, cantidad)) {
			return "menu";
		}
		
		return "Login";
	}
	
	@GetMapping(value = "MovimientoEntreFechas", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> movimientosEntreFechas(@RequestParam("numeroCuenta") int numeroCuenta, @RequestParam("f1") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date f1, 
			@RequestParam("f2") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date f2){
		return service.movimientosEntreFecha(numeroCuenta, f1, f2);
	}
	
}
