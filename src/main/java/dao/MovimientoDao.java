package dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Movimiento;

public interface MovimientoDao extends JpaRepository<Movimiento, Integer>{
	@Query("select m from Movimiento m where m.cuenta.numeroCuenta=?1 and m.fecha between ?2 and ?3")
	List<Movimiento> findByMovimientoFechas(int numeroCuenta, Date f1, Date f2);
}
