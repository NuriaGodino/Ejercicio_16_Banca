package model;

import javax.persistence.Entity;
import javax.persistence.Id;

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
@Table(name = "clientes")
public class Cliente {
	@Id
	private int dni;
	private String nombre;
	private String direccion;
	private int telefono;

//	@OneToMany(mappedBy = "idCliente")
//	private List<Titulares> titulares;

	
	
}
