package bssallex.TodoList.Request;

import bssallex.TodoList.Entity.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record ListRequest(String nomeTarefa, String descricaoTarefa,
                          @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                          LocalDate dataCriacao, Status status) {
}
