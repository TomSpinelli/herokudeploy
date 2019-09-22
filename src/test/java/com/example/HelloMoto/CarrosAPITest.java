package com.example.HelloMoto;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.HelloMoto.domain.Carro;
import com.example.HelloMoto.domain.CarroService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloMotoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {
    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    private CarroService service;

    private ResponseEntity<Carro> getCarro(String url) {
        return
                rest.withBasicAuth("user","123").getForEntity(url, Carro.class);
    }

    private ResponseEntity<List<Carro>> getCarros(String url) {
        return rest.withBasicAuth("user","123").exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Carro>>() {
                });
    }


    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        // Insert
        ResponseEntity response = rest.withBasicAuth("admin","123").postForEntity("/api/v1/carros", carro, null);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        List<Carro> c = (List<Carro>) getCarro(location).getBody();

        assertNotNull(c);
        assertEquals("Porshe", ((Carro) c).getNome());
        assertEquals("esportivos", ((Carro) c).getTipo());

        // Deletar o objeto
        rest.withBasicAuth("user","123").delete(location);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

    @Test
    public void testLista() {
        List<Carro> carros = getCarros("/api/v1/carros").getBody();
        assertNotNull(carros);
        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/luxo").getBody().size());

        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {

        ResponseEntity<Carro> response = getCarro("/api/v1/carros/11");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Carro c = response.getBody();
        assertEquals("Ferrari FF", c.getNome());
    }

    @Test
    public void testGetNotFound() {

        ResponseEntity response = getCarro("/api/v1/carros/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}