package br.com.alelofrota.api.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig {
	
	// http://localhost:8080/swagger-ui.html#/

		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(new ApiInfo("alelofrota", "APÌ Fullstack Test - Alelo Frota 2020", "1.0",
							"Terms of Service: Queiroz Tecnologia Ltda",
							new Contact("Danilo Queiroz", "https://www.linkedin.com/in/danilo-queiroz-6233a861",
									"daniloccuft@gmail.com"),
							"Apache License Version 2.0", "http://www.apache.org/licenses/LICENSE-2.0"))
					.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
		}

}
