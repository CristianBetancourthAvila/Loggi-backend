package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_CERRAR_CAJA", sequenceName = "SEQ_CERRAR_CAJA", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "cerrar_caja")
public class CerrarCajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CERRAR_CAJA")
    @Column(name = "id", columnDefinition = "NUMBER(15,0)")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apertura_caja_id", nullable = false, columnDefinition = "NUMBER(15,0)")
    private AperturaCajaEntity aperturaCaja;

    @Column(name = "numero_bolsa", nullable = false, length = 15)
    private String numeroBolsa;

    @Column(name = "fecha_cierre", nullable = false)
    private LocalDate fechaCierre;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_cierre", nullable = false, length = 8)
    private LocalTime horaCierre;

    @Column(name = "saldo_final", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long saldoFinal;

    @Column(name = "billetes", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long billetes;

    @Column(name = "monedas", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long monedas;

    @Column(name = "premios", columnDefinition = "NUMBER(12,0)")
    private Long premios;

    @Column(name = "otros", columnDefinition = "NUMBER(12,0)")
    private Long otros;

    @Column(name = "observaciones", length = 255)
    private String observaciones;


}
