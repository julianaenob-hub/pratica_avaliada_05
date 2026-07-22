package com.generation.infostore.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.infostore.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	List<Pedido> findAllByProdutoContainingIgnoreCase(String produto);

	// Consultas extras
	List<Pedido> findAllByMarcaContainingIgnoreCase(String marca);

	List<Pedido> findAllByDescricaoContainingIgnoreCase(String descricao);

	List<Pedido> findAllByValorGreaterThan(BigDecimal valor);

	List<Pedido> findAllByValorLessThan(BigDecimal valor);

}