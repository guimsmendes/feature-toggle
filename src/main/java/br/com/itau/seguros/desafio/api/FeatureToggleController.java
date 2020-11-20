package br.com.itau.seguros.desafio.api;

import br.com.itau.seguros.desafio.api.model.FeatureToggleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * API para um serviço de Feature Flag simples.
 *
 * @author LINSRAF Rafael M. Lins
 */
@RestController
@RequestMapping("/toggle")
public class FeatureToggleController {

    @PostMapping
    public ResponseEntity<?> registrarFeatureFlag(
        @RequestBody @Valid FeatureToggleRequest request
    ) {
        // TODO Implementar este método
        return null;
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> verificarFeatureFlag(
        @PathVariable String nome,
        @RequestParam(name = "valor", required = false) String valor
    ) {
        // TODO Implementar este método
        return null;
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<?> apagarFeatureFlag(
        @PathVariable String nome
    ) {
        // TODO Implementar este método
        return null;
    }

}
