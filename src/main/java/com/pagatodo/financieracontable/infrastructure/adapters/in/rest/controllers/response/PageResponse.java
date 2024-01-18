package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PageResponse<T> {

    @JsonProperty("registros")
    private T data;

    @JsonProperty("cantidadTotal")
    private BigInteger total;

}

