package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_APERTURA_CAJA", sequenceName = "SEQ_APERTURA_CAJA", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "apertura_caja")
public class AperturaCajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APERTURA_CAJA")
    @Column(name = "id", columnDefinition = "NUMBER(15,0)")
    private Long id;

    @Column(name = "usuario_id", nullable = false, columnDefinition = "NUMBER(38,0)")
    private Long usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caja_id", nullable = false, columnDefinition = "NUMBER(10,0)")
    private CajaEntity caja;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDate fechaApertura;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_apertura", nullable = false, length = 8)
    private LocalTime horaApertura;

    @Column(name = "saldo_anterior", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long saldoAnterior;

    @Column(name = "saldo_inicial", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long saldoInicial;

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

    @OneToMany(mappedBy = "aperturaCaja", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<IngresoEntity> ingresoEntities;

    @OneToMany(mappedBy = "aperturaCaja", fetch = FetchType.LAZY)
    private List<EgresoCajaEntity> egresoCaja;

    @OneToMany(mappedBy = "aperturaCaja", fetch = FetchType.LAZY)
    private List<CerrarCajaEntity> cerrarCajaEntities;

    @OneToMany(mappedBy = "aperturaCaja", fetch = FetchType.LAZY)
    private List<RecaudoCarteraEntity> recaudoCarteraEntities;
}
