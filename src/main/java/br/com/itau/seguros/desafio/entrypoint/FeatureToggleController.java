package br.com.itau.seguros.desafio.entrypoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.entrypoint.model.FeatureToggleRequest;
import br.com.itau.seguros.desafio.usecase.FeatureToggleUseCase;

import java.math.BigDecimal;

import javax.validation.Valid;

/**
 * API para um serviço de Feature Flag simples.
 *
 * @author LINSRAF Rafael M. Lins
 */
@RestController
@RequestMapping("/toggle")
public class FeatureToggleController {
	
	@Autowired
	private FeatureToggleUseCase featureToggleUseCase;

    @PostMapping
    public ResponseEntity<FeatureToggle> registrarFeatureFlag(
        @RequestBody @Valid FeatureToggleRequest request
    ) {
        FeatureToggle featureToggle = featureToggleUseCase.registrarFeatureToggle(request.asFeatureToggle());
        return ResponseEntity.status(HttpStatus.CREATED).body(featureToggle);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> verificarFeatureFlag(
        @PathVariable String nome,
        @RequestParam(name = "valor", required = false) String valor
    ) {
    	if(featureToggleUseCase.buscarFeatureToggle(nome, new BigDecimal(valor)) == "ATIVO") {
    		return ResponseEntity.ok().build();
    	}
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<?> apagarFeatureFlag(
        @PathVariable String nome
    ) {
        featureToggleUseCase.deletarFeatureToggle(nome);
        return ResponseEntity.ok().build();
    }

}
