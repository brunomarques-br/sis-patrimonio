package br.com.patrimonio.dto;

import br.com.patrimonio.validator.MarcaInsert;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@MarcaInsert
public class MarcaPatrimonioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("ID")
    @Getter
    @Setter
    private Integer idMarca;

    @JsonProperty("Nome")
    @Getter
    @Setter
    private String nome;

    @JsonProperty("Patrimônios")
    @Getter
    @Setter
    private List<PatrimonioDTO> patrimonioDTOList;

    public MarcaPatrimonioDTO() {
        super();
    }

    public MarcaPatrimonioDTO(Integer idMarca, String nome, List<PatrimonioDTO> patrimonioDTOList) {
        this.idMarca = idMarca;
        this.nome = nome;
        this.patrimonioDTOList = patrimonioDTOList;
    }
}
