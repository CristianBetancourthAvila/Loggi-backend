package com.pagatodo.financieracontable.application.usecases.anulacion;

import com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionDataNotFounException;
import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.domain.models.filter.AnulacionFilter;
import com.pagatodo.financieracontable.domain.models.vouchers.AnulacionVoucher;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.out.anulacion.AnulacionPort;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AnulacionUseCajaImplTest {

    @Mock
    private AnulacionPort anulacionPort;

    private static Integer id = 1;

    private static EgresoCaja egresoCaja;

    private static AnulacionFilter filter;

    private static Anulacion domain;

    private static AnulacionVoucher anulacionVoucher;

    @BeforeEach
    public void configInitial() {
        egresoCaja = new EgresoCaja();
        egresoCaja.setId(2);

        domain = new Anulacion();
        domain.setId(id);
        domain.setEgresoCaja(egresoCaja);
        domain.setEstado(EstadoAnulacion.PENDIENTE);
        domain.setAutorizadorId(null);
        domain.setFechaCreacion(LocalDate.now());
        domain.setHoraCreacion(LocalTime.now());

        filter = new AnulacionFilter();
        filter.setTipoMovimiento(TipoMovimiento.INGRESO);
        filter.setEstado(EstadoAnulacion.PENDIENTE);
        filter.setFechaCreacion(LocalDate.now());

        anulacionVoucher = new AnulacionVoucher();
        anulacionVoucher.setConcepto("255655-Transacción");
        anulacionVoucher.setComprobante(524L);
        anulacionVoucher.setMotivo("Motivo");
        anulacionVoucher.setRecibido("LEONEL ANDRES HIGUITA");
        anulacionVoucher.setAutorizador("Jhoan Sebastian");
        anulacionVoucher.setIdentificacion("156756566");
        anulacionVoucher.setFechaSolicitud("11/12/2023");
        anulacionVoucher.setAutorizado("Duplicado");
        anulacionVoucher.setComprobanteMovimiento("4251");
        anulacionVoucher.setValor("100.000.000");
        anulacionVoucher.setFechaHora("25/08/2024 10:22:00 AM");
        anulacionVoucher.setTipoDocumento("CC");
        anulacionVoucher.setNumeroDocumento("115689822");
    }

    @Test
    @DisplayName("Test for findAnulacionesByCriteria method")
    void findAnulacionesByCriteria_Success(){
        AnulacionUseCaseImpl useCase = new AnulacionUseCaseImpl(anulacionPort);
        PageModel<List<Anulacion>> page = new PageModel<>(List.of(domain),BigInteger.valueOf(1L));

        Mockito
                .when(anulacionPort.findAnulacionesByCriteria(Mockito.any(TipoMovimiento.class),
                                                              Mockito.any(LocalDate.class),
                                                              Mockito.any(EstadoAnulacion.class),
                                                              Mockito.any(Integer.class),
                                                              Mockito.any(Integer.class)))
                .thenReturn(page);
        var result = useCase.findAnulacionesByCriteria(filter, 10,0);
        assertEquals(page, result);
    }

    @Test
    @DisplayName("Test for updateAuthorizerUser method")
    void updateAuthorizerUser_Success() throws AnulacionDataNotFounException{
        AnulacionUseCaseImpl useCase = new AnulacionUseCaseImpl(anulacionPort);

        Mockito
                .when(anulacionPort.findById(Mockito.any(Integer.class)))
                .thenReturn(domain);

        useCase.updateAuthorizerUser(domain.getId(),1);

        Mockito.verify(anulacionPort,Mockito.times(1)).updateAuthorizerUser(Mockito.eq(domain.getId()), Mockito.eq(1),Mockito.isNotNull());
    }

    @Test
    @DisplayName("Test to generate the voucher of anulacion method with success")
    void generateVoucher_success() throws JRException {
        AnulacionUseCaseImpl useCase = new AnulacionUseCaseImpl(anulacionPort);

        var response = useCase.generateVoucher(anulacionVoucher);

        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Test for updateAuthorizerUser method when anulacion was not found")
    void updateAuthorizerUser_Error_anulacion_not_found(){
        AnulacionUseCaseImpl useCase = new AnulacionUseCaseImpl(anulacionPort);
        assertThrows(AnulacionDataNotFounException.class, () -> useCase.updateAuthorizerUser(domain.getId(), 1));
    }




}
