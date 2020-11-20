package br.com.itau.seguros.desafio.api.model;

import br.com.itau.seguros.desafio.model.FeatureToggle;
import br.com.itau.seguros.desafio.model.TipoToggle;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

import static java.util.Optional.ofNullable;

@Value
public class FeatureToggleRequest {
    @NotEmpty
    String nome;

    @NotEmpty
    String tipo;

    BigDecimal valor;

    @NotNull
    Boolean ligado;

    /**
     * Transforma este Request em uma entidade {@link FeatureToggle}.
     */
    public FeatureToggle asFeatureToggle() {
        return FeatureToggle.builder()
            .nome(nome)
            .tipo(TipoToggle.fromString(tipo))
            .valor(valor)
            .ligado(ligado)
            .build();
    }
}
