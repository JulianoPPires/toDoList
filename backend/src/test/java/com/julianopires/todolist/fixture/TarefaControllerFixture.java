package com.julianopires.todolist.fixture;

import com.julianopires.todolist.dto.request.StatusTarefaDTO;
import com.julianopires.todolist.dto.request.TarefaDTO;

public class TarefaControllerFixture {
    public static String jsonAdicionarTarefa() {
        return "{\n" +
                "  \"titulo\": \"titulo_f43d4a40d960\",\n" +
                "  \"descricao\": \"descricao_b193085d9d9f\",\n" +
                "  \"dataCriacao\": \"2024-04-27 02:33:31\",\n" +
                "  \"dataConclusao\": null,\n" +
                "  \"status\": \"PENDENTE\"\n" +
                "}";
    }

    public static String retoronoJsonAdicionarTarefa(){
        return "{\"id\":1," +
                "\"titulo\":\"titulo_f43d4a40d960\"," +
                "\"descricao\":\"descricao_b193085d9d9f\"," +
                "\"dataCriacao\":\"2024-04-27T02:33:31.000+00:00\"," +
                "\"dataConclusao\":null," +
                "\"status\":\"PENDENTE\"}";
    }
}
