package com.pagatodo.financieracontable.application.usecases.programarpago;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.pagatodo.financieracontable.application.exceptions.caja.CajaIdNotFoundException;
import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Condicion;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.ProgramarPago;
import com.pagatodo.financieracontable.domain.models.ProgramarPagoCaja;
import com.pagatodo.financieracontable.infrastructure.ports.in.programarpago.CreatePPUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.parametrizacionconcepto.ParametrizacionConceptoPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpago.ProgramarPagoPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.programarpagocaja.ProgramarPagoCajaPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePPUseCaseImpl implements CreatePPUseCase {

    private final ProgramarPagoPort programarPagoPort;
    private final CajaPort cajaPort;
    private final ProgramarPagoCajaPort programarPagoCajaPort;
    private final ParametrizacionConceptoPort parametrizacionConceptoPort;

    @Transactional
    @Override
    public ProgramarPago create(ProgramarPago programarPago, List<BigInteger> cajaIds) throws CajaIdNotFoundException {
        ParametrizacionConcepto parametrizacionConcepto = parametrizacionConceptoPort.findById(programarPago.getParametrizacionConcepto().getId());
        programarPago.setParametrizacionConcepto(parametrizacionConcepto);
        programarPago.setFechaCreacion(LocalDate.now());
        programarPago.setHoraCreacion(LocalTime.now());
        programarPago.setCondicion(Condicion.NO_PAGADO);
        List<ProgramarPagoCaja> programarPagoCajasToSave = new ArrayList<>();
        for (BigInteger cajaId : cajaIds) {
            Caja caja = cajaPort.findById(BigInteger.valueOf(cajaId.longValue()));
            if (caja == null){
                CajaIdNotFoundException cajaNotFoundException = new CajaIdNotFoundException();
                cajaNotFoundException.addParams(cajaId);
                throw cajaNotFoundException;
            }
            ProgramarPagoCaja programarPagoCaja = new ProgramarPagoCaja();
            programarPagoCaja.setCaja(caja);
            programarPagoCajasToSave.add(programarPagoCaja);
        }
        ProgramarPago programarSaved = programarPagoPort.save(programarPago);
        programarPagoCajasToSave.forEach(programarPagoCaja -> {
            programarPagoCaja.setProgramarPago(programarSaved);
            programarPagoCajaPort.save(programarPagoCaja);
        });
        return programarSaved;
    }
}
