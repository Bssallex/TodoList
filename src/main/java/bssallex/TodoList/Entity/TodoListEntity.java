package bssallex.TodoList.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "todolist")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeTarefa;

    private String descricaoTarefa;

    private LocalDate dataCriacao;

    private Status status;
}
