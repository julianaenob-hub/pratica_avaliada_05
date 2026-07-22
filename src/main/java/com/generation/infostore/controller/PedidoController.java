package com.generation.infostore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.infostore.model.Pedido;
import com.generation.infostore.repository.ClienteRepository;
import com.generation.infostore.repository.PedidoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {
		return ResponseEntity.ok(pedidoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable Long id) {
		return pedidoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/produto/{produto}")
	public ResponseEntity<List<Pedido>> getByProduto(@PathVariable String produto) {
		return ResponseEntity.ok(pedidoRepository.findAllByProdutoContainingIgnoreCase(produto));
	}

	// Consultas extras

	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Pedido>> getByMarca(@PathVariable String marca) {
		return ResponseEntity.ok(pedidoRepository.findAllByMarcaContainingIgnoreCase(marca));
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Pedido>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(pedidoRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@PostMapping
	public ResponseEntity<Pedido> post(@Valid @RequestBody Pedido pedido) {

		if (clienteRepository.existsById(pedido.getCliente().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(pedidoRepository.save(pedido));
		}

		return ResponseEntity.badRequest().build();
	}

	@PutMapping
	public ResponseEntity<Pedido> put(@Valid @RequestBody Pedido pedido) {

		if (pedidoRepository.existsById(pedido.getId())
				&& clienteRepository.existsById(pedido.getCliente().getId())) {

			return ResponseEntity.ok(pedidoRepository.save(pedido));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (pedido.isEmpty())
			return ResponseEntity.notFound().build();

		pedidoRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}