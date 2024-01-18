package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.pagatodo.financieracontable.domain.models.enums.Estado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametrizacionConceptoResponse {
	
	private Long id;
	
    private Long codigoConcepto;
    
	private String nombreConcepto;
    
	private String tipoConcepto;//TODO: to delete
	
	private Long subcategoriaId;
	
	private Long tipologiaId;
	
	private String tipo;
    
	private Boolean programable;
    
	private Boolean anulable;
	
	private Boolean imprimible;
	
	private String descripcion;
	
	private LocalDate fechaCreacion;
	
	private LocalTime horaCreacion;
	
    private Estado estado;
}
