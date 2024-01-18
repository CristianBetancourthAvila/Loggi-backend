package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.financieracontable.domain.models.enums.Estado;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "SEQ_CAJA", sequenceName = "SEQ_CAJA", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "caja")
public class CajaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAJA")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private BigInteger id;

    @Column(name = "punto_venta_terminal_id", nullable = false, columnDefinition = "NUMBER(6,0)")
    private Long puntoVentaTerminalId;

    @Column(name = "codigo_caja", nullable = false, length = 12)
    private String codigoCaja;

    @Column(name = "nombre_caja", nullable = false, length = 40)
    private String nombreCaja;

    @Column(name = "codigo_dane", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long codigoDane;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_creacion", nullable = false, length = 8)
    private LocalTime horaCreacion;

    @Column(name = "monto_maximo_pago", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long montoMaximoPago;

    @Column(name = "monto_maximo_giro", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long montoMaximoGiro;

    @Column(name = "monto_maximo_beps", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long montoMaximoBeps;

    @Column(name = "monto_maximo_bbva", nullable = false, columnDefinition = "NUMBER(12,0)")
    private Long montoMaximoBbva;

    @Column(name = "cantidad_papel_bond", nullable = false, columnDefinition = "NUMBER(8,0)")
    private Long cantidadPapelBond;

    @Column(name = "cantidad_papel_termico", nullable = false, columnDefinition = "NUMBER(8,0)")
    private Long cantidadPapelTermico;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 10)
    private Estado estado;

    @OneToMany(mappedBy = "caja", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AperturaCajaEntity> aperturaCajaEntities;

    @OneToMany(mappedBy = "cajaOrigen", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TrasladoEntity> cajaOrigenTrasladoEntities;

    @OneToMany(mappedBy = "cajaDestino", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TrasladoEntity> cajaDestinoTrasladoEntities;
}
