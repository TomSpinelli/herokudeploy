package com.example.HelloMoto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.HelloMoto.domain.Carro;
import com.example.HelloMoto.domain.CarroService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloMotoApplicationServiceTests {

	@Autowired CarroService service;
	@Test
	public void contextLoads() {
	}

	@Test
	public void testSave() {
		Carro c = new Carro();//("fusquinha", "de pobre", 5658L);
		c.setTipo("de pobre");
		c.setNome("fusquinha");
		c.setId(5658L);
				c =service.save(c);
		
		assertNotNull(c);
		Long id = c.getId();
		assertNotNull(id);
		
		Optional<Carro> oc = service.getCarrosById(id);
		assertTrue(oc.isPresent());
		
		c = oc.get();
		
		assertEquals("fusquinha", c.getNome());
		assertEquals("de pobre", c.getTipo());
		
		service.delete(id);
		assertFalse(service.getCarrosById(id).isPresent());
				
	}
	
	@Test
	public void testLista() {
		List<Carro> carros = (List<Carro>) service.getCarros();
		assertTrue(carros.size()<100);
		
	}
	
	
}
