package br.gov.mapa.ptd.service;

import br.gov.mapa.ptd.resource.dto.AcompanhamentoDTO;
import br.gov.mapa.ptd.resource.dto.AvaliacaoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface PtdService {
    ResponseEntity<AvaliacaoDTO> retornaUrlAvaliacao(String servico) throws JsonProcessingException;
}
