package com.pagatodo.financieracontable.application.usecases.programarpago;

import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.in.programarpago.UpdatePPStatusUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePPStatusUseCaseImpl implements UpdatePPStatusUseCase {

    private final ProgramarPagoPort programarPagoPort;

    @Transactional
    @Override
    public void updateStatus(Integer idProgramarPago) throws ProgramarPagoNotFoundException {
        ProgramarPagoNotFoundException errorNotFound =  new ProgramarPagoNotFoundException();
        errorNotFound.addParams(idProgramarPago);
        ProgramarPago programarPagoSaved = programarPagoPort.findById(idProgramarPago);
        if (programarPagoSaved == null){throw errorNotFound;}
        Estado newStatus = (programarPagoSaved.getEstado() == Estado.ACTIVO) ? Estado.INACTIVO : Estado.ACTIVO;
        if (programarPagoSaved.getEstado() == Estado.INACTIVO && programarPagoSaved.getCondicion() == Condicion.PAGADO){
            newStatus = programarPagoSaved.getEstado();
        }
        programarPagoPort.updateStatus(idProgramarPago, newStatus);
    }
}
