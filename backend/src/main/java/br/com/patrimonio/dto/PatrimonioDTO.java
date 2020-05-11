package br.com.patrimonio.dto;

import br.com.patrimonio.domain.Patrimonio;
import lombok.Getter;

import java.io.Serializable;

public class PatrimonioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private Integer numTombo;

    @Getter
    private String nome;

    @Getter
    private String descricao;

    @Getter
    private String marca;

    public PatrimonioDTO() {
        super();
    }

    public PatrimonioDTO(Patrimonio patrimonio) {
        this.numTombo = patrimonio.getNumTombo();
        this.nome = patrimonio.getNome();
        this.descricao = patrimonio.getDescricao();
        if (!patrimonio.getMarca().getNome().equals(""))
            this.marca = patrimonio.getMarca().getNome();
    }

}
