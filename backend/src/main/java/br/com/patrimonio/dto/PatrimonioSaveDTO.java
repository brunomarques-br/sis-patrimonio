package br.com.patrimonio.dto;

import br.com.patrimonio.domain.Patrimonio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PatrimonioSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer numTombo;

    @Getter
    @Setter
    @NotEmpty(message = "O campo nome não pode ser vazio")
    @Length(min = 1, max = 200, message = "O tamanho deve ser entre 1 e 200 caracteres")
    private String nome;

    @Getter
    @Setter
    private String descricao;

    @Setter
    @Getter
    @NotNull(message = "É obrigatório informar uma marca")
    private Integer idMarca;

    public PatrimonioSaveDTO() {
        super();
    }

    public PatrimonioSaveDTO(Patrimonio patrimonio) {
        this.numTombo = patrimonio.getNumTombo();
        this.nome = patrimonio.getNome();
        this.descricao = patrimonio.getDescricao();
        if (patrimonio.getMarca().getIdMarca() != null)
            idMarca = patrimonio.getMarca().getIdMarca();
    }

}
