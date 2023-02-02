package br.gov.mapa.ptd.resource.controller;


import br.gov.mapa.ptd.resource.dto.AvaliacaoDTO;
import br.gov.mapa.ptd.service.PtdService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value ="/api/avaliacao")
public class PtdController {
    private final PtdService service;

    public PtdController(PtdService service) {
        this.service = service;
    }

    @GetMapping("/{servico}")
    public String retornaUrlFormulario (@PathVariable String servico) throws JsonProcessingException {
        ResponseEntity<AvaliacaoDTO> result = service.retornaUrlAvaliacao(servico);


        return Objects.requireNonNull(result.getBody()).getLocationUrl() ;


    }

}
