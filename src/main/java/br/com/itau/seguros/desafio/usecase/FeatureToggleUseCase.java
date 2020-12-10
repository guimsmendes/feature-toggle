package br.com.itau.seguros.desafio.usecase;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.seguros.desafio.usecase.constant.Constant;
import br.com.itau.seguros.desafio.dataprovider.model.enums.TipoToggle;
import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.usecase.gateway.FeatureToggleGateway;

@Service
public class FeatureToggleUseCase {

	@Autowired
	private FeatureToggleGateway featureToggleGateway;

	public FeatureToggle registrarFeatureToggle(FeatureToggle featureToggle) {
		return featureToggleGateway.save(featureToggle);
	}

	public String buscarFeatureToggle(String nome, BigDecimal valor) {

		FeatureToggle featureToggle = featureToggleGateway.getByName(nome);

		if (featureToggle != null && featureToggle.isLigado() && verificarTipo(featureToggle, valor)) {
			return Constant.ATIVO;
		}
		return Constant.INATIVO;

	}

	public void deletarFeatureToggle(String nome) {
		featureToggleGateway.delete(nome);

	}

	private boolean verificarTipo(FeatureToggle featureToggle, BigDecimal valor) {
		if (featureToggle.getTipo() == TipoToggle.TOGGLE || valor.compareTo(featureToggle.getValor()) == 1) {
			return true;
		}
		return false;
	}

}
