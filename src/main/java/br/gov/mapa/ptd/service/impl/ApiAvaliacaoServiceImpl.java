package br.gov.mapa.ptd.service.impl;


import br.gov.mapa.ptd.resource.dto.AcompanhamentoDTO;
import br.gov.mapa.ptd.resource.dto.AvaliacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import  static br.gov.mapa.ptd.utils.Translations.*;

@Service
public class ApiAvaliacaoServiceImpl {
    @Value("${api.usuario}")
    private String usuario;

    @Value("${api.senha}")
    private String senha;

    @Value("${url.acompanhamento}")
    private String url_acompanhamento;

    @Value("${url.avaliacao}")
    private String url_avaliacao;



    private final RestTemplate restTemplate;

    @Autowired
    public ApiAvaliacaoServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public ResponseEntity<AcompanhamentoDTO> registrarAcompanhamento(String payload) {

        HttpEntity<String> entity = new HttpEntity<>(payload,montaHeader());
        return restTemplate.postForEntity(url_acompanhamento, entity,AcompanhamentoDTO.class);

    }

    private HttpHeaders montaHeader(){

        String credencial ="Basic " + encode(usuario + ":" + senha);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", credencial);
        headers.set("Accept", "application/json");
        headers.set("Accept-Language","pt-br");
        headers.set("Content-Type","application/json;charset=UTF-8");
        return headers;

    }

    public ResponseEntity<AvaliacaoDTO> buscaUrlFormulario(String payload) {

        HttpEntity<String> entity = new HttpEntity<>(payload,montaHeader());
        return restTemplate.postForEntity(url_avaliacao, entity,AvaliacaoDTO.class);

    }
}
