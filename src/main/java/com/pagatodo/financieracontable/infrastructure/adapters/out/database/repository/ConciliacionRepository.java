package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;


import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ConciliacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConciliacionRepository extends JpaRepository<ConciliacionEntity, Long>, JpaSpecificationExecutor<ConciliacionEntity> {
}
