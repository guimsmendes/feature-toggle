package br.com.itau.seguros.desafio.model;

/**
 * Tipos disponíveis de Feature Toggle.
 *
 * @author LINSRAF Rafael M. Lins
 */
public enum TipoToggle {
    TOGGLE,
    VALUE;

    /**
     * Retorna um {@link TipoToggle} relativo a uma string.
     */
    public static TipoToggle fromString(String desc) {
        for (TipoToggle tipoToggle : values()) {
            if (tipoToggle.name().equalsIgnoreCase(desc)) {
                return tipoToggle;
            }
        }

        return null;
    }
}
