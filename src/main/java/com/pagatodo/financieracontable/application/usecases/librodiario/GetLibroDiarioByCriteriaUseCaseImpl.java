package com.pagatodo.financieracontable.application.usecases.librodiario;

import com.pagatodo.commons.validation.VisitorValidator;
import com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioBusinessException;
import com.pagatodo.financieracontable.application.exceptions.librodiario.LibroDiarioErrorCodes;
import com.pagatodo.financieracontable.domain.models.EgresoCaja;
import com.pagatodo.financieracontable.domain.models.Ingreso;
import com.pagatodo.financieracontable.domain.models.LibroDiario;
import com.pagatodo.financieracontable.domain.models.filter.LibroDiarioFilter;
import com.pagatodo.financieracontable.domain.records.PageModel;
import com.pagatodo.financieracontable.infrastructure.ports.in.librodiario.GetLibroDiarioByCriteriaUseCase;
import com.pagatodo.financieracontable.infrastructure.ports.out.egresocaja.EgresoCajaPort;
import com.pagatodo.financieracontable.infrastructure.ports.out.ingreso.IngresoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GetLibroDiarioByCriteriaUseCaseImpl implements GetLibroDiarioByCriteriaUseCase {

    private static final String INVALID_DATES = LibroDiarioErrorCodes.INVALID_DATES.getLocalizedMessage();
    private static final String INVALID_FILTER = LibroDiarioErrorCodes.INVALID_FILTER.getLocalizedMessage();
    private final IngresoPort ingresoPort;
    private final EgresoCajaPort egresoCajaPort;

    @Transactional(readOnly = true)
    @Override
    public PageModel<List<LibroDiario>> findWithFiler(LibroDiarioFilter libroDiarioFilter, Integer rowsPerPage, Integer skip) throws LibroDiarioBusinessException {
        validateFilter(libroDiarioFilter);
        List<Ingreso> ingresos = obtenerIngresos(libroDiarioFilter);
        List<EgresoCaja> egresos = obtenerEgresos(libroDiarioFilter);
        Map<String, LibroDiario> libroDiarioMap = generarLibroDiario(ingresos, egresos, libroDiarioFilter.getIsCloseCaja());
        calcularSaldos(libroDiarioMap);
        boolean primerLibro = true;
        for (LibroDiario libroDiario : libroDiarioMap.values()) {
            fillAdditionalFields(libroDiario, libroDiarioFilter);
            if (primerLibro) {
                libroDiario.setQuantityUserDateOne(countDistinctUsersByDate(ingresos, egresos, libroDiarioFilter.getDateOne()));
                libroDiario.setQuantityUserDateTwo(countDistinctUsersByDate(ingresos, egresos, libroDiarioFilter.getDateTwo()));
                libroDiario.setQuantityCajaDateOne(countDistinctBoxesByDate(ingresos, egresos, libroDiarioFilter.getDateOne()));
                libroDiario.setQuantityCajaDateTwo(countDistinctBoxesByDate(ingresos, egresos, libroDiarioFilter.getDateTwo()));
                libroDiario.setQuantityTotalCaja(countDistinctBoxes(ingresos,egresos));
                libroDiario.setQuantityTotalUser(countDistinctUsers(ingresos,egresos));
                primerLibro = false;
            }
        }
        List<LibroDiario> libroDiarios = new ArrayList<>(libroDiarioMap.values());
        List<LibroDiario> paginatedResult = libroDiarios.subList(skip, Math.min(skip + rowsPerPage, libroDiarios.size()));
        return new PageModel<>(paginatedResult, BigInteger.valueOf(libroDiarios.size()));
    }

    private void validateFilter(LibroDiarioFilter libroDiarioFilter) throws LibroDiarioBusinessException {
        VisitorValidator.of(libroDiarioFilter)
                .and(INVALID_DATES, ld -> !ld.getIsAdmin() || ld.getDateTwo() != null)
                .and(INVALID_DATES, ld -> ld.getDateTwo() == null || ld.getDateTwo().isAfter(ld.getDateOne()))
                .and(INVALID_FILTER,ld -> !(ld.getIdCaja() != null && ld.getIdUsuario() != null))
                .and(INVALID_FILTER,ld -> !(ld.getIdCaja() == null && ld.getIdUsuario() == null))
                .execute(LibroDiarioBusinessException::new);
    }

    private int countDistinctUsers(List<Ingreso> ingresos, List<EgresoCaja> egresos) {
        return (int) Stream.concat(
                        ingresos.stream().map(ingreso -> ingreso.getAperturaCaja().getUsuarioId()),
                        egresos.stream().map(egreso -> egreso.getAperturaCaja().getUsuarioId())
                ).distinct().count();
    }

    private int countDistinctBoxes(List<Ingreso> ingresos, List<EgresoCaja> egresos) {
        return (int) Stream.concat(
                        ingresos.stream().map(ingreso -> ingreso.getAperturaCaja().getCaja().getId()),
                        egresos.stream().map(egreso -> egreso.getAperturaCaja().getCaja().getId())
                ).distinct().count();
    }

    private int countDistinctUsersByDate(List<Ingreso> ingresos, List<EgresoCaja> egresos, LocalDate date) {
        return (date == null) ? 0 :
                Stream.concat(
                        ingresos.stream()
                                .filter(ingreso -> ingreso.getFechaCreacion().isEqual(date))
                                .map(ingreso -> ingreso.getAperturaCaja().getUsuarioId()),
                        egresos.stream()
                                .filter(egreso -> egreso.getFechaCreacion().isEqual(date))
                                .map(egreso -> egreso.getAperturaCaja().getUsuarioId())
                ).collect(Collectors.toSet()).size();
    }

    private int countDistinctBoxesByDate(List<Ingreso> ingresos, List<EgresoCaja> egresos, LocalDate date) {
        return (date == null) ? 0 :
                Stream.concat(
                        ingresos.stream()
                                .filter(ingreso -> ingreso.getFechaCreacion().isEqual(date))
                                .map(ingreso -> ingreso.getAperturaCaja().getCaja().getId()),
                        egresos.stream()
                                .filter(egreso -> egreso.getFechaCreacion().isEqual(date))
                                .map(egreso -> egreso.getAperturaCaja().getCaja().getId())
                ).collect(Collectors.toSet()).size();
    }

    private void fillAdditionalFields(LibroDiario libroDiario, LibroDiarioFilter libroDiarioFilter) {
        BigInteger userId = obtainUserId(libroDiario, libroDiarioFilter);
        libroDiario.setUsuarioId(userId);
        String cajaName = obtainCajaName(libroDiario, libroDiarioFilter);
        libroDiario.setCajaName(cajaName);
        LocalDate dateSummary = obtainDateSummary(libroDiario, libroDiarioFilter);
        libroDiario.setDateSummary(dateSummary);
    }


    private BigInteger obtainUserId(LibroDiario libroDiario, LibroDiarioFilter libroDiarioFilter) {
        if (libroDiario.getEgresos() != null){
            return libroDiario.getEgresos().get(0).getAperturaCaja().getUsuarioId();
        }else if (libroDiario.getIngresos() != null){
            return libroDiario.getIngresos().get(0).getAperturaCaja().getUsuarioId();
        }else if (libroDiarioFilter.getIdUsuario() != null){
            return libroDiarioFilter.getIdUsuario();
        }
        return BigInteger.ZERO;
    }

    private String obtainCajaName(LibroDiario libroDiario, LibroDiarioFilter libroDiarioFilter) {
        if (libroDiario.getEgresos() != null){
            return libroDiario.getEgresos().get(0).getAperturaCaja().getCaja().getCodigoCaja()+ " - " +
                    libroDiario.getEgresos().get(0).getAperturaCaja().getCaja().getNombreCaja();
        }else if (libroDiario.getIngresos() != null){
            return libroDiario.getIngresos().get(0).getAperturaCaja().getCaja().getCodigoCaja()+ " - " +
                    libroDiario.getIngresos().get(0).getAperturaCaja().getCaja().getNombreCaja();
        }else if (libroDiarioFilter.getIdCaja() != null){
            return libroDiarioFilter.getIdCaja().toString();
        }
        return "";
    }

    private LocalDate obtainDateSummary(LibroDiario libroDiario, LibroDiarioFilter libroDiarioFilter) {
        if (libroDiario.getEgresos() != null){
            return libroDiario.getEgresos().get(0).getFechaCreacion();
        }else if (libroDiario.getIngresos() != null){
            return libroDiario.getIngresos().get(0).getFechaCreacion();
        }else if (libroDiarioFilter.getDateOne() != null){
            return libroDiarioFilter.getDateOne();
        }else if (libroDiarioFilter.getDateTwo() != null){
            return libroDiarioFilter.getDateTwo();
        }
        return LocalDate.now();
    }

    private List<Ingreso> obtenerIngresos(LibroDiarioFilter libroDiarioFilter) {
        if (Boolean.TRUE.equals(libroDiarioFilter.getIsAdmin())) {
            List<Ingreso> ingresosSaved = ingresoPort.findAllByDateBetweenAndLibroDiarioFilter(libroDiarioFilter);
            return ingresosSaved != null ? ingresosSaved : new ArrayList<>();
        } else {
            List<Ingreso> ingresos = ingresoPort.findIngresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateOne(), libroDiarioFilter.getIdUsuario(), libroDiarioFilter.getIdCaja());
            if (!libroDiarioFilter.getDateOne().equals(libroDiarioFilter.getDateTwo())) {
                List<Ingreso> ingresosFromDateTwo = ingresoPort.findIngresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateTwo(), libroDiarioFilter.getIdUsuario(), libroDiarioFilter.getIdCaja());
                if (ingresosFromDateTwo != null && !ingresosFromDateTwo.isEmpty()) {
                    ingresos.addAll(ingresosFromDateTwo);
                }
            }
            return ingresos;
        }
    }

    private List<EgresoCaja> obtenerEgresos(LibroDiarioFilter libroDiarioFilter) {
        if (Boolean.TRUE.equals(libroDiarioFilter.getIsAdmin())) {
            List<EgresoCaja> egresosSaved = egresoCajaPort.findAllByDateBetweenAndLibroDiarioFilter(libroDiarioFilter);
            return egresosSaved != null ? egresosSaved : new ArrayList<>();
        } else {
            List<EgresoCaja> egresos = egresoCajaPort.findEgresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateOne(), libroDiarioFilter.getIdUsuario(), libroDiarioFilter.getIdCaja());
            if (!libroDiarioFilter.getDateOne().equals(libroDiarioFilter.getDateTwo())) {
                List<EgresoCaja> egresosFromDateTwo = egresoCajaPort.findEgresosByDateAndUsuarioAndCaja(libroDiarioFilter.getDateTwo(), libroDiarioFilter.getIdUsuario(), libroDiarioFilter.getIdCaja());
                if (egresosFromDateTwo != null && !egresosFromDateTwo.isEmpty()) {
                    egresos.addAll(egresosFromDateTwo);
                }
            }
            return egresos;
        }
    }

    private Map<String, LibroDiario> generarLibroDiario(List<Ingreso> ingresos, List<EgresoCaja> egresos, Boolean isCloseCaja) {
        Map<String, LibroDiario> libroDiarioMap = new HashMap<>();
        ingresos.forEach(ingreso -> {
            String key = generarClave(ingreso, isCloseCaja);
            LibroDiario libroDiario = libroDiarioMap.getOrDefault(key, new LibroDiario());
            actualizarIngresos(libroDiario, ingreso);
            libroDiarioMap.put(key, libroDiario);
        });
        egresos.forEach(egreso -> {
            String key = generarClave(egreso, isCloseCaja);
            LibroDiario libroDiario = libroDiarioMap.getOrDefault(key, new LibroDiario());
            actualizarEgresos(libroDiario, egreso);
            libroDiarioMap.put(key, libroDiario);
        });
        return libroDiarioMap;
    }

    private void calcularSaldos(Map<String, LibroDiario> libroDiarioMap) {
        for (LibroDiario libroDiario : libroDiarioMap.values()) {
            List<EgresoCaja> egresos = libroDiario.getEgresos() != null ? libroDiario.getEgresos() : new ArrayList<>();
            long totalEgresos = egresos.stream().mapToLong(e -> e.getProgramarPago().getValor()).sum();
            libroDiario.setQuantityCashOuts(egresos.size());
            libroDiario.setTotalCashOuts(totalEgresos);
            List<Ingreso> ingresos = libroDiario.getIngresos() != null ? libroDiario.getIngresos() : new ArrayList<>();
            long totalIngresos = ingresos.stream().mapToLong(Ingreso::getValorRecibido).sum();
            libroDiario.setQuantityIncomes(ingresos.size());
            libroDiario.setTotalIncomes(totalIngresos);
            libroDiario.setDailyNet(totalIngresos - totalEgresos);
            libroDiario.setNewBalance(libroDiario.getPreviousBalance() + libroDiario.getDailyNet());
        }
    }

    private String generarClave(Ingreso ingreso, Boolean isCloseCaja) {
        return ingreso.getFechaCreacion() + "-" +
                ingreso.getAperturaCaja().getCaja().getId() +
                (!isCloseCaja ? "-" + ingreso.getAperturaCaja().getUsuarioId() : "");
    }

    private String generarClave(EgresoCaja egreso, Boolean isCloseCaja) {
        return egreso.getFechaCreacion() + "-" +
                egreso.getAperturaCaja().getCaja().getId() +
                (!isCloseCaja ? "-" + egreso.getAperturaCaja().getUsuarioId() : "");
    }

    private void actualizarIngresos(LibroDiario libroDiario, Ingreso ingreso) {
        if (libroDiario.getIngresos() == null) {
            libroDiario.setIngresos(new ArrayList<>());
        }
        libroDiario.getIngresos().add(ingreso);
        libroDiario.setPreviousBalance(ingreso.getAperturaCaja().getSaldoInicial());
    }

    private void actualizarEgresos(LibroDiario libroDiario, EgresoCaja egreso) {
        if (libroDiario.getEgresos() == null) {
            libroDiario.setEgresos(new ArrayList<>());
        }
        libroDiario.getEgresos().add(egreso);
        libroDiario.setPreviousBalance(egreso.getAperturaCaja().getSaldoInicial());
    }
}
