package com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.cartera;

import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CarteraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarteraMapper {
    CarteraEntity domainToEntity(Cartera domain);
    Cartera entityToDomain(CarteraEntity entity);
    @Mapping(target = "ventasDia", expression = "java(target.getVentasDia() + source.getVentasDia() >= 0 ? target.getVentasDia() + source.getVentasDia(): 0)")
    @Mapping(target = "ventaDiaLiquidada", expression = "java(target.getVentaDiaLiquidada() + source.getVentaDiaLiquidada() >= 0 ? target.getVentaDiaLiquidada() + source.getVentaDiaLiquidada(): 0)")
    @Mapping(target = "saldo", expression = "java(target.getSaldo() + source.getSaldo() >= 0 ? target.getSaldo() + source.getSaldo(): 0 )")
    void mergeToEntity(@MappingTarget CarteraEntity target, Cartera source);
}
