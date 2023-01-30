package com.kaibacorp.figmabackend.domain.repository;

import com.kaibacorp.figmabackend.domain.entity.Cargo;
import com.kaibacorp.figmabackend.domain.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CargoRepository extends JpaRepository<Cargo,Long> {

    public void removeAllBySetor(Setor cargo);
}
