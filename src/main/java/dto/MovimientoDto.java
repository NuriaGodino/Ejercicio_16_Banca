package dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Cuenta;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MovimientoDto {
	private int idMovimiento;
	private CuentaDto idCuenta;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	private int cantidad;
	private String operacion;
}
