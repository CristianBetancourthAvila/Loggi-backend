package com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.vouchers;

import com.pagatodo.commons.junit.PropertyTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class AnulacionRqVoucherTest {
    @Test
    @DisplayName("Test for AnulacionRqVoucher model")
    void testModelAnulacionRqVoucher(){
        AnulacionRqVoucher model = new AnulacionRqVoucher();
        model.setConcepto("255655-Transacción");
        model.setComprobante(524L);
        model.setMotivo("Motivo");
        model.setRecibido("LEONEL ANDRES HIGUITA");
        model.setAutorizador("Jhoan Sebastian");
        model.setIdentificacion("156756566");
        model.setFechaSolicitud("11/12/2023");
        model.setAutorizado("Duplicado");
        model.setComprobanteMovimiento("4251");
        model.setValor("100.000.000");
        model.setFechaHora("25/08/2024 10:22:00 AM");
        model.setTipoDocumento("CC");
        model.setNumeroDocumento("115689822");

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
}
