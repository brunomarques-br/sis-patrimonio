package br.com.patrimonio.dto;

import br.com.patrimonio.domain.Marca;
import br.com.patrimonio.validator.MarcaInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@MarcaInsert
public class MarcaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    private Integer idMarca;

    @Getter
    @Setter
    @NotEmpty(message = "O campo nome não pode ser vazio")
    @Length(min = 1, max = 200, message = "O tamanho deve ser entre 1 e 200 caracteres")
    private String nome;

    public MarcaDTO() {
        super();
    }

    public MarcaDTO(Marca marca){
        this.idMarca = marca.getIdMarca();
        this.nome = marca.getNome();
    }
}
