package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAllCajaUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    @InjectMocks
    private CreateAllCajaUseCaseImpl useCase;

    private static Caja domain;

    @BeforeAll
    static void configInitial() {
        domain = new Caja();
        domain.setId(BigInteger.ONE);
        domain.setPuntoVentaTerminalId(BigInteger.TWO);
        domain.setCodigoCaja("CAJA001");
        domain.setNombreCaja("Caja de Prueba");
        domain.setCodigoDane(123456L);
        domain.setMontoMaximoPago(10000L);
        domain.setMontoMaximoGiro(5000L);
        domain.setMontoMaximoBeps(2000L);
        domain.setMontoMaximoBbva(3000L);
        domain.setCantidadPapelBond(500L);
        domain.setCantidadPapelTermico(1000L);
        domain.setEstado(Estado.ACTIVO);
    }
    @Test
    @DisplayName("Test for the saveAll method of CreateAllCajaUseCaseImpl")
    void saveAllCajas_Success() {
        List<Caja> cajaDomains = List.of(domain);
        when(cajaPort.saveAll(anyList()))
                .thenReturn(cajaDomains);
        List<Caja> savedCajas = useCase.saveAll(cajaDomains);
        assertAll("Save All Cajas Success Test",
                () -> assertNotNull(savedCajas),
                () -> assertEquals(savedCajas.size(),cajaDomains.size()),
                () -> savedCajas.forEach(caja -> {
                    Assertions.assertNull(caja.getId());
                    assertNotNull(caja.getHoraCreacion());
                    assertNotNull(caja.getFechaCreacion());
                })
        );
    }
}
