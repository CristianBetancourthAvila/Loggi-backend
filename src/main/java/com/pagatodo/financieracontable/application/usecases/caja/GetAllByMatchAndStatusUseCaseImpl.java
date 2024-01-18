package com.pagatodo.financieracontable.application.usecases.caja;

import com.pagatodo.financieracontable.domain.models.Caja;
import com.pagatodo.financieracontable.domain.models.enums.Estado;
import com.pagatodo.financieracontable.infrastructure.ports.in.caja.GetAllByMatchAndStatusUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.caja.CajaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllByMatchAndStatusUseCaseImpl implements GetAllByMatchAndStatusUseCase {

    private final CajaPort cajaPort;

    @Transactional(readOnly = true)
    @Override
    public List<Caja> findAllByMatchesAndStatus(String filterText, Estado status) {
        if (filterText == null || filterText.replaceAll("\\s+", "").length() <= 3) {
            return cajaPort.findByTop10AndMatchesAndStatus(null, status);
        } else {
            return cajaPort.findAllByMatchesAndStatus(filterText.toUpperCase(), status);
        }
    }
}
