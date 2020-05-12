package br.com.patrimonio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class PatrimonioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer numTombo;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private Integer idMarca;

    @Getter
    @Setter
    private String marca;

    public PatrimonioDTO() {
    }

    public PatrimonioDTO(Integer numTombo, String nome, String descricao, Integer idMarca, String marca) {
        this.numTombo = numTombo;
        this.nome = nome;
        this.descricao = descricao;
        this.idMarca = idMarca;
        this.marca = marca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatrimonioDTO)) return false;
        PatrimonioDTO that = (PatrimonioDTO) o;
        return numTombo.equals(that.numTombo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numTombo);
    }
}
