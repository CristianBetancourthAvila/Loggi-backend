package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_CONCILIACION", sequenceName = "SEQ_CONCILIACION", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "Conciliacion")
public class ConciliacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONCILIACION")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conciliacion", nullable = false, length = 20)
    private TipoConciliacion tipoConciliacion;

    @Column(name = "aliado_producto", nullable = false, length = 30)
    private String aliadoProducto;

    @Column(name = "fecha_inicial", nullable = false)
    private LocalDate fechaInicial;

    @Column(name = "fecha_final", nullable = false)
    private LocalDate fechaFinal;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "archivo")
    private String archivo;
}
