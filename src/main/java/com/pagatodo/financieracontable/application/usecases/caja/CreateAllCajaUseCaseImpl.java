package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.CreateAllCajaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateAllCajaUseCaseImpl implements CreateAllCajaUseCase {

    private final CajaPort cajaPort;

    @Transactional
    @Override
    public List<Caja> saveAll(List<Caja> cajaList) {
        cajaList.forEach(caja -> {
            caja.setId(null);
            caja.setHoraCreacion(LocalTime.now());
            caja.setFechaCreacion(LocalDate.now());
        });
        return cajaPort.saveAll(cajaList);
    }
}
