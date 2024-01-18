package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_PROGRAMAR_PAGO", sequenceName = "SEQ_PROGRAMAR_PAGO", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "programar_pago")
public class ProgramarPagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROGRAMAR_PAGO")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parametrizacion_concepto_id", nullable = false,columnDefinition = "NUMBER(10,0)")
    private ParametrizacionConceptoEntity parametrizacionConcepto;
    //TODO: El tamaño a 20 es temporal
    @Column(name = "usuario_tercero_id", nullable = false,columnDefinition = "NUMBER(20,0)")
    private BigInteger usuarioTerceroId;

    @Column(name = "afectacion_cartera", nullable = false)
    private Boolean afectacionCartera;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_creacion", nullable = false, length = 8)
    private LocalTime horaCreacion;

    @Column(name = "codigo_vendedor", columnDefinition = "NUMBER(15,0)")
    private Long codigoVendedor;

    @Column(name = "fecha_aplicacion", nullable = false)
    private LocalDate fechaAplicacion;

    @Column(name = "rango_vigencia", nullable = false, length = 25)
    private String rangoVigencia;

    @Column(name = "valor", nullable = false, columnDefinition = "NUMBER(11,0)")
    private Long valor;

    @Column(name = "observacion",  nullable = false, length = 255)
    private String observacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 15)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "condicion", nullable = false, length = 9)
    private Condicion condicion;

    @OneToOne(mappedBy = "programarPago", fetch = FetchType.LAZY)
    private EgresoCajaEntity egresoCaja;
}
