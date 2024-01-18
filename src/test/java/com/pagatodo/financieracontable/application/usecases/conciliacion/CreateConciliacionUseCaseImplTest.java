package com.pagatodo.financieracontable.application.usecases.conciliacion;

import com.pagatodo.financieracontable.application.exceptions.conciliacion.ConciliacionBusinessException;
import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.infrastructure.ports.out.conciliacion.ConciliacionPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateConciliacionUseCaseImplTest {

    @Mock
    private ConciliacionPort conciliacionPort;

    @InjectMocks
    private CreateConciliacionUseCaseImpl createConciliacionUseCase;

    private Conciliacion conciliacion;

    @BeforeEach
    void setUp() {
        createConciliacionUseCase = new CreateConciliacionUseCaseImpl(conciliacionPort);

        conciliacion = new Conciliacion();
        conciliacion.setId(1L);
        conciliacion.setFechaInicial(LocalDate.of(2023,5,1));
        conciliacion.setFechaFinal(LocalDate.of(2023,6,1));
        conciliacion.setFechaCreacion(LocalDate.now());
    }

    @Test
    @DisplayName("Create Conciliacion Successfully")
    void createConciliacion_Successfully() throws ConciliacionBusinessException {

        when(conciliacionPort.save(any(Conciliacion.class))).thenReturn(conciliacion);

        Conciliacion createdConciliacion = createConciliacionUseCase.create(conciliacion);

        assertNotNull(createdConciliacion);
        verify(conciliacionPort, times(1)).save(any(Conciliacion.class));
    }
}
