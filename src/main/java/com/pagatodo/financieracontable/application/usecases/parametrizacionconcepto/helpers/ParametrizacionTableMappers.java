package com.pagatodo.financieracontable.application.usecases.parametrizacionconcepto.helpers;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.pagatodo.financieracontable.domain.models.ParametrizacionConcepto;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParametrizacionTableMappers {

	ParametrizacionConceptoTable toParametrizacionTable(ParametrizacionConcepto domain);

	default ParametrizacionConceptoTable buildParametrizacionTable(ParametrizacionConcepto source) {

		ParametrizacionConceptoTable destination = toParametrizacionTable(source);
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		List<String> list = new ArrayList<>();

		if (source.getProgramable() != null && source.getProgramable()) {
			list.add("Programable");
		}
		if (source.getAnulable() != null && source.getAnulable()) {
			list.add("Anulable");
		}
		if (source.getImprimible() != null && source.getImprimible()) {
			list.add("Imprimible");
		}

		String concatenated = list.stream().collect(Collectors.joining(", "));

		//TODO: pendiente obtener descripcionde id y cruce con servicio externo
		destination.setTipoConcepto(concatenated);
		destination.setEstadoRegistro(destination.getEstado() == Estado.ACTIVO ? "Activo" : "Inactivo");
		destination.setFechaGestion(source.getFechaCreacion().format(formato));
		destination.setTipologia(source.getTipologiaId()+"_");

		return destination;
	}

}
