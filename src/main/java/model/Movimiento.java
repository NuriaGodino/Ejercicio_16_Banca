package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "movimientos")
public class Movimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovimiento;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private int cantidad;
	private String operacion;
	
	@ManyToOne()
	@JoinColumn(name = "numeroCuenta", referencedColumnName = "numeroCuenta")
	Cuenta cuenta;

	public Movimiento(int idMovimiento, Date fecha, int cantidad, String operacion) {
		super();
		this.idMovimiento = idMovimiento;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.operacion = operacion;
	}
}
