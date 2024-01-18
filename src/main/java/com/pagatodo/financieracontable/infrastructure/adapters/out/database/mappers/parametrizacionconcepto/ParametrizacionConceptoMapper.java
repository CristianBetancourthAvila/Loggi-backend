package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.parametrizacionconcepto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.ParametrizacionConceptoEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.projections.ConceptoProjection;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParametrizacionConceptoMapper {

    @Mapping(target = "programarPago.parametrizacionConcepto", ignore = true)
    @Mapping(target = "programarPago.egresoCaja.programarPago", ignore = true)
    @Mapping(target = "ingresoEntity.programacionConcepto", ignore = true)
    ParametrizacionConcepto entityToDomain(ParametrizacionConceptoEntity entity);
    List<ParametrizacionConcepto> entitiesToDomains(List<ParametrizacionConceptoEntity> entity);
    
    ParametrizacionConceptoEntity domainToEntity(ParametrizacionConcepto domain);
    List<ParametrizacionConceptoEntity> domainsToEntities(List<ParametrizacionConcepto> domain);  
    
    ParametrizacionConcepto conceptoToDomain(ConceptoProjection projection);
    List<ParametrizacionConcepto> conceptosToDomains(List<ConceptoProjection> projection);  
    
    void mergeToEntity(@MappingTarget ParametrizacionConceptoEntity target, ParametrizacionConcepto source);
}
