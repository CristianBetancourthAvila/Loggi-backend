package com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api;

import com.pagatodo.financieracontable.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "estructura-comercial-client", url = "${url.estructura-comercial-client}")
public interface ECApiClient {

    @GetMapping("/terminal/filtrar")
    ResponseEntity<PageResponse<List<TerminalResponse>>> findByMac(@RequestParam(name = "rowsPerPage") Integer rowsPerPage, @RequestParam(name = "skip") Integer skip, @RequestParam(name = "companyId") Long companyId, @RequestParam(name = "idTeam") String idTeam);
}
