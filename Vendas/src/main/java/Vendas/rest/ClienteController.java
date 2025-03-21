package Vendas.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Vendas.model.entity.Cliente;
import Vendas.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@Valid @RequestBody Cliente cliente){
		return repository.save(cliente);
		
	}
	
	@GetMapping("{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		return repository
				.findById(id)
					.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente não encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		 repository
				.findById(id)
				.map(cliente->{
					repository.delete(cliente);
					return Void.TYPE;
				})
					.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"cliente não encontrado"));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long id, @Valid @RequestBody Cliente clienteAtualizado) {
		repository.findById(id)
			.map(cliente ->{
				clienteAtualizado.setId(cliente.getId());
				clienteAtualizado.setDataCadastro(cliente.getDataCadastro());
				repository.save(clienteAtualizado);
				return Void.TYPE;
			})
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
	}

}
