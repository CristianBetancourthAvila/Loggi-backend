package com.pagatodo.financieracontable.application.usecases.commons;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class ValidateStatusCajaUtilsTest {

    @Mock
    private CajaPort cajaPort;

    @Mock
    private AperturaCajaPort aperturaCajaPort;

    @InjectMocks
    private ValidateStatusCajaUtils validateStatusCajaUtils;

    private static Long aperturaCajaId = 1L;

    private static BigInteger cajaId = BigInteger.ONE;

    private static AperturaCaja lastRecord;

    private static Caja caja;

    @BeforeEach
    public void configInitial() {
        validateStatusCajaUtils = new ValidateStatusCajaUtils(cajaPort, aperturaCajaPort);

        caja = new Caja();
        caja.setId(cajaId);

        lastRecord = new AperturaCaja();
        lastRecord.setId(aperturaCajaId);
        lastRecord.setUsuarioId(BigInteger.valueOf(4));
        lastRecord.setCaja(caja);
        lastRecord.setEstado(true);
        lastRecord.setSaldoInicial(50000L);
        lastRecord.setBilletes(30000L);
        lastRecord.setMonedas(20000L);
    }

    @Test
    @DisplayName("Test to validate the status of a caja when is open ")
    void validateStatusCaja_Success_when_is_open() throws Exception {

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        Mockito
                .when(aperturaCajaPort.getLastRecord(Mockito.any(BigInteger.class)))
                .thenReturn(lastRecord);

        var result = validateStatusCajaUtils.validateCajaAlreadyOpen(cajaId);

        assertAll("Validate if a caja is open when it is",
                () -> Assertions.assertTrue(result));

    }

    @Test
    @DisplayName("Test to validate the status of a caja when is not open ")
    void validateStatusCaja_Success_when_is_not_open() throws Exception {

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        lastRecord.setEstado(false);

        Mockito
                .when(aperturaCajaPort.getLastRecord(Mockito.any(BigInteger.class)))
                .thenReturn(lastRecord);

        var result = validateStatusCajaUtils.validateCajaAlreadyOpen(cajaId);

        assertAll("Validate if a caja is open when it is not",
                () -> Assertions.assertFalse(result));

    }

    @Test
    @DisplayName("Test to validate the status of a caja with an error called caja not found ")
    void validateStatusCaja_error_caja_not_found(){

        assertThatExceptionOfType(CajaIdNotFoundException.class)
                .isThrownBy(() -> validateStatusCajaUtils.validateCajaAlreadyOpen(cajaId))
                .withMessage(CajaErrorCodes.CAJA_ID_NOT_FOUND.getLocalizedMessage());

    }


}
