package com.pagatodo.financieracontable.infrastructure.adapters.out.database.implementation.cartera;

import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraNotFoundException;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities.CarteraEntity;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.mappers.cartera.CarteraMapper;
import com.pagatodo.financieracontable.infrastructure.adapters.out.database.repository.CarteraRepository;
import com.pagatodo.financieracontable.infrastructure.ports.out.cartera.CarteraPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarteraAdapter implements CarteraPort {

    private final CarteraMapper carteraMapper;

    private final CarteraRepository carteraRepository;
    @Override
    public Cartera save(Cartera cartera) {
        CarteraEntity carteraEntity = carteraMapper.domainToEntity(cartera);
        return carteraMapper.entityToDomain(carteraRepository.save(carteraEntity));
    }

    @Override
    public Cartera findCarteraByVendedorId(Integer sellerId){
        CarteraEntity result = carteraRepository.findCarteraByVendedorId(sellerId);
        return carteraMapper.entityToDomain(result);
    }

    @Override
    public Cartera update(Cartera cartera) throws NotFoundException{
        CarteraNotFoundException errorNotFound = new CarteraNotFoundException();
        errorNotFound.addParams(cartera.getId());
        CarteraEntity target = carteraRepository.findById(cartera.getId()).orElseThrow(()-> errorNotFound);
        carteraMapper.mergeToEntity(target, cartera);
        return carteraMapper.entityToDomain(carteraRepository.save(target));
    }

    @Override
    public Cartera findById(Integer id) {
        return carteraMapper.entityToDomain(carteraRepository.findById(id).orElse(null));
    }
}
