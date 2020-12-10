package br.com.itau.seguros.desafio.dataprovider.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

import br.com.itau.seguros.desafio.dataprovider.model.enums.TipoToggle;

/**
 * Representa um Feature Toggle que tem um nome, tipo, valor e se o toggle encontra-se ativo ou inativo.
 *
 * @author LINSRAF Rafael M. Lins
 */
@Value
@Builder
public class FeatureToggle {
    String nome;
    TipoToggle tipo;
    BigDecimal valor;
    boolean ligado;
}
