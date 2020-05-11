package br.com.patrimonio.repository;

import br.com.patrimonio.domain.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    @Transactional(readOnly = true)
    Marca findByNome(String nome);
}
