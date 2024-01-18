package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.request;

import com.pagatodo.financieracontable.domain.models.enums.Estado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParametrizacionConceptoRequest {
	
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
	
    private Estado estado;
}
