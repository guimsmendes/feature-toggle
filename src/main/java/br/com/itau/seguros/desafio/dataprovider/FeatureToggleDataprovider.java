package br.com.itau.seguros.desafio.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

import br.com.itau.seguros.desafio.dataprovider.exception.NotFoundException;
import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.dataprovider.repository.FeatureToggleRepository;
import br.com.itau.seguros.desafio.usecase.gateway.FeatureToggleGateway;

@Component
public class FeatureToggleDataprovider implements FeatureToggleGateway{
	@Autowired
	private FeatureToggleRepository featureToggleRepository;
	
	public FeatureToggle save(FeatureToggle featureToggle) {
		return featureToggleRepository.save(featureToggle);
	}
	
	public FeatureToggle getByName(String name) {
		Optional<FeatureToggle> resultado = featureToggleRepository.getByName(name);
		return resultado.orElseThrow(()-> new NotFoundException("Não foi encontrado um toggle para o nome: " + name));
	}
	
	public void delete(String name) {
		featureToggleRepository.deleteByName(name);
	}
}
