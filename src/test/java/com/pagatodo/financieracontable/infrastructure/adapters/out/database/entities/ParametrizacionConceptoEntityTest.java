package com.pagatodo.financieracontable.infrastructure.adapters.out.database.entities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.pagatodo.financieracontable.domain.models.enums.EstadoAnulacion;
import com.pagatodo.financieracontable.domain.models.enums.TipoMovimiento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pagatodo.commons.junit.PropertyTester;
import com.pagatodo.financieracontable.domain.models.enums.Estado;

@ExtendWith(MockitoExtension.class)
class ParametrizacionConceptoEntityTest {
	
    @Test
    void testParametrizacionConceptoEntity() {
    	
    	ParametrizacionConceptoEntity model = new ParametrizacionConceptoEntity();
    	
    	model.setId(1L);
    	model.setCodigoConcepto(1L);
    	model.setNombreConcepto("CD01");
    	model.setTipoConcepto("tipo");
    	model.setProgramable(true);
    	model.setAnulable(false);
    	model.setImprimible(true);
    	model.setDescripcion("Ejemplo de descripción");
    	model.setFechaCreacion(LocalDate.now());
    	model.setHoraCreacion(LocalTime.now());
    	model.setEstado(Estado.ACTIVO); 

        assertDoesNotThrow(() -> PropertyTester.test(model));
    }
    
    @Test
    void testAllArgsConstructor() {
		AnulacionEntity anulacionEntity = new AnulacionEntity(1, TipoMovimiento.INGRESO,new IngresoEntity(),null, null, LocalDate.now(), LocalTime.now(), EstadoAnulacion.PENDIENTE);
		IngresoEntity ingresoEntity= new IngresoEntity(1,new ParametrizacionConceptoEntity(), new AperturaCajaEntity(), 1, 4, 25000L, "Prueba de observaciones", LocalDate.now(), LocalTime.now(), "motivo anulacion", anulacionEntity, new TrasladoEntity());
		List<IngresoEntity> ingresoEntities = new ArrayList<>();
		ingresoEntities.add(ingresoEntity);
    	ParametrizacionConceptoEntity model = new ParametrizacionConceptoEntity(1L,1L, "","",1l,1l,"",true, false, true, "Ejemplo de descripción", LocalDate.now(),LocalTime.now(), Estado.ACTIVO, ingresoEntities, List.of(new ProgramarPagoEntity()));

        Assertions.assertNotNull(model.toString());
    }
}
