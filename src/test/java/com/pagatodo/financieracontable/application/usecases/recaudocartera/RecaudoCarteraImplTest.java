package com.pagatodo.financieracontable.application.usecases.recaudocartera;


import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.reacudocartera.RecaudoCarteraBusinessException;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.domain.models.RecaudoCartera;
import com.pagatodo.financieracontable.domain.models.vouchers.RecaudoCarteraVoucher;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.cartera.CarteraPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.recaudocartera.RecaudoCarteraPort;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RecaudoCarteraImplTest {

    @Mock
    private RecaudoCarteraPort recaudoCarteraPort;

    @Mock
    private CarteraPort carteraPort;

    @Mock
    private AperturaCajaPort aperturaCajaPort;

    @Mock
    private ValidateStatusCajaUtils validateStatusCajaUtils;

    @InjectMocks
    private RecaudoCarteraUseCaseImpl recaudoCarteraUseCase;

    private static Integer id = 1;

    private static RecaudoCartera domain;

    private static AperturaCaja aperturaCaja;

    private static Cartera cartera;

    private static Caja caja;

    private static RecaudoCarteraVoucher recaudoCarteraVoucher;

    @BeforeEach
    public void configInitial() {
        cartera = new Cartera();
        cartera.setId(4);

        caja = new Caja();
        caja.setId(BigInteger.ONE);

        aperturaCaja = new AperturaCaja();
        aperturaCaja.setCaja(caja);
        aperturaCaja.setId(1L);

        domain = new RecaudoCartera();
        domain.setId(id);
        domain.setCartera(cartera);
        domain.setAperturaCaja(aperturaCaja);
        domain.setObservaciones("Observaciones");
        domain.setValorRecaudo(1000L);
        domain.setMedioPagoId(4);
        domain.setFechaCreacion(LocalDate.now());
        domain.setHoraCreacion(LocalTime.now());

        recaudoCarteraVoucher = new RecaudoCarteraVoucher();
        recaudoCarteraVoucher.setCaja("CAJA125 - 1022");
        recaudoCarteraVoucher.setComprobante(524L);
        recaudoCarteraVoucher.setDetalle("14125-Recaudo");
        recaudoCarteraVoucher.setRecibido("LEONEL ANDRES HIGUITA");
        recaudoCarteraVoucher.setZona("Bolivar Centro Sur");
        recaudoCarteraVoucher.setValor("100.000.000");
        recaudoCarteraVoucher.setFechaHora("25/08/2024 10:22:00 AM");
        recaudoCarteraVoucher.setMedioPago("Efectivo");
    }

    @Test
    @DisplayName("Test for create recaudo cartera method with success")
    void createRecaudoCartera_success() throws NotFoundException, BusinessException {
        Mockito
                .when(aperturaCajaPort.findById(Mockito.any(Long.class)))
                .thenReturn(aperturaCaja);

        Mockito
                .when(carteraPort.findById(Mockito.any(Integer.class)))
                .thenReturn(cartera);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(true);

        Mockito
                .when(aperturaCajaPort.getLastRecord(Mockito.any(BigInteger.class)))
                .thenReturn(aperturaCaja);

        Mockito
                .when(recaudoCarteraPort.save(Mockito.any(RecaudoCartera.class)))
                .thenReturn(domain);

        var response = recaudoCarteraUseCase.create(domain);

        assertAll("Save New Register of recaudo cartera Success Test",
                () -> assertNotNull(response),
                () -> assertNull(response.getId()));
    }

    @Test
    @DisplayName("Test to generate the voucher of recuado cartera method with success")
    void generateVoucher_success() throws JRException {

        var response = recaudoCarteraUseCase.generateVoucher(recaudoCarteraVoucher);

        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Test for creating a new record of recaudo cartera with an error called Apertura caja not found")
    void create_savedRecaudo_Error_Apertura_not_found() {

        assertThrows(AperturaCajaNotFoundException.class, () -> recaudoCarteraUseCase.create(domain));
    }

    @Test
    @DisplayName("Test for creating a new record of recaudo cartera with an error called cartera not found")
    void create_savedRecaudo_Error_Cartera_not_found() {

        Mockito
                .when(aperturaCajaPort.findById(Mockito.any(Long.class)))
                .thenReturn(aperturaCaja);

        assertThrows(CarteraNotFoundException.class, () -> recaudoCarteraUseCase.create(domain));

    }

    @Test
    @DisplayName("Test for creating a new record of recaudo cartera with an error called caja is not open")
    void create_savedRecaudo_Error_caja_is_not_open() throws NotFoundException {

        Mockito
                .when(aperturaCajaPort.findById(Mockito.any(Long.class)))
                .thenReturn(aperturaCaja);

        Mockito
                .when(carteraPort.findById(Mockito.any(Integer.class)))
                .thenReturn(cartera);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(false);

        assertThrows(RecaudoCarteraBusinessException.class, () -> recaudoCarteraUseCase.create(domain));

    }


}
