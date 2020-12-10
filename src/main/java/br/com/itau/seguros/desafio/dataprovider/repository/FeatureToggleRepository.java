package br.com.itau.seguros.desafio.dataprovider.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;

@Repository
public interface FeatureToggleRepository extends JpaRepository<FeatureToggle, Long> {
	
	@Query("SELECT u FROM toggle u WHERE nome = ?1")
	public Optional<FeatureToggle> getByName(String name);
	
	@Transactional
	@Modifying
	@Query("DELETE toggle WHERE nome = ?1")
	public void deleteByName(String name);
	
}
