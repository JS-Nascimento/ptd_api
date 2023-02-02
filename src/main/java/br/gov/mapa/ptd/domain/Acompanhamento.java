package br.gov.mapa.ptd.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@JsonPropertyOrder({ "dataEtapa", "dataSituacaoEtapa" , "etapa" , "orgao" ,"protocolo" , "servico" , "situacaoEtapa"})
@ToString
@EqualsAndHashCode
public class Acompanhamento {

    private String dataEtapa;
    private String dataSituacaoEtapa;
    private String etapa;
    private String orgao;
    private String protocolo;
    private String servico;
    private String situacaoEtapa;

    public Acompanhamento(String servico, String etapa, String orgao){

        this.servico = servico;
        this.etapa = etapa;
        this.orgao = orgao;
        this.situacaoEtapa = "Não implementado";
        this.dataEtapa = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.dataSituacaoEtapa = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.protocolo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss"));

    }
    @JsonGetter("dataEtapa")
    public String getDataEtapa() {
        return this.dataEtapa;
    }
    @JsonGetter("dataSituacaoEtapa")
    public String getDataSituacaoEtapa() {
        return  this.dataSituacaoEtapa;
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
    @JsonGetter("situacaoEtapa")
    public String getSituacaoEtapa() {
        return  this.situacaoEtapa;
    }
}
