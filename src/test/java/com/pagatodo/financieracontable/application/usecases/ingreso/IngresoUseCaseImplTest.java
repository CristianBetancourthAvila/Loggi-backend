package com.pagatodo.financieracontable.application.usecases.ingreso;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.aperturacaja.AperturaCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoBusinessException;
import com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.parametrizacionconcepto.ParametrizacionConceptoNotFoundException;
import com.pagatodo.financieracontable.application.usecases.commons.CreateAnulacionUtils;
import com.pagatodo.financieracontable.application.usecases.commons.ValidateStatusCajaUtils;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.vouchers.IngresoVoucher;
import com.pagatodo.financieracontable.infrastructure.ports.out.aperturacaja.AperturaCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.ingreso.IngresoPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class IngresoUseCaseImplTest {

    @Mock
    private IngresoPort ingresoPort;

    @Mock
    private AperturaCajaPort aperturaCajaPort;

    @Mock
    private ParametrizacionConceptoPort parametrizacionConceptoPort;

    @Mock
    private CreateAnulacionUtils createAnulacionUtils;

    @Mock
    private ValidateStatusCajaUtils validateStatusCajaUtils;

    private Ingreso domain;

    private String cancellationReason;

    private Caja caja;

    private AperturaCaja aperturaCaja;

    private ParametrizacionConcepto parametrizacionConcepto;

    private IngresoVoucher ingresoVoucher;

    @BeforeEach
    public void configInitial() {
        cancellationReason = "Motivo de anulacion";
        parametrizacionConcepto = new ParametrizacionConcepto();
        parametrizacionConcepto.setId(8L);
        parametrizacionConcepto.setAnulable(true);

        caja = new Caja();
        caja.setId(BigInteger.valueOf(5));
        caja.setEstado(Estado.ACTIVO);

        aperturaCaja = new AperturaCaja();
        aperturaCaja.setId(1L);
        aperturaCaja.setCaja(caja);

        domain = new Ingreso();
        domain.setId(1);
        domain.setFechaCreacion(LocalDate.of(2023, 10, 22));
        domain.setHoraCreacion(LocalTime.now());
        domain.setAperturaCaja(aperturaCaja);
        domain.setParametrizacionConcepto(parametrizacionConcepto);
        domain.setMedioPagoId(4);
        domain.setUsuarioTerceroId(8);
        domain.setMotivoAnulacion("Motivo anulacion");
        domain.setValorRecibido(25000L);
        domain.setObservaciones("Observaciones prueba");

        ingresoVoucher = new IngresoVoucher();
        ingresoVoucher.setConcepto("255655-Transacción");
        ingresoVoucher.setComprobante(524L);
        ingresoVoucher.setDetalle("14125-Recaudo");
        ingresoVoucher.setRecibido("LEONEL ANDRES HIGUITA");
        ingresoVoucher.setObservacion("Una observación");
        ingresoVoucher.setValor("100.000.000");
        ingresoVoucher.setFechaHora("25/08/2024 10:22:00 AM");
        ingresoVoucher.setTipoDocumento("CC");
        ingresoVoucher.setNumeroDocumento("115689822");
    }

    @Test
    @DisplayName("Test for creating a new record of ingreso with success")
    void create_savedIngreso_Success() throws Exception {
        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils, createAnulacionUtils);

        Mockito
                .when(ingresoPort.save(Mockito.any(Ingreso.class)))
                .thenReturn(domain);

        Mockito
                .when(aperturaCajaPort.findById(Mockito.any(Long.class)))
                .thenReturn(aperturaCaja);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(true);

        Mockito
                .when(aperturaCajaPort.getLastRecord(Mockito.any(BigInteger.class)))
                .thenReturn(aperturaCaja);


        var response = useCase.create(domain);

        assertAll("Save New Register of ingreso Success Test",
                () -> assertNotNull(response),
                () -> assertNull(response.getId()));

    }

    @Test
    @DisplayName("Test for creating a new record of ingreso with an error called inactivated caja")
    void create_savedIngreso_Error_Inactivated_caja(){

        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils,createAnulacionUtils);

        caja.setEstado(Estado.INACTIVO);

        Mockito
                .when(aperturaCajaPort.findById(Mockito.any(Long.class)))
                .thenReturn(aperturaCaja);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(IngresoErrorCodes.ERROR_INGRESO.getLocalizedMessage());

    }

    @Test
    @DisplayName("Test for creating a new record of ingreso with an error called Apertura caja not found")
    void create_savedIngreso_Error_Apertura_not_found() throws BusinessException {

        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils,createAnulacionUtils);

        assertThrows(AperturaCajaNotFoundException.class, () -> useCase.create(domain));

    }

    @Test
    @DisplayName("Test for creating a new record of ingreso with an error called caja is not open")
    void create_savedIngreso_Error_Caja_is_not_open() throws NotFoundException {

        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils,createAnulacionUtils);

        Mockito
                .when(validateStatusCajaUtils.validateCajaAlreadyOpen(Mockito.any(BigInteger.class)))
                .thenReturn(false);

        Mockito
                .when(aperturaCajaPort.findById(Mockito.any(Long.class)))
                .thenReturn(aperturaCaja);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> useCase.create(domain))
                .withMessage(IngresoErrorCodes.ERROR_INGRESO.getLocalizedMessage());

    }

    @Test
    @DisplayName("Test for updating the field called motivoAnulacion with success")
    void update_cancellationReason_Success() throws Exception {
        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils,createAnulacionUtils);

        Mockito
                .when(ingresoPort.findById(Mockito.any(Integer.class)))
                .thenReturn(domain);
        Mockito
                .when(parametrizacionConceptoPort.findById(Mockito.any(Long.class)))
                .thenReturn(parametrizacionConcepto);

       useCase.updateCancellationReason(domain.getId(),cancellationReason);

       Mockito.verify(ingresoPort, Mockito.times(1)).updateCancellationReason(domain.getId(), cancellationReason);

    }

    @Test
    @DisplayName("Test for updating the field called motivoAnulacion with an error called id not found")
    void update_cancellationReason_Error_Id_not_found() {
        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils,createAnulacionUtils);

        Mockito
                .when(ingresoPort.findById(Mockito.any(Integer.class)))
                .thenReturn(null);

        assertThrows(IngresoIdNotFoundException.class, () -> useCase.updateCancellationReason(domain.getId(), cancellationReason));

    }
    @Test
    @DisplayName("Test for updating the field called motivoAnulacion with an error called parametrizacionConcepto not found")
    void update_cancellationReason_Error_parametrizacion_concepto_not_found() {
        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils,createAnulacionUtils);

        Mockito
                .when(ingresoPort.findById(Mockito.any(Integer.class)))
                .thenReturn(domain);

        assertThrows(ParametrizacionConceptoNotFoundException.class, () -> useCase.updateCancellationReason(domain.getId(), cancellationReason));

    }

    @Test
    @DisplayName("Test for updating the field called motivoAnulacion with an error called not nullable")
    void update_cancellationReason_Error_not_nullable() {
        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils, createAnulacionUtils);

        Mockito
                .when(ingresoPort.findById(Mockito.any(Integer.class)))
                .thenReturn(domain);

        parametrizacionConcepto.setAnulable(false);

        Mockito
                .when(parametrizacionConceptoPort.findById(Mockito.any(Long.class)))
                .thenReturn(parametrizacionConcepto);

        assertThrows(IngresoBusinessException.class, () -> useCase.updateCancellationReason(domain.getId(), cancellationReason));

    }

    @Test
    @DisplayName("Test for updating the field called motivoAnulacion with an error called cancellation reason is null or blank")
    void update_cancellationReason_Error_cancellation_reason_is_null_or_blank() {
        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils, createAnulacionUtils);

        Mockito
                .when(ingresoPort.findById(Mockito.any(Integer.class)))
                .thenReturn(domain);
        Mockito
                .when(parametrizacionConceptoPort.findById(Mockito.any(Long.class)))
                .thenReturn(parametrizacionConcepto);

        assertThrows(IngresoBusinessException.class, () -> useCase.updateCancellationReason(domain.getId(), ""));

    }

    @Test
    @DisplayName("Test to generate the voucher of ingreso method with success")
    void generateVoucher_success() throws JRException {
        IngresoUseCaseImpl useCase = new IngresoUseCaseImpl(ingresoPort, aperturaCajaPort,parametrizacionConceptoPort, validateStatusCajaUtils, createAnulacionUtils);

        var response = useCase.generateVoucher(ingresoVoucher);

        Assertions.assertNotNull(response);
    }
}
