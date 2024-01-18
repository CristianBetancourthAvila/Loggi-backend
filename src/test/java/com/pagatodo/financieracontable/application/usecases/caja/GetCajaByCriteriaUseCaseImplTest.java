package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.filter.CajaFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetCajaByCriteriaUseCaseImplTest {

    @Mock
    private CajaPort cajaPort;

    private GetCajaByCriteriaUseCaseImpl getUseCase;

    @BeforeEach
    void setUp() {
        getUseCase = new GetCajaByCriteriaUseCaseImpl(cajaPort);
    }

    @Test
    @DisplayName("Test for findWithFiler method")
    void findWithFilter_Success() throws CajaNotFoundException {
        CajaFilter cajaFilter = new CajaFilter();
        cajaFilter.setCodigoCaja("CAJA001");
        cajaFilter.setPuntoVentaId(1L);
        List<Caja> cajaList = new ArrayList<>();
        cajaList.add(new Caja());
        cajaList.add(new Caja());
        cajaList.add(new Caja());
        Mockito.when(cajaPort.findAllByCodeAndPVTId("CAJA001", 1L)).thenReturn(cajaList);
        PageModel<List<Caja>> result = getUseCase.findWithFiler(cajaFilter, 3, 0);
        assertEquals(BigInteger.valueOf(cajaList.size()), result.total());
    }

    @Test
    @DisplayName("Test for findWithFilter method - No Cajas Found")
    void findWithFilter_NoCajasFound_ThrowCajaNotFoundException() {
        CajaFilter cajaFilter = new CajaFilter();
        cajaFilter.setCodigoCaja("CAJA001");
        cajaFilter.setPuntoVentaId(1L);
        List<Caja> cajaList = new ArrayList<>();
        Mockito.when(cajaPort.findAllByCodeAndPVTId("CAJA001", 1L)).thenReturn(cajaList);
        assertThrows(CajaNotFoundException.class, () -> getUseCase.findWithFiler(cajaFilter, 3, 0));
    }
}
