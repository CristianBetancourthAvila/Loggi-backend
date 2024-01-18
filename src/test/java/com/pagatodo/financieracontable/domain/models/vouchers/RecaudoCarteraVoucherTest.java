package com.pagatodo.financieracontable.domain.models.vouchers;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class RecaudoCarteraVoucherTest {

    @Test
    @DisplayName("Test for RecaudoCarteraVoucher model")
    void testModelRecaudoCarteraVoucher(){
        RecaudoCarteraVoucher model = new RecaudoCarteraVoucher();
        model.setCaja("CAJA125 - 1022");
        model.setComprobante(524L);
        model.setDetalle("14125-Recaudo");
        model.setRecibido("LEONEL ANDRES HIGUITA");
        model.setZona("Bolivar Centro Sur");
        model.setValor("100.000.000");
        model.setFechaHora("25/08/2024 10:22:00 AM");
        model.setMedioPago("Efectivo");
        model.setTipoDocumento("CC");
        model.setNumeroDocumento("115689822");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
