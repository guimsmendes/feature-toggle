package br.com.itau.seguros.desafio.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;

@Repository
public interface FeatureToggleRepository extends JpaRepository<FeatureToggle, Long> {
	
	@Query("SELECT u FROM featureToggle u WHERE name = ?1")
	public FeatureToggle getByName(String name);
	
	@Query("DELETE * FROM featureToggle WHERE name = ?1")
	public void deleteByName(String name);
}
