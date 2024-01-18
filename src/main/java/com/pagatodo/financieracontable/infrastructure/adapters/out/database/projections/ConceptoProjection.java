package com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections;

public record ConceptoProjection(
		Long codigoConcepto, 
		String nombreConcepto,
		Long id
		) {}
