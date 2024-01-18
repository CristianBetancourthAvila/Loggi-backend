package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
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
@SequenceGenerator(name = "SEQ_ANULACION", sequenceName = "SEQ_ANULACION", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "anulacion")
public class AnulacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ANULACION")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false, length = 7)
    private TipoMovimiento tipoMovimiento;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingreso_id", nullable = true, columnDefinition = "NUMBER(10,0)")
    private IngresoEntity ingreso;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "egreso_id", nullable = true, columnDefinition = "NUMBER(10,0)")
    private EgresoCajaEntity egresoCaja;

    @Column(name = "autorizador_id", columnDefinition = "NUMBER(10,0)")
    private Integer autorizadorId;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_creacion", nullable = false, length = 8)
    private LocalTime horaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 9)
    private EstadoAnulacion estado;


}
