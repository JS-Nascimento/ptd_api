package br.gov.mapa.ptd.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonPropertyOrder({ "canalAvaliacao", "canalPrestacao" , "etapa" , "orgao" ,"protocolo" , "servico" })
@ToString
@EqualsAndHashCode
public class Avaliacao {

    private String canalAvaliacao;
    private String canalPrestacao;
    private String etapa;
    private String orgao;
    private String protocolo;
    private String servico;

    public Avaliacao(String servico, String etapa, String orgao, String canalAvaliacao, String canalPrestacao, String protocolo){

        this.servico = servico;
        this.etapa = etapa;
        this.orgao = orgao;
        this.canalAvaliacao = canalAvaliacao;
        this.canalPrestacao = canalPrestacao;
        this.protocolo = protocolo;

    }
    @JsonGetter("canalAvaliacao")
    public String getCanalAvaliacao() {
        return this.canalAvaliacao;
    }
    @JsonGetter("canalPrestacao")
    public String getCanalPrestacao() {
        return  this.canalPrestacao;
    }
    @JsonGetter("etapa")
    public String getEtapa() {
        return  this.etapa;
    }
    @JsonGetter("orgao")
    public String getOrgao() {
        return  this.orgao;
    }
    @JsonGetter("protocolo")
    public String getProtocolo() {
        return  this.protocolo;
    }
    @JsonGetter("servico")
    public String getServico() {
        return  this.servico;
    }

}
