package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CajaEntityTest {
    @Test
    @DisplayName("Test CajaEntity")
    void testModelCajaEntity() {List<IngresoEntity> ingresoEntities = new ArrayList<>();
        List<RecaudoCarteraEntity> recaudoCarteraEntities = new ArrayList<>();
        recaudoCarteraEntities.add(new RecaudoCarteraEntity());
        AnulacionEntity anulacionEntity = new AnulacionEntity(1, TipoMovimiento.INGRESO,new IngresoEntity(),null, null, LocalDate.now(), LocalTime.now(), EstadoAnulacion.PENDIENTE);
        ingresoEntities.add(new IngresoEntity(1,new ParametrizacionConceptoEntity(), new AperturaCajaEntity(), 1, 4, 25000L, "Prueba de observaciones", LocalDate.now(), LocalTime.now(), "motivo anulacion", anulacionEntity, new TrasladoEntity()));
        List<CerrarCajaEntity> cerrarCajaEntities = new ArrayList<>();
        cerrarCajaEntities.add(new CerrarCajaEntity());
        List<AperturaCajaEntity> aperturaCajaEntities = new ArrayList<>();
        aperturaCajaEntities.add(new AperturaCajaEntity(1L, 100L, new CajaEntity(), true,
                LocalDate.now(), LocalTime.of(9, 0), 1000L, 1000L, 500L, 500L, 0L, 0L, "Prueba de observaciones", ingresoEntities, List.of(new EgresoCajaEntity()), cerrarCajaEntities, recaudoCarteraEntities));

        CajaEntity model = new CajaEntity();
        model.setId(BigInteger.ONE);
        model.setPuntoVentaTerminalId(1L);
        model.setCodigoCaja("CodigoCaja123");
        model.setNombreCaja("Caja de Prueba");
        model.setCodigoDane(12345L);
        model.setFechaCreacion(LocalDate.now());
        model.setHoraCreacion(LocalTime.of(8, 0));
        model.setMontoMaximoPago(10000L);
        model.setMontoMaximoGiro(5000L);
        model.setMontoMaximoBeps(2000L);
        model.setMontoMaximoBbva(3000L);
        model.setCantidadPapelBond(15L);
        model.setCantidadPapelTermico(15L);
        model.setEstado(Estado.ACTIVO);
        model.setAperturaCajaEntities(aperturaCajaEntities);

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }

    @Test
    @DisplayName("Test CajaEntity AllArgsConstructor")
    void testModelCajaEntityAllArgsConstructor() {
        List<TrasladoEntity> cajaOrigenTrasladoEntities = new ArrayList<>();
        List<TrasladoEntity> cajaDestinoTrasladoEntities = new ArrayList<>();
        List<AperturaCajaEntity> aperturaCajaEntities = new ArrayList<>();
        List<IngresoEntity> ingresoEntities = new ArrayList<>();
        List<CerrarCajaEntity> cerrarCajaEntities = new ArrayList<>();
        List<RecaudoCarteraEntity> recaudoCarteraEntities = new ArrayList<>();
        cerrarCajaEntities.add(new CerrarCajaEntity());
        cajaDestinoTrasladoEntities.add(new TrasladoEntity());
        cajaOrigenTrasladoEntities.add(new TrasladoEntity());
        AnulacionEntity anulacionEntity = new AnulacionEntity(1, TipoMovimiento.INGRESO,new IngresoEntity(),null, null, LocalDate.now(), LocalTime.now(), EstadoAnulacion.PENDIENTE);
        ingresoEntities.add(new IngresoEntity(1,new ParametrizacionConceptoEntity(), new AperturaCajaEntity(), 1, 4, 25000L, "Prueba de observaciones", LocalDate.now(), LocalTime.now(), "motivo anulacion", anulacionEntity, new TrasladoEntity()));
        aperturaCajaEntities.add(new AperturaCajaEntity(1L, 100L, new CajaEntity(), true,
                LocalDate.now(), LocalTime.of(9, 0), 1000L, 1000L, 500L, 500L, 0L, 0L, "Prueba de observaciones", ingresoEntities, List.of(new EgresoCajaEntity()), cerrarCajaEntities, recaudoCarteraEntities));
        CajaEntity model = new CajaEntity(BigInteger.ONE, 1L, "CodigoCaja123", "Caja de Prueba", 12345L,
                LocalDate.now(), LocalTime.of(8, 0), 10000L, 5000L, 2000L, 3000L, 15L, 15L, Estado.ACTIVO, aperturaCajaEntities, cajaOrigenTrasladoEntities, cajaDestinoTrasladoEntities);
        Assertions.assertNotNull(model.toString());
    }
}
