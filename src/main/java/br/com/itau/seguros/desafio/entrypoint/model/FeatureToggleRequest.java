package br.com.itau.seguros.desafio.entrypoint.model;

import br.com.itau.seguros.desafio.dataprovider.model.FeatureToggle;
import br.com.itau.seguros.desafio.dataprovider.model.enums.TipoToggle;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.math.BigDecimal;

@Value
public class FeatureToggleRequest {
	
	@Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Favor informar um nome válido.")
    @NotEmpty(message = "Favor preencher o nome.")
    String nome;

    @NotEmpty(message = "Favor informar um tipo válido: Toggle ou Value.")
    String tipo;
    
    BigDecimal valor;

    @NotNull(message = "Favor informar se o Feature Toggle está ou não ligado: True ou False.")
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
