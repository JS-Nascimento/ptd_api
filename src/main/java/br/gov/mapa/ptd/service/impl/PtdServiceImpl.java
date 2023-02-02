package br.gov.mapa.ptd.service.impl;

import br.gov.mapa.ptd.domain.Acompanhamento;
import br.gov.mapa.ptd.domain.Avaliacao;
import br.gov.mapa.ptd.resource.dto.AcompanhamentoDTO;
import br.gov.mapa.ptd.resource.dto.AvaliacaoDTO;
import br.gov.mapa.ptd.service.PtdService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PtdServiceImpl implements PtdService {

    private final ApiAvaliacaoServiceImpl apiAvaliacaoService;

    @Value("${ptd.etapa}")
    private String etapa;
    @Value(("${ptd.orgao}"))
    private String orgao;
    @Value("${ptd.canal.avaliacao}")
    private String canalAvaliacao;
    @Value(("${ptd.canal.prestacao}"))
    private String canalPrestacao;

    private String protocolo;

    private Acompanhamento acompanhamento ;
    private Avaliacao avaliacao;

    public PtdServiceImpl(ApiAvaliacaoServiceImpl apiAvaliacaoService) {
        this.apiAvaliacaoService = apiAvaliacaoService;
    }

    @Override
    public ResponseEntity<AvaliacaoDTO> retornaUrlAvaliacao(String servico) throws JsonProcessingException {

        protocolo = registraAcompanhamento(servico);
        ResponseEntity<AvaliacaoDTO> url = null;

        if (protocolo != null){  url = buscaUrlFormulario(servico, protocolo); }

        return url;
    }

    private String registraAcompanhamento(String servico)  {

        acompanhamento = new Acompanhamento(servico,etapa,orgao);

        try {

            String payload = new ObjectMapper().writeValueAsString(acompanhamento);
            ResponseEntity<AcompanhamentoDTO> response = apiAvaliacaoService.registrarAcompanhamento(payload);

            System.out.println(Objects.requireNonNull(response.getBody()));

            if (response.getStatusCodeValue() == 201) {  return acompanhamento.getProtocolo(); }


        } catch (JsonProcessingException e){

            //implementar HandlerException
            e.printStackTrace();

        }
        return null;
    }

    private ResponseEntity<AvaliacaoDTO> buscaUrlFormulario (String servico, String protocolo ) throws JsonProcessingException {
        avaliacao = new Avaliacao(servico, etapa, orgao, canalAvaliacao, canalPrestacao,protocolo);
        String payload = new ObjectMapper().writeValueAsString(avaliacao);

        ResponseEntity<AvaliacaoDTO> response = apiAvaliacaoService.buscaUrlFormulario(payload);

        if(response.getStatusCodeValue()==200){
            return response;
        }
            return null;
    }
}
