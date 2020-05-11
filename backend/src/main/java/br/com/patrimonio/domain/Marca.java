package br.com.patrimonio.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Marca implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarca;

    @Getter
    @Setter
    @NotNull
    private String nome;

    public Marca() {
        super();
    }

    public Marca(Integer idMarca, String nome) {
        super();
        this.idMarca = idMarca;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marca)) return false;
        Marca marca = (Marca) o;
        return idMarca.equals(marca.idMarca) &&
                nome.equals(marca.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarca, nome);
    }
}
