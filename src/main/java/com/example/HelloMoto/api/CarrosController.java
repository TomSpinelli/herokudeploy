package com.example.HelloMoto.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HelloMoto.domain.Carro;
import com.example.HelloMoto.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	@Autowired //Dependency Injection
	private CarroService service;
	
	@GetMapping()
	public ResponseEntity<Iterable<Carro>> get() {
		return new ResponseEntity<>(service.getCarros(),
												HttpStatus.INTERNAL_SERVER_ERROR)
				;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<Carro> carro = service.getCarrosById(id);
				
		return carro.isPresent()?
					ResponseEntity.ok(carro):
					ResponseEntity.notFound().build();	
	}								//sem argumentos => .build
	
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity  get(@PathVariable("tipo") String tipo) {
		List<Carro> carros = service.getCarrosByTipo(tipo);
		
		return carros.isEmpty()?
					ResponseEntity.ok(carros):	
					ResponseEntity.noContent().build();
			
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = service.save(carro);
		
		return "Carro salvo com sucesso: " + c.getId();
		}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro,id);
		 return "Carro atualizado com sucesso " + c.getId(); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long  id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
