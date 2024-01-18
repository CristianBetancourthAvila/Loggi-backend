package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class LibroDiarioResponseTest {

    @Test
    @DisplayName("Test for LibroDiarioResponse model")
    void testModelLibroDiarioResponse() {
        LibroDiarioResponse model = new LibroDiarioResponse();
        model.setIngresos(new ArrayList<>());
        model.setEgresos(new ArrayList<>());
        model.setQuantityIncomes(10);
        model.setQuantityCashOuts(5);
        model.setTotalIncomes(5000L);
        model.setTotalCashOuts(2000L);
        model.setPreviousBalance(3000L);
        model.setDailyNet(1000L);
        model.setNewBalance(4000L);
        model.setUsuarioId(BigInteger.valueOf(123));
        model.setCajaName("CajaPrueba");
        model.setDateSummary(LocalDate.of(2023, 12, 31));
        model.setQuantityUserDateOne(8);
        model.setQuantityUserDateTwo(12);
        model.setQuantityCajaDateOne(6);
        model.setQuantityCajaDateTwo(10);
        model.setQuantityTotalCaja(16);
        model.setQuantityTotalUser(20);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
