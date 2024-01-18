package com.pagatodo.financieracontable.application.usecases.commons;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionBusinessException;
import com.pagatodo.financieracontable.application.exceptions.anulacion.AnulacionErrorCodes;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Component
public class CreateAnulacionUtils {
    private static final String AT_LEAST_INGRESO_OR_EGRESO = AnulacionErrorCodes.AT_LEAST_INGRESO_OR_EGRESO.getLocalizedMessage();

    private final AnulacionPort anulacionPort;

    private final IngresoPort ingresoPort;

    private final EgresoCajaPort egresoCajaPort;

    public void create(Integer movementId, TipoMovimiento movementType) throws BusinessException, NotFoundException {
        VisitorValidator.of(movementId)
                        .and(AT_LEAST_INGRESO_OR_EGRESO, m-> (m!=null && m!=0))
                        .execute(AnulacionBusinessException::new);
        Anulacion anulacion = assignMovementValues(movementId, movementType);
        anulacion.setId(null);
        anulacion.setFechaCreacion(LocalDate.now());
        anulacion.setEstado(EstadoAnulacion.PENDIENTE);
        anulacion.setHoraCreacion(LocalTime.now());
        anulacion.setAutorizadorId(null);
        anulacionPort.create(anulacion);
    }

    private Anulacion assignMovementValues(Integer movementId, TipoMovimiento movementType) throws NotFoundException {
        Anulacion anulacion = new Anulacion();
        if(movementType.equals(TipoMovimiento.INGRESO)){
                Ingreso ingreso = ingresoPort.findById(movementId);
                if(ingreso == null){
                    throw new IngresoIdNotFoundException();
                }
                anulacion.setIngreso(ingreso);
        } else{
                EgresoCaja egresoCaja = egresoCajaPort.findById(movementId);
                if(egresoCaja == null){
                    throw new EgresoCajaNotFoundException();
                }
                anulacion.setEgresoCaja(egresoCaja);
        }
        anulacion.setTipoMovimiento(movementType);
        return anulacion;
    }
}
