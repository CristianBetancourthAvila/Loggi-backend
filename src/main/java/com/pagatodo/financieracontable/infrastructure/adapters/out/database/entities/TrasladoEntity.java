package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
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

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_TRASLADO", sequenceName = "SEQ_TRASLADO", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "traslado")
public class TrasladoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRASLADO")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Integer id;

    @Column(name = "trasladar_premio", nullable = false)
    private Boolean trasladarPremio;

    @Column(name = "observacion_traslado",length = 255)
    private String observacionTraslado;

    @Column(name = "observacion_envio",length = 255)
    private String observacionEnvio;

    @Column(name = "observacion_recepcion",length = 255)
    private String observacionRecepcion;

    @Column(name = "observacion_diferencia",length = 255)
    private String observacionDiferencia;

    @Column(name = "serie_premio", columnDefinition = "NUMBER(10,0)")
    private Integer seriePremio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caja_origen_id", columnDefinition = "NUMBER(10,0)")
    private CajaEntity cajaOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caja_destino_id", columnDefinition = "NUMBER(10,0)")
    private CajaEntity cajaDestino;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingreso_caja_id", nullable = true ,columnDefinition = "NUMBER(10,0)")
    private IngresoEntity ingreso;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "egreso_caja_id", nullable = true ,columnDefinition = "NUMBER(10,0)")
    private EgresoCajaEntity egresoCaja;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado",nullable = false,  length = 18)
    private EstadoTraslado estado;

    @Column(name = "valor_diferencia", columnDefinition = "NUMBER(10,0)")
    private Integer valorDiferencia;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_creacion", nullable = false, length = 8)
    private LocalTime horaCreacion;

    @Column(name = "numero_bolsa",length = 20)
    private String numeroBolsa;

}
