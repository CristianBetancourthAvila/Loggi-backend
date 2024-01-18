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
@SequenceGenerator(name = "SEQ_INGRESO", sequenceName = "SEQ_INGRESO", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "ingreso")
public class IngresoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INGRESO")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parametrizacion_concepto_id", nullable = false, columnDefinition = "NUMBER(10,0)")
    private ParametrizacionConceptoEntity parametrizacionConcepto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apertura_caja_id", nullable = false, columnDefinition = "NUMBER(15,0)")
    private AperturaCajaEntity aperturaCaja;

    @Column(name = "medio_pago_id", nullable = false, columnDefinition = "NUMBER(2,0)")
    private Integer medioPagoId;

    @Column(name = "usuario_tercero_id", nullable = false, columnDefinition = "NUMBER(8,0)")
    private Integer usuarioTerceroId;

    @Column(name = "valor_recibido", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long valorRecibido;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_creacion", nullable = false, length = 8)
    private LocalTime horaCreacion;

    @Column(name = "motivo_anulacion", length = 255)
    private String motivoAnulacion;

    @OneToOne(mappedBy = "ingreso", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AnulacionEntity anulacionEntity;

    @OneToOne(mappedBy = "ingreso", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TrasladoEntity trasladoEntity;



}
