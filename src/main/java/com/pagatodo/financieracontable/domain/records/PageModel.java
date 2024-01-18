package com.pagatodo.financieracontable.domain.records;

import java.math.BigInteger;

public record PageModel<T>(T data, BigInteger total) {}