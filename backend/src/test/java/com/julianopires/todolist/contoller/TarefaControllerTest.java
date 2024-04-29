package com.julianopires.todolist.contoller;

import com.julianopires.todolist.fixture.TarefaControllerFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class TarefaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testRotaVizualizarTarefas() throws Exception {
        this.mockMvc.perform(get("/api/v1/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testRotaAdicionarTarefas() throws Exception {
        this.mockMvc.perform(post("/api/v1/tarefas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TarefaControllerFixture.jsonAdicionarTarefa())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(TarefaControllerFixture.retoronoJsonAdicionarTarefa()));
    }


}