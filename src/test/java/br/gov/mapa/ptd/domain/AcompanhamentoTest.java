package br.gov.mapa.ptd.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@SpringBootTest
public class AcompanhamentoTest {
    @Value("${ptd.etapa}")
    private String etapa;
    @Value(("${ptd.orgao}"))
    private String orgao;

    @Test
    public void whenSerializingUsingJsonGetter_thenCorrect()
            throws JsonProcessingException {

       Acompanhamento acompanhamento = new Acompanhamento("9462",etapa, orgao );

        System.out.println(acompanhamento);
        String result = new ObjectMapper().writeValueAsString(acompanhamento);
        System.out.println(result);

        Assertions.assertTrue(result.contains(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        Assertions.assertTrue(result.contains("37524"));
        Assertions.assertTrue(result.contains("9462"));

    }
}
