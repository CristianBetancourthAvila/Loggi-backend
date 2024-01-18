package com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api;

import com.pagatodo.financieracontable.infrastructure.adapters.out.rest.api.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "administrar-vendedores-client", url = "${url.administrar-vendedores-client}")
public interface AVV1ApiClient {
    @GetMapping("/datos-personales/terminales/vendedor")
    ResponseEntity<List<TerminalResponse>> findByIdSeller(@RequestParam(name = "idSeller") Long idSeller);

    @GetMapping("/datos-personales/terminales/usuario")
    ResponseEntity<List<TerminalResponse>> findByIdUser(@RequestParam(name = "userId") Long userId);
}
