package com.pagatodo.financieracontable.application.usecases.aperturacaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AperturaCajaUseCaseImplTest {

    @Mock
    private AperturaCajaPort aperturaCajaPort;

    @Mock
    private CajaPort cajaPort;

    @Mock
    private ValidateStatusCajaUtils validateStatusCajaUtils;

    @Mock
    private Caja caja;

    private AperturaCaja domain;

    private Long id = 1L;

    @BeforeEach
    public void configInitial() {
        BigInteger cajaId = BigInteger.valueOf(1);
        caja = new Caja();
        caja.setId(cajaId);
        caja.setPuntoVentaTerminalId(BigInteger.ONE);
        caja.setEstado(Estado.ACTIVO);

        domain = new AperturaCaja();
        domain.setId(id);
        domain.setUsuarioId(BigInteger.valueOf(4));
        domain.setCaja(caja);
        domain.setEstado(true);
        domain.setSaldoInicial(50000L);
        domain.setBilletes(30000L);
        domain.setMonedas(20000L);
    }

    @Test
    @DisplayName("Test for creating a new record of aperturaCaja with success")
    void create_savedAperturaCaja_Success() throws Exception {

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(false);

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        Mockito
                .when(aperturaCajaPort.create(Mockito.any(AperturaCaja.class)))
                .thenReturn(domain);

        var response = useCase.create(domain);

        assertAll("Save New Register of aperturaCaja Success Test",
                () -> assertNotNull(response),
                () -> assertNull(response.getId()),
                () -> assertEquals(Optional.of(0L),Optional.of(response.getSaldoAnterior())));
    }

    @Test
    @DisplayName("Test for creating a new record of AperturaCaja with an error called inactivated caja")
    void create_savedAperturaCaja_Error_Inactivated_caja(){

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        caja.setEstado(Estado.INACTIVO);

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(AperturaCajaErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());

    }

    @Test
    @DisplayName("Test for creating a new record of AperturaCaja with an error called caja already open")
    void create_savedAperturaCaja_Error_Caja_already_open() throws NotFoundException {

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(true);

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(AperturaCajaErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());
    }

    @Test
    @DisplayName("Test for creating a new record of AperturaCaja with an error called not match")
    void create_savedAperturaCaja_Error_Caja_does_not_match(){

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        Caja errorCaja = new Caja();
        errorCaja.setId(BigInteger.ONE);

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(errorCaja);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(AperturaCajaErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());

    }

    @Test
    @DisplayName("Test for creating a new record of AperturaCaja with an error called invalid amount")
    void create_savedAperturaCaja_Error_Invalid_Amount(){

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        domain.setBilletes(5000L);

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(AperturaCajaErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());

    }

    @Test
    @DisplayName("Test for creating a new record of AperturaCaja with an error called required field observaciones")
    void create_savedAperturaCaja_Error_Required_field_observaciones(){

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        domain.setBilletes(20000L);
        domain.setPremios(10000L);

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(AperturaCajaErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());

    }


    @Test
    @DisplayName("Test to validate if one caja is already open")
    void validateStatus_success() throws NotFoundException {

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(true);
        var response = useCase.validateStatus(domain.getUsuarioId());

        assertAll("Validate status caja Success Test",
                () -> assertNotNull(response),
                () -> assertTrue(response));

    }

    @Test
    @DisplayName("Test to find caja if by sellerId")
    void findAssignedCajaBySellerId_success() {

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        Mockito
                .when(cajaPort.findById(Mockito.any(BigInteger.class)))
                .thenReturn(caja);

        var response = useCase.findAssignedCajaByCajaId(domain.getUsuarioId());

        assertAll("find assigned caja by seller id caja Success Test",
                () -> assertNotNull(response),
                () -> assertEquals(response, caja));

    }

    @Test
    @DisplayName("Test to find last record of aperturaCaja")
    void findLastRecord_success() {

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        Mockito
                .when(aperturaCajaPort.getLastRecord(Mockito.any(BigInteger.class)))
                .thenReturn(domain);

        var response = useCase.findLastRecord(caja.getId());

        assertAll("find last record of apertura caja Success Test",
                () -> assertNotNull(response),
                () -> assertEquals(response, domain));

    }

    @Test
    @DisplayName("Test to update the status of a specific record of apertura caja to false with success")
    void updateStatus_success() throws NotFoundException {

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        Mockito
                .when(aperturaCajaPort.findById(Mockito.any(Long.class)))
                .thenReturn(domain);

        useCase.updateStatus(domain.getId());

        Mockito.verify(aperturaCajaPort, Mockito.times(1)).updateStatus(domain.getId(), false);

    }

    @Test
    @DisplayName("Test to update the status of a specific record of apertura caja to false with an error called apertura not found")
    void updateStatus_Error_apertura_caja_not_found() throws NotFoundException {

        AperturaCajaUseCaseImpl useCase = new AperturaCajaUseCaseImpl(aperturaCajaPort,validateStatusCajaUtils, cajaPort);

        assertThrows(AperturaCajaNotFoundException.class, () -> useCase.updateStatus(domain.getId()));
    }
}
