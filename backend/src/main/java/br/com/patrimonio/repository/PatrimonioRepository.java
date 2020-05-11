package br.com.patrimonio.repository;

import br.com.patrimonio.domain.Patrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Integer> {

    @Transactional(readOnly = true)
    List<Patrimonio> findAllByMarca_IdMarca(Integer idMarca);
}
