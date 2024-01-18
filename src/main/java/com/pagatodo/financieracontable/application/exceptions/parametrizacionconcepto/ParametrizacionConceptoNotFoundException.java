package com.pagatodo.financieracontable.application.exceptions.parametrizacionconcepto;

import com.pagatodo.commons.exceptions.NotFoundException;

public class ParametrizacionConceptoNotFoundException extends NotFoundException {
	
	private static final long serialVersionUID = -1979721866868546234L;

	public ParametrizacionConceptoNotFoundException() {
        super(ParametrizacionConceptoErrorCodes.NOT_FOUND);
    }
}