package br.com.itau.seguros.desafio.dataprovider.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.dataprovider.model.enums.TipoToggle;

@SpringBootTest
public class FeatureToggleRepositoryTest {

	@Autowired
	private FeatureToggleRepository featureToggleRepository;

	@Test
	public void saveToggleTest() {
		FeatureToggle createdFeatureToggle = featureToggleRepository.save(mockFeatureToggle());

		assertThat(createdFeatureToggle.getNome()).isEqualTo("nome-do-feature-toggle");
	}

	@Test
	public void saveValueTest() {

		FeatureToggle createdFeatureToggle = featureToggleRepository.save(mockFeatureValue());

		assertThat(createdFeatureToggle.getNome()).isEqualTo("nome-do-feature-value");
	}

	@Test
	public void getByNameTest() {
		FeatureToggle createdFeatureToggle = featureToggleRepository.save(mockFeatureValue());

		FeatureToggle featureToggle = featureToggleRepository.getByName(createdFeatureToggle.getNome()).get();

		assertThat(featureToggle.getNome()).isEqualTo("nome-do-feature-value");
		assertThat(featureToggle.getTipo()).isEqualTo(TipoToggle.VALUE);
		assertThat(featureToggle.getValor()).isEqualTo(BigDecimal.valueOf(215.63));
		assertThat(featureToggle.isLigado()).isEqualTo(true);
	}

	@Test
	public void getByNameNullTest() {
		Optional<FeatureToggle> resultado = featureToggleRepository.getByName("cenario-erro");
		assertThat(resultado.isEmpty());
	}

	@Test
	public void deleteTest() {
		FeatureToggle createdFeatureToggle = featureToggleRepository.save(mockFeatureValue());
		featureToggleRepository.deleteByName(createdFeatureToggle.getNome());
		Optional<FeatureToggle> resultado = featureToggleRepository.getByName(createdFeatureToggle.getNome());
		assertThat(resultado.isEmpty());
	}

	private FeatureToggle mockFeatureToggle() {
		return new FeatureToggle("nome-do-feature-toggle", TipoToggle.TOGGLE, null, true);

	}

	private FeatureToggle mockFeatureValue() {
		return new FeatureToggle("nome-do-feature-value", TipoToggle.VALUE, BigDecimal.valueOf(215.63), true);

	}

}
