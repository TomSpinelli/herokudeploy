package com.example.HelloMoto.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class indexController {

	@GetMapping
	//mesma coisa de @RequestMapping(method = RequestMethod.POST/GET/PUT)
	public String get() {
		return "API DOS CARROS" ;
	}
	
	@PostMapping
	public String post() {
		return "post string BOOT" ;
	}
	@PutMapping
	public String put() {
		return "put string BOOT" ;
	}
	
	@DeleteMapping
	public String delete() {
		return "delete string BOOT" ;
	}
	
	//==========================================
	
	@PostMapping("/login")
	public String login(@RequestParam("login") String pLogin,
							@RequestParam("senha") String pSenha) {
		return "Login: " + pLogin + " Senha: " +pSenha;
		
		//http://localhost:8080/login
	}
	
	@GetMapping("/login/{login}/senha/{senha}")
	public String loginPath(@PathVariable("login") String pLogin,
							@PathVariable("senha") String pSenha) {
		return "Login: " + pLogin + " Senha: " +pSenha;
		
		//http://localhost:8080/login/TOMAS/senha/SENHA
	}
	
	@GetMapping("/carros/id/{id}")
	public String getCarroById(@PathVariable("id") Long id) {
		return "Carro" + id;
	}

	@GetMapping("/carros/tipo/{tipo}")
	public String getCarroByTipo(@PathVariable("tipo") Long tipo) {
		return "Carro " + tipo;
	}	
	
	
	@GetMapping("/teste")
	public String test() {
		return "Hello string TEST" ;
	}
}
