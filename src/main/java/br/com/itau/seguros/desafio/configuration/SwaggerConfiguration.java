package br.com.itau.seguros.desafio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket produceApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.itau.seguros.desafio.entrypoint")).build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Feature Toggle").description(
				"Esta API têm como objeto implantar um recurso de feature toggle que provém de uma forma dinâmica alternar entre diferentes caminhos o código fonte de forma que funcionalidades possam ser testadas e completadas antes de serem lançadas em produção")
				.contact(new Contact("Guilherme Mendes", "https://gitlab.com/guimsmendes", "guimsmendes@gmail.com"))
				.build();
	}

}