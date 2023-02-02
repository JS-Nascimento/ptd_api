package br.gov.mapa.ptd.resource.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcompanhamentoDTO {

    @JsonProperty("message")
    private String messageApi;

    @JsonProperty("status")
    private String statusApi;


}
