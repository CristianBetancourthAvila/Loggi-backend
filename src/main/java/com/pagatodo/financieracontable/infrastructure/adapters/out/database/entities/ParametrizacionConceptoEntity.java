package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "PARAMETRIZACION_CONCEPTO_SEQ", sequenceName = "SEQ_PARAMETRIZACION_CONCEPTO", initialValue = 1, allocationSize = 1)
@Entity
@Table(name = "PARAMETRIZACION_CONCEPTO")
public class ParametrizacionConceptoEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETRIZACION_CONCEPTO_SEQ")
    @Column(name = "id", columnDefinition = "NUMBER(10,0)")
    private Long id;
    
    @Column(name = "codigo_concepto", nullable = false, columnDefinition = "NUMBER(10,0)")
    private Long codigoConcepto;
    
    @Column(name = "nombre_concepto", nullable = false, length = 200)
	private String nombreConcepto;
    
    @Column(name = "tipo_concepto", nullable = false, length = 200)
	private String tipoConcepto;//TODO: to delete
    
    @Column(name = "subcategoria_id", nullable = false, columnDefinition = "NUMBER(10,0)")
    private Long subcategoriaId;
    
    @Column(name = "tipologia_id", nullable = false, columnDefinition = "NUMBER(10,0)")
    private Long tipologiaId;
    
    @Column(name = "tipo", nullable = false, length = 200)
	private String tipo;
    
    @Column(name = "programable", nullable = false)
	private Boolean programable;
    
	@Column(name = "anulable", nullable = false)
	private Boolean anulable;
	
	@Column(name = "imprimible", nullable = false)
	private Boolean imprimible;
	
    @Column(name = "descripcion", nullable = false, length = 255)
	private String descripcion;
	
	@Column(name = "fecha_creacion", nullable = false)
	private LocalDate fechaCreacion;
	
    @Convert(converter = com.pagatodo.commons.jpa.type.LocalTimeStringConverter.class)
    @Column(name = "hora_creacion", nullable = false, length = 8)
    private LocalTime horaCreacion;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 30)
    private Estado estado;

    @OneToMany(mappedBy = "parametrizacionConcepto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<IngresoEntity> ingresoEntities;

    @OneToMany(mappedBy = "parametrizacionConcepto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProgramarPagoEntity> programarPagoList;
}
