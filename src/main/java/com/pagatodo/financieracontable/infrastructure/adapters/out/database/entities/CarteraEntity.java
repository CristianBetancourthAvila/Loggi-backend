package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@SequenceGenerator(name = "SEQ_CARTERA", sequenceName = "SEQ_CARTERA", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "cartera")
public class CarteraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARTERA")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Integer id;

    @Column(name = "vendedor_id", nullable = false, columnDefinition = "NUMBER(8,0)")
    private Integer vendedorId;

    @Column(name = "saldo", nullable = false, columnDefinition = "NUMBER(12, 0)")
    private Long saldo;

    @Column(name = "venta_dia_liquidada", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long ventaDiaLiquidada;

    @Column(name = "ventas_dia", nullable = false,  columnDefinition = "NUMBER(12,0)")
    private Long ventasDia;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_creacion", nullable = false, length = 8)
    private LocalTime horaCreacion;

    @OneToMany(mappedBy = "cartera", fetch = FetchType.LAZY)
    private List<RecaudoCarteraEntity> recaudoCarteraEntities;

}
