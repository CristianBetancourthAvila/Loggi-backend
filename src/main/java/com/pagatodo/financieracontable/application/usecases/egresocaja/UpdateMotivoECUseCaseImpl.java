package com.pagatodo.financieracontable.application.usecases.egresocaja;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaBusinessException;
import com.pagatodo.financieracontable.application.exceptions.egresocaja.EgresoCajaErrorCodes;
import com.pagatodo.financieracontable.application.usecases.commons.CreateAnulacionUtils;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import com.pagatodo.financieracontable.infrastructure.ports.in.egresocaja.UpdateMotivoECUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja.EgresoCajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateMotivoECUseCaseImpl implements UpdateMotivoECUseCase {

    private final EgresoCajaPort egresoCajaPort;
    private static final String MOTIVO_ANULACION_NOT_NULL = EgresoCajaErrorCodes.MOTIVO_ANULACION_NOT_NULL.getLocalizedMessage();
    private static final String ANULACION_NOT_VALID = EgresoCajaErrorCodes.ANULACION_NOT_VALID.getLocalizedMessage();

    private final CreateAnulacionUtils createAnulacionUtils;

    @Transactional
    @Override
    public void updateMotivo(Integer id, EgresoCaja egresoCaja) throws NotFoundException, BusinessException {
        EgresoCaja egresoCajaSaved = egresoCajaPort.findById(id);
        var motivoAnulacion = egresoCaja.getMotivoAnulacion();
        VisitorValidator.of(egresoCajaSaved)
                .and(MOTIVO_ANULACION_NOT_NULL, p ->
                                !(motivoAnulacion == null || motivoAnulacion.isBlank())
                )
                .and(ANULACION_NOT_VALID, p -> p.getProgramarPago().getParametrizacionConcepto().getAnulable())
                .execute(EgresoCajaBusinessException::new);
        egresoCajaPort.updateMotivo(id, motivoAnulacion);
        createAnulacionUtils.create(id, TipoMovimiento.EGRESO);
    }
}
