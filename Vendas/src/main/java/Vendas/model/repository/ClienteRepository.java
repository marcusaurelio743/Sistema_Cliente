package Vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Vendas.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
