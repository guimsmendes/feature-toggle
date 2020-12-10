package br.com.itau.seguros.desafio.usecase.gateway;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;

public interface FeatureToggleGateway {
	
	public FeatureToggle save(FeatureToggle featureToggle);
	
	public FeatureToggle update(FeatureToggle featureToggle);
	
	public FeatureToggle getByName(String name);
	
	public void delete(String name);
}
