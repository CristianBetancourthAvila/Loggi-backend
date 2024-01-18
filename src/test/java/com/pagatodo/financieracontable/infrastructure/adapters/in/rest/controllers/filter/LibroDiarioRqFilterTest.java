package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.filter;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class LibroDiarioRqFilterTest {

    @Test
    @DisplayName("Test for LibroDiarioRqFilter model")
    void testModelLibroDiarioRqFilter() {
        LibroDiarioRqFilter model = new LibroDiarioRqFilter();
        model.setDateOne(LocalDate.of(2023, 1, 1));
        model.setDateTwo(LocalDate.of(2023, 12, 31));
        model.setIdCaja(BigInteger.valueOf(123));
        model.setIdUsuario(BigInteger.valueOf(456));
        model.setIsAdmin(true);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
