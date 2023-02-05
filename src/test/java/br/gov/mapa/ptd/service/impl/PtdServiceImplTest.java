package br.gov.mapa.ptd.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import br.gov.mapa.ptd.domain.Acompanhamento;
import br.gov.mapa.ptd.domain.Avaliacao;
import br.gov.mapa.ptd.resource.dto.AcompanhamentoDTO;
import br.gov.mapa.ptd.resource.dto.AvaliacaoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class PtdServiceImplTest {

    @InjectMocks
    private PtdServiceImpl ptdService;

    @Mock
    private ApiAvaliacaoServiceImpl apiAvaliacaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retornaUrlAvaliacao_protocoloRegistradoComSucesso_retornaUrlFormulario() throws JsonProcessingException {
        // arrange
        String servico = "9462";
        String protocolo = "12345";

        Acompanhamento acompanhamento = new Acompanhamento(servico, "etapa", "orgao");
        String payloadAcompanhamento = new ObjectMapper().writeValueAsString(acompanhamento);
        ResponseEntity<AcompanhamentoDTO> responseAcompanhamento = ResponseEntity.status(201).build();
        when(apiAvaliacaoService.registrarAcompanhamento(payloadAcompanhamento)).thenReturn(responseAcompanhamento);

        Avaliacao avaliacao = new Avaliacao(servico, "etapa", "orgao", "canalAvaliacao", "canalPrestacao", protocolo);
        String payloadAvaliacao = new ObjectMapper().writeValueAsString(avaliacao);
        ResponseEntity<AvaliacaoDTO> responseAvaliacao = ResponseEntity.ok().build();
        when(apiAvaliacaoService.buscaUrlFormulario(payloadAvaliacao)).thenReturn(responseAvaliacao);

        // act
        ResponseEntity<AvaliacaoDTO> result = ptdService.retornaUrlAvaliacao(servico);

        // assert
        assertNotNull(result);
        verify(apiAvaliacaoService).registrarAcompanhamento(payloadAcompanhamento);
        verify(apiAvaliacaoService).buscaUrlFormulario(payloadAvaliacao);
    }

    @Test
    void retornaUrlAvaliacao_erroAoRegistrarAcompanhamento_retornaNull() throws JsonProcessingException {
        // arrange
        String servico = "teste";
        Acompanhamento acompanhamento = new Acompanhamento(servico, "etapa", "orgao");
        String payloadAcompanhamento = new ObjectMapper().writeValueAsString(acompanhamento);
        ResponseEntity<AcompanhamentoDTO> responseAcompanhamento = ResponseEntity.badRequest().build();
        when(apiAvaliacaoService.registrarAcompanhamento(anyString()))
                .thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));

        ResponseEntity<AvaliacaoDTO> result = ptdService.retornaUrlAvaliacao(servico);
        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());

        verify(apiAvaliacaoService, times(1)).registrarAcompanhamento(anyString());
        verify(apiAvaliacaoService, times(0)).buscaUrlFormulario(anyString());
    }

    @Test
    void retornaUrlAvaliacaoJsonProcessingExceptionTest() throws JsonProcessingException {
        String servico = "servico1";
        String protocolo = "protocolo1";
        String payload = "payload1";
        doThrow(new JsonProcessingException("Error") {
        }).when(apiAvaliacaoService).registrarAcompanhamento(payload);
        Acompanhamento acompanhamento = null;
        when(acompanhamento.getProtocolo()).thenReturn(protocolo);
        when(apiAvaliacaoService.buscaUrlFormulario(payload)).thenReturn(ResponseEntity.ok(new AvaliacaoDTO("url1")));

        ResponseEntity<AvaliacaoDTO> result = ptdService.retornaUrlAvaliacao(servico);

        assertNull(result);
    }
}