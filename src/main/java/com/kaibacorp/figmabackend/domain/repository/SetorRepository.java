package com.kaibacorp.figmabackend.domain.repository;

import com.kaibacorp.figmabackend.domain.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface SetorRepository extends JpaRepository<Setor,Long> {

    void deleteByNome(String nome);

    Optional<Setor> findByNome(String nome);

}
