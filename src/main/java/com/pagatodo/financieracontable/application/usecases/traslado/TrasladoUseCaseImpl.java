package com.pagatodo.financieracontable.application.usecases.traslado;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.traslado.TrasladoBusinessException;
import com.pagatodo.financieracontable.application.exceptions.traslado.TrasladoErrorCodes;
import com.pagatodo.financieracontable.domain.models.Traslado;
import com.pagatodo.financieracontable.domain.models.enums.EstadoTraslado;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.in.traslado.TrasladoUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.traslado.TrasladoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrasladoUseCaseImpl implements TrasladoUseCase {
    private static final String FIELD_SERIE_PREMIO_REQUIRED = TrasladoErrorCodes.FIELD_SERIE_PREMIO_REQUIRED.getLocalizedMessage();

    private final TrasladoPort trasladoPort;
    @Transactional
    @Override
    public Traslado create(Traslado traslado) throws BusinessException {
        VisitorValidator.of(traslado)
                        .and(FIELD_SERIE_PREMIO_REQUIRED, this::validateSeriePremioField)
                        .execute(TrasladoBusinessException::new);
        traslado.setId(null);
        traslado.setHoraCreacion(LocalTime.now());
        traslado.setFechaCreacion(LocalDate.now());
        traslado.setEstado(EstadoTraslado.PROGRAMADO);
        return trasladoPort.create(traslado);
    }


    @Transactional(readOnly = true)
    @Override
    public PageModel<List<Traslado>> findTrasladosByDate(LocalDate date, Integer rowsPerPage, Integer skip) {
        return trasladoPort.findTrasladosByDate(date, rowsPerPage, skip);
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<List<Traslado>> findSendReceiveTrasladosByCaja(BigInteger cajaId, Boolean send, Integer rowsPerPage, Integer skip) {
        return trasladoPort.findSendReceiveTrasladosByCaja(cajaId,send, rowsPerPage, skip);
    }

    private boolean validateSeriePremioField(Traslado traslado){
        if(Boolean.TRUE.equals(traslado.getTrasladarPremio())){
            return traslado.getSeriePremio() != null && traslado.getSeriePremio() != 0;
        }else{
            return traslado.getSeriePremio() == null;
        }
    }
}
