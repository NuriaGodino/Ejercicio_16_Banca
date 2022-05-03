package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cuenta;

public interface CuentaDao extends JpaRepository<Cuenta, Integer>{
	Cuenta findBynumeroCuenta(int numeroCuenta);
}
