package br.com.itau.seguros.desafio.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.dataprovider.repository.FeatureToggleRepository;
import br.com.itau.seguros.desafio.usecase.gateway.FeatureToggleGateway;

public class FeatureToggleDataprovider implements FeatureToggleGateway{
	@Autowired
	private FeatureToggleRepository featureToggleRepository;
	
	public FeatureToggle save(FeatureToggle featureToggle) {
		return featureToggleRepository.save(featureToggle);
	}
	
	public FeatureToggle update(FeatureToggle featureToggle) {
		return featureToggleRepository.save(featureToggle);
	}
	
	public FeatureToggle getByName(String name) {
		return featureToggleRepository.getByName(name);
	}
	
	public void delete(String name) {
		featureToggleRepository.deleteByName(name);
	}
}
