package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CerrarCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CerrarCajaRepository extends JpaRepository<CerrarCajaEntity, Long> {
}
