package br.com.itau.seguros.desafio.dataprovider.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import br.com.itau.seguros.desafio.dataprovider.model.enums.TipoToggle;

/**
 * Representa um Feature Toggle que tem um nome, tipo, valor e se o toggle encontra-se ativo ou inativo.
 *
 * @author LINSRAF Rafael M. Lins
 */

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "toggle")
public class FeatureToggle implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6016452524907190931L;

	@Id
	@Column(length = 75, nullable = false, unique = true)
    String nome;
	
	@Column(length = 20, nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
    TipoToggle tipo;
	
    BigDecimal valor;
    
    @Column(nullable = false)
    boolean ligado;
    
}
