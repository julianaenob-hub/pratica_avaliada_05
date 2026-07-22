package com.generation.infostore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo Produto é obrigatório!")
	@Size(min = 2, max = 100, message = "O atributo Produto deve conter entre 2 e 100 caracteres!")
	private String produto;

	@NotBlank(message = "O atributo Descrição é obrigatória!")
	@Size(min = 5, max = 500, message = "O atributo Descrição deve conter entre 5 e 500 caracteres!")
	private String descricao;

	@NotBlank(message = "O atributo Marca é obrigatória!")
	@Size(min = 2, max = 50, message = "A Marca deve conter entre 2 e 50 caracteres!")
	private String marca;

	@NotNull(message = "O atributo Quantidade é obrigatório!")
	@Positive(message = "A Quantidade deve ser maior que zero!")
	private Integer quantidade;

	@NotNull(message = "O atributo Valor é obrigatório!")
	@Positive(message = "O atributo Valor deve ser maior que zero!")
	private BigDecimal valor;

	@UpdateTimestamp
	private LocalDateTime data;

	@ManyToOne
	@JsonIgnoreProperties("pedido")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}