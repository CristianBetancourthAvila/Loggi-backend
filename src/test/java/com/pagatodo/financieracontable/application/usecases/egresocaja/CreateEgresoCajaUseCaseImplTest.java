package com.pagatodo.financieracontable.application.usecases.egresocaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaBusinessException;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja.EgresoCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateEgresoCajaUseCaseImplTest {

    @Mock
    private EgresoCajaPort egresoCajaPort;

    @Mock
    private ProgramarPagoPort programarPagoPort;

    @Mock
    private AperturaCajaPort aperturaCajaPort;

    @Mock
    private ValidateStatusCajaUtils validateStatusCajaUtils;

    @InjectMocks
    private CreateEgresoCajaUseCaseImpl createEgresoCajaUseCase;

    private static EgresoCaja egresoCaja;

    @BeforeEach
    void configInitial() {
        egresoCaja = new EgresoCaja();
        ProgramarPago programarPago = new ProgramarPago();
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        AperturaCaja aperturaCaja = new AperturaCaja();
        aperturaCaja.setId(1L);
        aperturaCaja.setCaja(caja);
        programarPago.setId(1);
        egresoCaja.setId(1);
        egresoCaja.setUsuarioId(2);
        egresoCaja.setProgramarPago(programarPago);
        egresoCaja.setFechaCreacion(LocalDate.now());
        egresoCaja.setAperturaCaja(aperturaCaja);
    }

    @Test
    @DisplayName("Create EgresoCaja Test")
    void createEgresoCaja() throws NotFoundException {
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setId(1);
        when(programarPagoPort.findById(1)).thenReturn(programarPago);
        when(validateStatusCajaUtils.validateCajaAlreadyOpen(any(BigInteger.class))).thenReturn(false);
        assertThrows(EgresoCajaBusinessException.class, () -> createEgresoCajaUseCase.create(egresoCaja));
    }

    @Test
    @DisplayName("Create EgresoCaja Successfully")
    void createEgresoCaja_Successfully() throws BusinessException, NotFoundException {
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setId(1);
        when(programarPagoPort.findById(1)).thenReturn(programarPago);
        when(validateStatusCajaUtils.validateCajaAlreadyOpen(any(BigInteger.class))).thenReturn(true);
        EgresoCaja egresoCajaSaved = new EgresoCaja();
        when(egresoCajaPort.save(any(EgresoCaja.class))).thenReturn(egresoCajaSaved);
        EgresoCaja result = createEgresoCajaUseCase.create(egresoCaja);
        assertEquals(egresoCajaSaved, result);
    }

}
