package Vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Vendas.model.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
