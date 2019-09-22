package com.example.HelloMoto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {
	@Autowired
	private CarroRepository rep;
	
	public Iterable<Carro> getCarros(){
		return rep.findAll();
	}
	
	public List<Carro> getCarrosFake(){
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro("Fusca",1L));
		carros.add(new Carro("Brasilia",2L));

		return carros;
	}

	public Optional<Carro> getCarrosById(Long id) {
		return rep.findById(id);
	}

	public List<Carro> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo);
	}

	public Carro save(Carro carro) {
		return rep.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id,"não deu");
		Optional<Carro> optional =  getCarrosById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println( "Carro id: " + db.getId());
			
			rep.save(db);
			
			return db;
		}else {
			throw new RuntimeException("Nao foi possivel att");
		}
		
		
	}

	public void delete(Long id) {
		Optional<Carro> carro = getCarrosById(id);
//		if(carro.isPresent()) {
//			rep.deleteById(id);
//		}else {
//			throw new RuntimeException("não tem esse carro");
//		}
		rep.deleteById(id);
	}

	
	
}
