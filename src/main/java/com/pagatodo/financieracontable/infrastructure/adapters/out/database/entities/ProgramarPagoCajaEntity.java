package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "programar_pago_caja")
@SequenceGenerator(name = "SEQ_PROGRAMAR_PAGO_CAJA", sequenceName = "SEQ_PROGRAMAR_PAGO_CAJA", initialValue = 1, allocationSize = 1)
public class ProgramarPagoCajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROGRAMAR_PAGO_CAJA")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programar_pago_id", nullable = false)
    private ProgramarPagoEntity programarPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caja_id", nullable = false)
    private CajaEntity caja;
}
