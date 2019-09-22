package com.example.HelloMoto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Carro {

	@Column(name = "nome")
	private String nome;
	private String tipo;
	private String descricao;
	private String urlFoto;
	private String urlVideo;
	private String latitude;
	private String longitude;
	
	
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Carro() {
		
	}
	
	public Carro(String nome, Long id) {
		super();
		this.nome = nome;
		this.id = id;
	}
	

}
