package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Integer>{
	
}
