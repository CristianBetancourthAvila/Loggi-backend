package com.pagatodo.financieracontable.application.usecases.cartera;

import com.pagatodo.commons.exceptions.BusinessException;
import com.pagatodo.commons.exceptions.NotFoundException;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraErrorCodes;
import com.pagatodo.financieracontable.application.exceptions.cartera.CarteraNotFoundByVendedorIdException;
import com.pagatodo.financieracontable.domain.models.Cartera;
import com.pagatodo.financieracontable.infrastructure.ports.out.cartera.CarteraPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CarteraUseCaseImplTest {

    @Mock
    private CarteraPort carteraPort;

    @InjectMocks
    private CarteraUseCaseImpl carteraUseCase;

    private static Integer id = 1;

    private static Cartera domain;

    private static Integer sellerId = 4;

    @BeforeEach
    public void configInitial() {
        domain = new Cartera();
        domain.setSaldo(20000L);
        domain.setVendedorId(sellerId);
        domain.setId(id);
        domain.setVentaDiaLiquidada(20000L);
        domain.setVentasDia(10000L);
        domain.setHoraCreacion(LocalTime.now());
        domain.setFechaCreacion(LocalDate.now());
    }

    @Test
    @DisplayName("Test for create cartera method with success")
    void createCartera_success() throws BusinessException {

        Mockito
                .when(carteraPort.save(Mockito.any(Cartera.class)))
                .thenReturn(domain);

        var response = carteraUseCase.create(domain);

        assertAll("Save New Register of cartera Success Test",
                () -> assertNotNull(response),
                () -> assertNull(response.getId()));

    }
    @Test
    @DisplayName("Test for create cartera method with an error called balance less than zero")
    void createCartera_error_balance_less_than_zero() throws BusinessException {

        domain.setSaldo(0L);

        assertThatExceptionOfType(BusinessException.class)
                .isThrownBy(() -> carteraUseCase.create(domain))
                .withMessage(CarteraErrorCodes.INVALID_TRANSACTION.getLocalizedMessage());

    }

    @Test
    @DisplayName("Test for find cartera with id vendedor method with success")
    void findCarteraByVendedorId_success() throws NotFoundException {

        Mockito
                .when(carteraPort.findCarteraByVendedorId(Mockito.any(Integer.class)))
                .thenReturn(domain);

        var response = carteraUseCase.findCarteraByVendedorId(sellerId);

        assertAll("Find a register of cartera by seller id Success Test",
                () -> assertNotNull(response),
                () -> assertEquals(response.getVendedorId(), sellerId));

    }

    @Test
    @DisplayName("Test for find cartera by seller id with an error called not found cartera by seller id")
    void findCarteraByVendedorId_error_called_not_found_by_seller_id() throws NotFoundException {

        assertThatExceptionOfType(CarteraNotFoundByVendedorIdException.class)
                .isThrownBy(() -> carteraUseCase.findCarteraByVendedorId(sellerId))
                .withMessage(CarteraErrorCodes.CARTERA_NOT_FOUND_BY_VENDEDOR.getLocalizedMessage());

    }

    @Test
    @DisplayName("Test for update cartera method with success")
    void updateCartera_success() throws NotFoundException {

        Mockito
                .when(carteraPort.update(Mockito.any(Cartera.class)))
                .thenReturn(domain);

        var response = carteraUseCase.update(domain);

        assertAll("Update Register of cartera Success Test",
                () -> assertNotNull(response),
                () -> assertNotNull(response.getId()));

    }




}
