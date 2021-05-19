package br.com.alelofrota.domain.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Data
public class Problema {

	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	private List<Campo> campos;

	public static class Campo {

		@Getter 
		@Setter
		private String nome;
		@Getter 
		@Setter
		private String mensagem;

		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}

	}

}
