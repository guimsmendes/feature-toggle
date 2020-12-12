package br.com.itau.seguros.desafio.usecase.gateway;

import org.springframework.stereotype.Component;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;

@Component
public interface FeatureToggleGateway {
	
	public FeatureToggle save(FeatureToggle featureToggle);
	
	public FeatureToggle getByName(String name);
	
	public void delete(String name);
}
