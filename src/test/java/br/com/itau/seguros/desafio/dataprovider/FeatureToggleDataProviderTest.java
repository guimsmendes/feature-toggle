package br.com.itau.seguros.desafio.dataprovider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.itau.seguros.desafio.dataprovider.exception.NotFoundException;
import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.dataprovider.model.enums.TipoToggle;
import br.com.itau.seguros.desafio.dataprovider.repository.FeatureToggleRepository;

@SpringBootTest
class FeatureToggleDataProviderTest {

	@InjectMocks
	private FeatureToggleDataprovider featureToggleDataProvider;

	@Mock
	private FeatureToggleRepository featureToggleRepository;

	@Test
	void saveTest() {
		when(featureToggleRepository.save(any(FeatureToggle.class))).thenReturn(mockFeatureToggle());

		FeatureToggle createdFeatureToggle = featureToggleDataProvider.save(mockFeatureToggle());

		assertThat(createdFeatureToggle.getNome()).isEqualTo("nome-do-feature-toggle");
		assertThat(createdFeatureToggle.getTipo()).isEqualTo(TipoToggle.VALUE);
		assertThat(createdFeatureToggle.getValor()).isEqualTo(BigDecimal.valueOf(215.63));
		assertThat(createdFeatureToggle.isLigado()).isTrue();
	}

	@Test
	void getByNameTest() {
		when(featureToggleRepository.getByName(anyString())).thenReturn(Optional.of(mockFeatureToggle()));
		
		FeatureToggle featureToggle = featureToggleDataProvider.getByName("nome-do-feature-toggle");
		
		assertThat(featureToggle.getNome()).isEqualTo("nome-do-feature-toggle");
		assertThat(featureToggle.getTipo()).isEqualTo(TipoToggle.VALUE);
		assertThat(featureToggle.getValor()).isEqualTo(BigDecimal.valueOf(215.63));
		assertThat(featureToggle.isLigado()).isTrue();

	}

	@Test
	void getByNameExceptionTest() {
		when(featureToggleRepository.getByName(anyString())).thenReturn(Optional.empty());

		Assertions.assertThrows(NotFoundException.class,
				() -> featureToggleDataProvider.getByName("cenario-exception"));

	}

	private FeatureToggle mockFeatureToggle() {
		return new FeatureToggle("nome-do-feature-toggle", TipoToggle.VALUE, BigDecimal.valueOf(215.63), true);

	}
}
