package br.com.patrimonio.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class MarcaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Integer idMarca;

    @Getter
    @Setter
    private String nome;

    public MarcaDTO() {
    }

    public MarcaDTO(Integer idMarca, String nome) {
        this.idMarca = idMarca;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarcaDTO)) return false;
        MarcaDTO marca = (MarcaDTO) o;
        return idMarca.equals(marca.idMarca) &&
                nome.equals(marca.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarca, nome);
    }
}
