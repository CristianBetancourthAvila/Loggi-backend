package com.pagatodo.financieracontable.application.usecases.cerrarcaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.cerrarcaja.CerrarCajaErrorCodes;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.CerrarCaja;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.cerrarcaja.CerrarCajaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class CerrarCajaUseCaseImplTest {

    @Mock
    private AperturaCajaPort aperturaCajaPort;

    @Mock
    private CerrarCajaPort cerrarCajaPort;

    @Mock
    private ValidateStatusCajaUtils validateStatusCajaUtils;

    private AperturaCaja aperturaCaja;

    private CerrarCaja domain;

    private Long id = 1L;

    @BeforeEach
    public void configInitial() {
        aperturaCaja = new AperturaCaja();
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        aperturaCaja.setId(id);
        aperturaCaja.setCaja(caja);

        domain = new CerrarCaja();
        domain.setAperturaCaja(aperturaCaja);
        domain.setNumeroBolsa("123");
        domain.setMonedas(1000L);
        domain.setBilletes(1000L);
        domain.setHoraCierre(LocalTime.now());
        domain.setFechaCierre(LocalDate.now());
        domain.setSaldoFinal(2000L);

    }

    @Test
    @DisplayName("Test for creating a new record of cerrarCaja with success")
    void create_savedCerrarCaja_Success() throws Exception {
        CerrarCajaUseCaseImpl useCase = new CerrarCajaUseCaseImpl(cerrarCajaPort, validateStatusCajaUtils,aperturaCajaPort);

        Mockito
                .when(aperturaCajaPort.findById(Mockito.anyLong()))
                .thenReturn(aperturaCaja);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(true);

        Mockito
                .when(cerrarCajaPort.create(Mockito.any(CerrarCaja.class)))
                .thenReturn(domain);

        var response = useCase.create(domain);

        assertAll("Save New Register of cerrarCaja Success Test",
                () -> assertNotNull(response),
                () -> assertNull(response.getId()));

    }

    @Test
    @DisplayName("Test for creating a new record of cerrarCaja with an error called apertura caja not found")
    void create_savedCerrarCaja_Error_Apertura_caja_not_found() throws Exception {
        CerrarCajaUseCaseImpl useCase = new CerrarCajaUseCaseImpl(cerrarCajaPort, validateStatusCajaUtils,aperturaCajaPort);

        assertThatExceptionOfType(AperturaCajaNotFoundException.class).isThrownBy(() -> useCase.create(domain));
    }

    @Test
    @DisplayName("Test for creating a new record of cerrarCaja with an error called caja is not open")
    void create_savedCerrarCaja_Error_Caja_is_not_open() throws Exception {
        CerrarCajaUseCaseImpl useCase = new CerrarCajaUseCaseImpl(cerrarCajaPort, validateStatusCajaUtils,aperturaCajaPort);

        Mockito
                .when(aperturaCajaPort.findById(Mockito.anyLong()))
                .thenReturn(aperturaCaja);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(false);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(CerrarCajaErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());
    }

    @Test
    @DisplayName("Test for creating a new record of cerrarCaja with an error called observacions field required")
    void create_savedCerrarCaja_Error_observaciones_field_required() throws Exception {
        CerrarCajaUseCaseImpl useCase = new CerrarCajaUseCaseImpl(cerrarCajaPort, validateStatusCajaUtils,aperturaCajaPort);

        Mockito
                .when(aperturaCajaPort.findById(Mockito.anyLong()))
                .thenReturn(aperturaCaja);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(true);

        domain.setPremios(1000L);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(CerrarCajaErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());
    }

    @Test
    @DisplayName("Test for creating a new record of cerrarCaja with an error called invalid amount")
    void create_savedCerrarCaja_Error_invalid_amount() throws Exception {
        CerrarCajaUseCaseImpl useCase = new CerrarCajaUseCaseImpl(cerrarCajaPort, validateStatusCajaUtils,aperturaCajaPort);

        Mockito
                .when(aperturaCajaPort.findById(Mockito.anyLong()))
                .thenReturn(aperturaCaja);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(true);

        domain.setBilletes(5000L);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(CerrarCajaErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());
    }


}
