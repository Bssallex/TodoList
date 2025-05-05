package bssallex.TodoList.Response;

import bssallex.TodoList.Entity.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record ListResponse(Long id, String nomeTarefa, String descricaoTarefa,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate dataCriacao, Status status) {
}
