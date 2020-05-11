package br.com.patrimonio.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Patrimonio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numTombo;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    public Patrimonio() {
        super();
    }

    public Patrimonio(Integer numTombo, String nome, String descricao, Marca marca) {
        super();
        this.numTombo = numTombo;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patrimonio)) return false;
        Patrimonio that = (Patrimonio) o;
        return numTombo.equals(that.numTombo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numTombo);
    }
}
