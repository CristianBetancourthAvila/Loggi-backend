package com.pagatodo.financieracontable.application.usecases.commons;

import com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionBusinessException;
import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.ingreso.IngresoIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.Anulacion;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.infrastructure.ports.out.anulacion.AnulacionPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja.EgresoCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.ingreso.IngresoPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class CreateAnulacionUtilsTest {

    @Mock
    private AnulacionPort anulacionPort;

    @Mock
    private IngresoPort ingresoPort;

    @Mock
    private EgresoCajaPort egresoCajaPort;

    private static Integer id = 1;

    private static Ingreso ingreso;
    private static EgresoCaja egresoCaja;

    private static Anulacion domain;

    @BeforeEach
    public void configInitial() {
        ingreso = new Ingreso();
        ingreso.setId(1);

        egresoCaja = new EgresoCaja();
        egresoCaja.setId(2);

        domain = new Anulacion();
        domain.setId(id);
        domain.setEstado(EstadoAnulacion.PENDIENTE);
        domain.setAutorizadorId(null);
        domain.setFechaCreacion(LocalDate.now());
    }


    @Test
    @DisplayName("Test for creating a new record of anulacion with success when is an ingreso")
    void create_savedAnulacion_Success_when_is_an_ingreso() throws Exception {
        CreateAnulacionUtils utilClass = new CreateAnulacionUtils(anulacionPort, ingresoPort, egresoCajaPort);

        Mockito
                .when(ingresoPort.findById(Mockito.any(Integer.class)))
                .thenReturn(ingreso);
        domain.setIngreso(ingreso);
        domain.setTipoMovimiento(TipoMovimiento.INGRESO);
        utilClass.create(domain.getIngreso().getId(), domain.getTipoMovimiento());
        Mockito.verify(anulacionPort, Mockito.times(1)).create(Mockito.any(Anulacion.class));

    }

    @Test
    @DisplayName("Test for creating a new record of anulacion with success when is an egreso")
    void create_savedAnulacion_Success_when_is_an_egreso() throws Exception {
        CreateAnulacionUtils utilClass = new CreateAnulacionUtils(anulacionPort, ingresoPort, egresoCajaPort);

        Mockito
                .when(egresoCajaPort.findById(Mockito.any(Integer.class)))
                .thenReturn(egresoCaja);
        domain.setEgresoCaja(egresoCaja);
        domain.setTipoMovimiento(TipoMovimiento.EGRESO);
        utilClass.create(domain.getEgresoCaja().getId(), domain.getTipoMovimiento());
        Mockito.verify(anulacionPort, Mockito.times(1)).create(Mockito.any(Anulacion.class));

    }

    @Test
    @DisplayName("Test for creating a new record of anulacion with an error called egreso not found")
    void create_savedAnulacion_Error_egreso_not_found() throws Exception {
        CreateAnulacionUtils utilClass = new CreateAnulacionUtils(anulacionPort, ingresoPort, egresoCajaPort);

        domain.setEgresoCaja(egresoCaja);
        domain.setTipoMovimiento(TipoMovimiento.EGRESO);
        assertThrows(EgresoCajaNotFoundException.class, () -> utilClass.create(domain.getEgresoCaja().getId(), domain.getTipoMovimiento()));

    }

    @Test
    @DisplayName("Test for creating a new record of anulacion with an error called ingreso not found")
    void create_savedAnulacion_Error_ingreso_not_found() throws Exception {
        CreateAnulacionUtils utilClass = new CreateAnulacionUtils(anulacionPort, ingresoPort, egresoCajaPort);

        domain.setIngreso(ingreso);
        domain.setTipoMovimiento(TipoMovimiento.INGRESO);
        assertThrows(IngresoIdNotFoundException.class, () -> utilClass.create(domain.getIngreso().getId(), domain.getTipoMovimiento()));

    }

    @Test
    @DisplayName("Test for creating a new record of anulacion with an error called at least ingreso or egreso when movement id is zero")
    void create_savedAnulacion_Error_At_least_ingreso_or_egreso_when_movement_id_is_zero() throws Exception {
        CreateAnulacionUtils utilClass = new CreateAnulacionUtils(anulacionPort, ingresoPort, egresoCajaPort);

        domain.setIngreso(ingreso);
        domain.setTipoMovimiento(TipoMovimiento.INGRESO);
        assertThrows(AnulacionBusinessException.class, () -> utilClass.create(0, domain.getTipoMovimiento()));

    }

    @Test
    @DisplayName("Test for creating a new record of anulacion with an error called at least ingreso or egreso when movement id is zero")
    void create_savedAnulacion_Error_At_least_ingreso_or_egreso_when_movement_id_is_null() throws Exception {
        CreateAnulacionUtils utilClass = new CreateAnulacionUtils(anulacionPort, ingresoPort, egresoCajaPort);

        domain.setIngreso(ingreso);
        domain.setTipoMovimiento(TipoMovimiento.INGRESO);
        assertThrows(AnulacionBusinessException.class, () -> utilClass.create(null, domain.getTipoMovimiento()));

    }
}
