package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class IngresoRqVoucherTest {

    @Test
    @DisplayName("Test for IngresoRqVoucher model")
    void testModelIngresoRqVoucher(){
        IngresoRqVoucher model = new IngresoRqVoucher();
        model.setConcepto("255655-Transacción");
        model.setComprobante(524L);
        model.setDetalle("14125-Recaudo");
        model.setRecibido("LEONEL ANDRES HIGUITA");
        model.setObservacion("Una observación");
        model.setValor("100.000.000");
        model.setFechaHora("25/08/2024 10:22:00 AM");
        model.setTipoDocumento("CC");
        model.setNumeroDocumento("115689822");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
