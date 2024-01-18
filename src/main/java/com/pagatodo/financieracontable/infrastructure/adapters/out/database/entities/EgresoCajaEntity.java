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

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_EGRESO_CAJA", sequenceName = "SEQ_EGRESO_CAJA", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "egreso_caja")
public class EgresoCajaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EGRESO_CAJA")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Integer id;
    //TODO: El tamaño a 20 es temporal
    @Column(name = "usuario_tercero_id", nullable = false, columnDefinition = "NUMBER(20,0)")
    private BigInteger usuarioTerceroId;

    @Column(name = "usuario_id", nullable = false, columnDefinition = "NUMBER(10,0)")
    private Integer usuarioId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programacion_pago_id", nullable = false,columnDefinition = "NUMBER(10,0)")
    private ProgramarPagoEntity programarPago;

    @Column(name = "motivo_anulacion", length = 255)
    private String motivoAnulacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_creacion", nullable = false, length = 8)
    private LocalTime horaCreacion;

    @OneToOne(mappedBy = "egresoCaja", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AnulacionEntity anulacionEntity;

    @OneToOne(mappedBy = "egresoCaja", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TrasladoEntity trasladoEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apertura_caja_id", nullable = false,columnDefinition = "NUMBER(15,0)")
    private AperturaCajaEntity aperturaCaja;
}
