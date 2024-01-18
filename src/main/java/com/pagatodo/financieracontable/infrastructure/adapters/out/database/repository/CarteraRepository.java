package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CarteraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarteraRepository extends JpaRepository<CarteraEntity, Integer> {

    @Query("SELECT C FROM CarteraEntity C WHERE C.vendedorId = :sellerId")
    CarteraEntity findCarteraByVendedorId(@Param("sellerId") Integer sellerId);
}
