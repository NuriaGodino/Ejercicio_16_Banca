package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cuentas")
public class Cuenta {
	@Id
	private int numeroCuenta;
	private int saldo;
	private String tipoCuenta;
	
	@OneToMany(mappedBy = "cuenta")
	private List<Movimiento> movimientos;
	
//	@OneToMany(mappedBy = "idCuenta")
//	private List<Titulares> titulares;

	public Cuenta(int numeroCuenta, int saldo, String tipoCuenta) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.tipoCuenta = tipoCuenta;
	}
	
	
}
