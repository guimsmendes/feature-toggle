package br.com.itau.seguros.desafio.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.dataprovider.model.enums.TipoToggle;
import br.com.itau.seguros.desafio.usecase.gateway.FeatureToggleGateway;

@SpringBootTest
class FeatureToggleUseCaseTest {

	@InjectMocks
	private FeatureToggleUseCase featureToggleUseCase;

	@Mock
	private FeatureToggleGateway featureToggleGateway;

	@Test
	void registrarFeatureToggleTest() {
		when(featureToggleGateway.save(any(FeatureToggle.class))).thenReturn(mockFeatureValue());

		FeatureToggle createdFeatureToggle = featureToggleUseCase.registrarFeatureToggle(mockFeatureValue());

		assertThat(createdFeatureToggle.getNome()).isEqualTo("nome-do-feature-value");
		assertThat(createdFeatureToggle.getTipo()).isEqualTo(TipoToggle.VALUE);
		assertThat(createdFeatureToggle.getValor()).isEqualTo(BigDecimal.valueOf(215.63));
		assertThat(createdFeatureToggle.isLigado()).isTrue();

	}

	@Test
	void buscarFeatureToggleAtivoToggleTest() {
		when(featureToggleGateway.getByName(anyString())).thenReturn(mockFeatureToggle());
		String resultado = featureToggleUseCase.buscarFeatureToggle("nome-do-feature-toggle", null);

		assertThat(resultado).isEqualTo("ATIVO");

	}

	@Test
	void buscarFeatureToggleAtivoValueTest() {
		when(featureToggleGateway.getByName(anyString())).thenReturn(mockFeatureValue());
		String resultado = featureToggleUseCase.buscarFeatureToggle("nome-do-feature-value", BigDecimal.valueOf(250));

		assertThat(resultado).isEqualTo("ATIVO");

	}

	@Test
	void buscarFeatureToggleInativoPeloValorTest() {
		when(featureToggleGateway.getByName(anyString())).thenReturn(mockFeatureValue());
		String resultado = featureToggleUseCase.buscarFeatureToggle("nome-do-feature-value", BigDecimal.valueOf(210));

		assertThat(resultado).isEqualTo("INATIVO");

	}

	@Test
	void buscarFeatureToggleInativoPorqueEstaDesligadoTest() {
		when(featureToggleGateway.getByName(anyString())).thenReturn(mockFeatureToggleDesligado());
		String resultado = featureToggleUseCase.buscarFeatureToggle("nome-do-feature-toggle", null);

		assertThat(resultado).isEqualTo("INATIVO");

	}

	@Test
	void buscarFeatureToggleNuloTest() {
		when(featureToggleGateway.getByName(anyString())).thenReturn(mockFeatureToggleNulo());
		String resultado = featureToggleUseCase.buscarFeatureToggle(null, null);

		assertThat(resultado).isEqualTo("INATIVO");

	}

	private FeatureToggle mockFeatureToggle() {
		return new FeatureToggle("nome-do-feature-toggle", TipoToggle.TOGGLE, null, true);

	}

	private FeatureToggle mockFeatureValue() {
		return new FeatureToggle("nome-do-feature-value", TipoToggle.VALUE, BigDecimal.valueOf(215.63), true);

	}

	private FeatureToggle mockFeatureToggleDesligado() {
		return new FeatureToggle("nome-do-feature-toggle", TipoToggle.TOGGLE, null, false);

	}

	private FeatureToggle mockFeatureToggleNulo() {
		return new FeatureToggle(null, null, null, true);

	}

}
