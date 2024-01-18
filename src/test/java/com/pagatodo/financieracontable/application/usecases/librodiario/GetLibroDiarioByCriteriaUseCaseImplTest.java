package com.pagatodo.financieracontable.application.usecases.librodiario;

import com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioBusinessException;
import com.pagatodo.financieracontable.domain.models.AperturaCaja;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.LibroDiario;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja.EgresoCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.ingreso.IngresoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetLibroDiarioByCriteriaUseCaseImplTest {

    @Mock
    private IngresoPort ingresoPort;

    @Mock
    private EgresoCajaPort egresoCajaPort;

    private GetLibroDiarioByCriteriaUseCaseImpl getLibroDiarioByCriteriaUseCaseImpl;

    @BeforeEach
    public void setUp() {
        getLibroDiarioByCriteriaUseCaseImpl = new GetLibroDiarioByCriteriaUseCaseImpl(ingresoPort,egresoCajaPort);
    }

    @Test
    @DisplayName("Test for finding libro diario with valid filter and isAdmin")
    void findLibroDiarioWithValidFilterIsAdmin() throws LibroDiarioBusinessException {
        LibroDiarioFilter libroDiarioFilter = new LibroDiarioFilter();
        libroDiarioFilter.setDateOne(LocalDate.of(2023, 1, 1));
        libroDiarioFilter.setDateTwo(LocalDate.of(2023, 1, 31));
        libroDiarioFilter.setIdUsuario(BigInteger.valueOf(100));
        libroDiarioFilter.setIsAdmin(true);
        libroDiarioFilter.setIsCloseCaja(false);
        AperturaCaja aperturaCaja = new AperturaCaja();
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        aperturaCaja.setCaja(caja);
        aperturaCaja.setSaldoInicial(1000L);
        Ingreso ingreso = new Ingreso();
        ingreso.setId(1);
        ingreso.setParametrizacionConcepto(new ParametrizacionConcepto());
        ingreso.setAperturaCaja(aperturaCaja);
        ingreso.setMedioPagoId(123);
        ingreso.setUsuarioTerceroId(456);
        ingreso.setValorRecibido(10000L);
        ingreso.setObservaciones("Ingreso por ventas");
        ingreso.setFechaCreacion(LocalDate.now());
        ingreso.setHoraCreacion(LocalTime.now());
        ingreso.setMotivoAnulacion(null);
        List<Ingreso> ingresos = new ArrayList<>();
        ingresos.add(ingreso);
        List<EgresoCaja> egresos = new ArrayList<>();
        when(ingresoPort.findAllByDateBetweenAndLibroDiarioFilter(libroDiarioFilter)).thenReturn(ingresos);
        when(egresoCajaPort.findAllByDateBetweenAndLibroDiarioFilter(libroDiarioFilter)).thenReturn(egresos);
        PageModel<List<LibroDiario>> result = getLibroDiarioByCriteriaUseCaseImpl.findWithFiler(libroDiarioFilter, 10, 0);
        assertEquals(1, result.data().size());
        verify(ingresoPort, times(1)).findAllByDateBetweenAndLibroDiarioFilter(libroDiarioFilter);
        verify(egresoCajaPort, times(1)).findAllByDateBetweenAndLibroDiarioFilter(libroDiarioFilter);
    }

    @Test
    @DisplayName("Test for finding libro diario with valid filter and is not Admin")
    void findLibroDiarioWithValidFilterNotAdmin() throws LibroDiarioBusinessException {
        LibroDiarioFilter libroDiarioFilter = new LibroDiarioFilter();
        libroDiarioFilter.setDateOne(LocalDate.of(2023, 1, 1));
        libroDiarioFilter.setDateTwo(LocalDate.of(2023, 1, 31));
        libroDiarioFilter.setIdUsuario(BigInteger.valueOf(100));
        libroDiarioFilter.setIsAdmin(false);
        libroDiarioFilter.setIsCloseCaja(true);
        AperturaCaja aperturaCaja = new AperturaCaja();
        Caja caja = new Caja();
        caja.setId(BigInteger.ONE);
        aperturaCaja.setCaja(caja);
        aperturaCaja.setSaldoInicial(1000L);
        ProgramarPago programarPago = new ProgramarPago();
        programarPago.setValor(1000L);
        Ingreso ingreso = new Ingreso();
        ingreso.setId(1);
        ingreso.setParametrizacionConcepto(new ParametrizacionConcepto());
        ingreso.setAperturaCaja(aperturaCaja);
        ingreso.setMedioPagoId(123);
        ingreso.setUsuarioTerceroId(456);
        ingreso.setValorRecibido(10000L);
        ingreso.setObservaciones("Ingreso por ventas");
        ingreso.setFechaCreacion(LocalDate.now());
        ingreso.setHoraCreacion(LocalTime.now());
        ingreso.setMotivoAnulacion(null);
        EgresoCaja egreso = new EgresoCaja();
        egreso.setId(101);
        egreso.setUsuarioTerceroId(BigInteger.ONE);
        egreso.setUsuarioId(999);
        egreso.setProgramarPago(programarPago);
        egreso.setMotivoAnulacion(null);
        egreso.setFechaCreacion(LocalDate.now());
        egreso.setHoraCreacion(LocalTime.now());
        egreso.setAperturaCaja(aperturaCaja);
        List<Ingreso> ingresos = new ArrayList<>();
        List<EgresoCaja> egresos = new ArrayList<>();
        ingresos.add(ingreso);
        egresos.add(egreso);
        when(ingresoPort.findIngresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateOne(),libroDiarioFilter.getIdUsuario(),libroDiarioFilter.getIdCaja())).thenReturn(ingresos);
        when(ingresoPort.findIngresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateTwo(),libroDiarioFilter.getIdUsuario(),libroDiarioFilter.getIdCaja())).thenReturn(ingresos);
        when(egresoCajaPort.findEgresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateOne(),libroDiarioFilter.getIdUsuario(),libroDiarioFilter.getIdCaja())).thenReturn(egresos);
        when(egresoCajaPort.findEgresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateTwo(),libroDiarioFilter.getIdUsuario(),libroDiarioFilter.getIdCaja())).thenReturn(egresos);
        PageModel<List<LibroDiario>> result = getLibroDiarioByCriteriaUseCaseImpl.findWithFiler(libroDiarioFilter, 10, 0);
        assertEquals(1, result.data().size());
        verify(ingresoPort, times(1)).findIngresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateOne(),libroDiarioFilter.getIdUsuario(),libroDiarioFilter.getIdCaja());
        verify(egresoCajaPort, times(1)).findEgresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateTwo(),libroDiarioFilter.getIdUsuario(),libroDiarioFilter.getIdCaja());
    }
}
