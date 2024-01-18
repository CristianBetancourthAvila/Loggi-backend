package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.conciliacion;

import com.pagatodo.financieracontable.domain.models.Conciliacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoConciliacion;
import com.pagatodo.financieracontable.domain.models.filter.ConciliacionFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ConciliacionEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.conciliacion.ConciliacionMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.ConciliacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConciliacionAdapterTest {

    @Mock
    private ConciliacionRepository conciliacionRepository;

    @Mock
    private ConciliacionMapper conciliacionMapper;

    @InjectMocks
    private ConciliacionAdapter conciliacionAdapter;

    private static Conciliacion conciliacion;
    private static ConciliacionEntity conciliacionEntity;

    @BeforeEach
    void setUp() {
        conciliacionAdapter = new ConciliacionAdapter(conciliacionRepository, conciliacionMapper);

        conciliacion = new Conciliacion();
        conciliacion.setId(1L);
        conciliacion.setTipoConciliacion(TipoConciliacion.BANCARIA);
        conciliacion.setAliadoProducto("Aliado1");
        conciliacion.setFechaInicial(LocalDate.now());
        conciliacion.setFechaFinal(LocalDate.now().plusDays(7));
        conciliacion.setFechaCreacion(LocalDate.now());
        conciliacion.setArchivo("archivo_conciliacion.txt");

        conciliacionEntity = new ConciliacionEntity();
        conciliacionEntity.setId(1L);
        conciliacionEntity.setTipoConciliacion(TipoConciliacion.BANCARIA);
        conciliacionEntity.setAliadoProducto("Aliado1");
        conciliacionEntity.setFechaInicial(LocalDate.now());
        conciliacionEntity.setFechaFinal(LocalDate.now().plusDays(7));
        conciliacionEntity.setFechaCreacion(LocalDate.now());
        conciliacionEntity.setArchivo("archivo_conciliacion.txt");
    }

    @Test
    @DisplayName("Test for save method")
    void save_ConciliacionSaved_Success() {
        when(conciliacionMapper.domainToEntity(conciliacion)).thenReturn(conciliacionEntity);
        when(conciliacionRepository.save(any())).thenReturn(conciliacionEntity);
        when(conciliacionMapper.entityToDomain(conciliacionEntity)).thenReturn(conciliacion);

        Conciliacion savedConciliacion = conciliacionAdapter.save(conciliacion);

        assertEquals(conciliacion, savedConciliacion);
    }

    @Test
    @DisplayName("Test for findWithFilter method")
    void findWithFilter_ConciliacionList_Success() {
        ConciliacionFilter conciliacionFilter = new ConciliacionFilter();
        Integer rowsPerPage = 10;
        Integer skip = 0;

        List<ConciliacionEntity> conciliacionEntities = List.of(
                new ConciliacionEntity(),
                new ConciliacionEntity()
        );

        Page<ConciliacionEntity> page = new PageImpl<>(conciliacionEntities);
        when(conciliacionRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        when(conciliacionMapper.entitiesToDomains(conciliacionEntities)).thenReturn(List.of(
                new Conciliacion(),
                new Conciliacion()
        ));

        PageModel<List<Conciliacion>> result = conciliacionAdapter.findWithFilter(conciliacionFilter, rowsPerPage, skip);
        assertEquals(BigInteger.TWO, result.total());
    }
}
