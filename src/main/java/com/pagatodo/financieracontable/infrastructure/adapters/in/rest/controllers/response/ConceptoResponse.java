package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConceptoResponse {
	
	private Long codigo;
	
	private String nombre;
	
	private Long parametrizacionConceptoId;
}
