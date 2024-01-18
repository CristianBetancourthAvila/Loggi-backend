package com.pagatodo.financieracontable.domain.models.filter;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class LibroDiarioFilterTest {

    @Test
    @DisplayName("Test for LibroDiarioFilter model")
    void testModelLibroDiarioFilter() {
        LibroDiarioFilter filter = new LibroDiarioFilter();
        filter.setDateOne(LocalDate.of(2023, 1, 1));
        filter.setDateTwo(LocalDate.of(2023, 12, 31));
        filter.setIdCaja(BigInteger.valueOf(1));
        filter.setIdUsuario(BigInteger.valueOf(100));
        filter.setIsAdmin(true);

        assertDoesNotThrow(() -> PropertyTester.test(filter));
    }
}
