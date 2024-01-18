package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.RecaudoCarteraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecaudoCarteraRepository extends JpaRepository<RecaudoCarteraEntity, Integer> {
}
