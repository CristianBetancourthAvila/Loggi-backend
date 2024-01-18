package com.pagatodo.financieracontable.application.usecases.programarpago;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.caja.CajaNotFoundException;
import com.pagatodo.financieracontable.application.exceptions.programarpago.ProgramarPagoNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.ProgramarPagoCaja;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.in.programarpago.UpdatePPUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpagocaja.ProgramarPagoCajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdatePPUseCaseImpl implements UpdatePPUseCase {

    private final ProgramarPagoPort programarPagoPort;
    private final CajaPort cajaPort;
    private final ProgramarPagoCajaPort programarPagoCajaPort;

    @Transactional
    @Override
    public ProgramarPago update(ProgramarPago programarPago, List<BigInteger> cajaIds) throws ProgramarPagoNotFoundException, CajaIdNotFoundException, CajaNotFoundException {
        List<Caja> cajaList = cajaPort.findCajasByProgramarPagoid(programarPago.getId());
        ProgramarPago programarPagoSaved = programarPagoPort.findById(programarPago.getId());
        List<BigInteger> pendingCreate = new ArrayList<>();
        List<BigInteger> pendingDelete = new ArrayList<>();
        for (BigInteger cajaId : cajaIds) {
            boolean found = cajaList.stream()
                    .anyMatch(caja -> caja.getId().equals(cajaId));
            if (!found) {
                pendingCreate.add(cajaId);
            }
        }
        for (Caja caja : cajaList) {
            boolean found = cajaIds.contains(caja.getId());
            if (!found) {
                pendingDelete.add(caja.getId());
            }
        }
        saveProgramarPagoCajas(programarPagoSaved, pendingCreate);
        deleteProgramarPagoCajas(programarPago.getId(), pendingDelete);
        if (programarPago.getEstado() == Estado.INACTIVO && programarPago.getCondicion() == Condicion.PAGADO){
            programarPago.setEstado(null);
        }
        return programarPagoPort.update(programarPago);
    }

    private void saveProgramarPagoCajas(ProgramarPago programarPagoSaved, List<BigInteger> pendingCreate) throws CajaIdNotFoundException {
        List<ProgramarPagoCaja> programarPagoCajasToSave = new ArrayList<>();
        for (BigInteger cajaId : pendingCreate) {
            Caja caja = cajaPort.findById(BigInteger.valueOf(cajaId.longValue()));
            if (caja == null){
                CajaIdNotFoundException cajaIdNotFoundException = new CajaIdNotFoundException();
                cajaIdNotFoundException.addParams(cajaId);
                throw cajaIdNotFoundException;
            }
            ProgramarPagoCaja programarPagoCaja = new ProgramarPagoCaja();
            programarPagoCaja.setCaja(caja);
            programarPagoCajasToSave.add(programarPagoCaja);
        }
        programarPagoCajasToSave.forEach(programarPagoCaja -> {
            programarPagoCaja.setProgramarPago(programarPagoSaved);
            programarPagoCajaPort.save(programarPagoCaja);
        });
    }

    private void deleteProgramarPagoCajas(Integer programarPagoId, List<BigInteger> pendingDelete) {
        pendingDelete.forEach(cajaId -> {
            ProgramarPagoCaja programarPagoCaja = programarPagoCajaPort.findPPByProgramarPagoIdAndCajaId(programarPagoId, cajaId);
            programarPagoCajaPort.delete(programarPagoCaja);
        });
    }

}
