package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto.helpers;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametrizacionConceptoTable extends ParametrizacionConcepto{
	
	private String subcategoria;
	private String tipologia;
	private String codigoCuenta;
	private String codigoCuentaAnulable;
	
	private String fechaGestion;
	private String estadoRegistro;

}
