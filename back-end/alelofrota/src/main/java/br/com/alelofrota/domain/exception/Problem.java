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
public class Problem {

	private Integer status;
	private LocalDateTime dateTime;
	private String title;
	private List<Field> fields;

	public static class Field {

		@Getter 
		@Setter
		private String name;
		@Getter 
		@Setter
		private String mensage;

		public Field(String name, String mensage) {
			super();
			this.name = name;
			this.mensage = mensage;
		}

	}

}
