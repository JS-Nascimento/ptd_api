package br.gov.mapa.ptd.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.gov.mapa.ptd.resource.controller.PtdController;
import br.gov.mapa.ptd.resource.dto.AvaliacaoDTO;
import br.gov.mapa.ptd.service.PtdService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PtdController.class)
public class PtdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PtdService ptdService;



    @Test
    public void testRetornaUrlFormulario() throws Exception {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO("https://www.example.com");
        ResponseEntity<AvaliacaoDTO> response = ResponseEntity.ok(avaliacaoDTO);
        when(ptdService.retornaUrlAvaliacao(anyString())).thenReturn(response);

        mockMvc.perform(get("/api/avaliacao/test"))
                .andExpect(status().isOk());
    }

}
