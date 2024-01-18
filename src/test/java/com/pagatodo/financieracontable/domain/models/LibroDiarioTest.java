package com.pagatodo.financieracontable.domain.models;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LibroDiarioTest {

    @Test
    @DisplayName("Test for LibroDiario model")
    void testModelLibroDiario() {
        LibroDiario libroDiario = new LibroDiario();
        List<Ingreso> ingresos = new ArrayList<>();
        List<EgresoCaja> egresos = new ArrayList<>();
        Ingreso ingreso1 = new Ingreso();
        ingresos.add(ingreso1);
        Ingreso ingreso2 = new Ingreso();
        ingresos.add(ingreso2);
        EgresoCaja egreso1 = new EgresoCaja();
        egresos.add(egreso1);
        EgresoCaja egreso2 = new EgresoCaja();
        egresos.add(egreso2);
        libroDiario.setIngresos(ingresos);
        libroDiario.setEgresos(egresos);
        libroDiario.setQuantityIncomes(2);
        libroDiario.setQuantityCashOuts(2);
        libroDiario.setTotalIncomes(10000L);
        libroDiario.setTotalCashOuts(5000L);
        libroDiario.setPreviousBalance(3000L);
        libroDiario.setDailyNet(2000L);
        libroDiario.setNewBalance(5000L);
        libroDiario.setUsuarioId(BigInteger.valueOf(123));
        libroDiario.setCajaName("CajaPrincipal");
        libroDiario.setDateSummary(LocalDate.of(2023, 12, 31));
        libroDiario.setQuantityUserDateOne(5);
        libroDiario.setQuantityUserDateTwo(7);
        libroDiario.setQuantityCajaDateOne(10);
        libroDiario.setQuantityCajaDateTwo(12);
        libroDiario.setQuantityTotalCaja(22);
        libroDiario.setQuantityTotalUser(12);

        assertDoesNotThrow(() -> PropertyTester.test(libroDiario));
    }
}
