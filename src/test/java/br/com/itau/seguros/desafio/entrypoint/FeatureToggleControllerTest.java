package br.com.itau.seguros.desafio.entrypoint;

import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.dataprovider.model.enums.TipoToggle;
import br.com.itau.seguros.desafio.entrypoint.model.FeatureToggleRequest;
import br.com.itau.seguros.desafio.usecase.FeatureToggleUseCase;

@SpringBootTest
class FeatureToggleControllerTest {

	@Mock
	private FeatureToggleUseCase featureToggleUseCase;

	@InjectMocks
	private FeatureToggleController featureToggleController;

	@Test
	void registrarToggle() {
		when(featureToggleUseCase.registrarFeatureToggle(any(FeatureToggle.class))).thenReturn(mockFeatureToggle());

		ResponseEntity<?> result = featureToggleController.registrarFeatureFlag(mockFeatureToggleRequest());
		assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.OK).build());
	}

	@Test
	void registrarToggleInvalido() {
		when(featureToggleUseCase.registrarFeatureToggle(any(FeatureToggle.class))).thenReturn(mockFeatureToggle());

		ResponseEntity<?> result = featureToggleController.registrarFeatureFlag(mockFeatureToggleRequestInvalido());
		assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@Test
	void verificarToggleAtivo() {
		when(featureToggleUseCase.buscarFeatureToggle(anyString(), any(BigDecimal.class))).thenReturn("ATIVO");

		ResponseEntity<?> result = featureToggleController.verificarFeatureFlag("teste-verificar-toggle-ativo",
				"200");
		assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.OK).build());
	}

	@Test
	void verificarToggleInativo() {
		when(featureToggleUseCase.buscarFeatureToggle(anyString(), any(BigDecimal.class))).thenReturn("INATIVO");

		ResponseEntity<?> result = featureToggleController.verificarFeatureFlag("teste-verificar-toggle-ativo", null);
		assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@Test
	void deleteToggle() {
		ResponseEntity<?> result = featureToggleController.apagarFeatureFlag("teste-deletar-toggle");
		assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.OK).build());
	}

	private FeatureToggleRequest mockFeatureToggleRequest() {
		return new FeatureToggleRequest("teste-feature-toggle", "value", BigDecimal.valueOf(120), true);
	}

	private FeatureToggleRequest mockFeatureToggleRequestInvalido() {
		return new FeatureToggleRequest("teste-feature-toggle", "value", null, true);
	}

	private FeatureToggle mockFeatureToggle() {
		return new FeatureToggle("teste-feature-toggle", TipoToggle.VALUE, BigDecimal.valueOf(120), true);
	}
}
