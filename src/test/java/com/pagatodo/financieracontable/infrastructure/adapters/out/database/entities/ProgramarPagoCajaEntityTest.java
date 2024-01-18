package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class ProgramarPagoCajaEntityTest {

    @Test
    @DisplayName("Test ProgramarPagoCajaEntity Setters")
    void testModelProgramarPagoCajaEntitySetters() {
        ProgramarPagoEntity programarPagoEntity = new ProgramarPagoEntity();
        CajaEntity caja = new CajaEntity();
        ProgramarPagoCajaEntity model = new ProgramarPagoCajaEntity();
        model.setId(10);
        model.setProgramarPago(programarPagoEntity);
        model.setCaja(caja);
        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
