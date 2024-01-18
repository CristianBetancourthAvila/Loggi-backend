package com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository;

import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.AperturaCajaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

public interface AperturaCajaRepository extends JpaRepository<AperturaCajaEntity, Long> {

    @Query("SELECT ac " +
            "FROM AperturaCajaEntity ac JOIN CajaEntity ca " +
            "ON ac.caja.id = ca.id " +
            "WHERE ca.id = :cajaId " +
            "AND ac.fechaApertura = (SELECT MAX(ac2.fechaApertura) " +
                                    "FROM AperturaCajaEntity ac2 " +
                                    "WHERE ac2.caja.id = ca.id ) " +
            "AND ac.horaApertura = (SELECT MAX(ac3.horaApertura) " +
                                    "FROM AperturaCajaEntity ac3 " +
                                    "WHERE ac3.caja.id = ca.id " +
                                    "AND ac3.fechaApertura = " +
                                        "(SELECT MAX(ac4.fechaApertura) " +
                                        "FROM AperturaCajaEntity ac4 " +
                                        "WHERE ac4.caja.id = ca.id))")
    AperturaCajaEntity getLastRecord(@Param("cajaId") BigInteger cajaId);

    @Transactional
    @Modifying
    @Query("UPDATE AperturaCajaEntity AC SET AC.estado = :status WHERE AC.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") boolean status);

}
