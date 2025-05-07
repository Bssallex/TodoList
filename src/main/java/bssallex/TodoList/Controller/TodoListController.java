package bssallex.TodoList.Controller;

import bssallex.TodoList.Request.ListRequest;
import bssallex.TodoList.Response.ListResponse;
import bssallex.TodoList.Service.TodoListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService service;

    @GetMapping
    @Operation(summary = "Listar todas as tarefas", description = "Lista todas as tarefas, independente do status de PENDENTE/CONCLUÍDO")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Requisição bem sucedida")})
    public ResponseEntity<List<ListResponse>> listarTarefas(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/pendentes")
    @Operation(summary = "Listar tarefas pendentes", description = "Lista todas as tarefas com o status de PENDENTE")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Requisição bem sucedida")})
    public ResponseEntity listarTarefasPendentes(){
        return ResponseEntity.ok(service.listarPendentes());
    }

    @GetMapping("/concluidas")
    @Operation(summary = "Listar tarefas concluídas", description = "Lista todas as tarefas com o status de CONCLUÍDO")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Requisição bem sucedida")})
    public ResponseEntity listarTarefasConcluidas(){
        return ResponseEntity.ok(service.listarConcluidas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar tarefa por ID", description = "Lista uma tarefa passando um ID na requisição")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
    @ApiResponse(responseCode = "404", description = "Recurso inválido")
    })
    public ResponseEntity listarTarefaPorId(@PathVariable Long id){
        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Criar uma nova tarefa", description = "Cria uma tarefa e salva automaticamente com o status de PENDENTE")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Recurso criado com exito")})
    public ResponseEntity criarTarefa(@RequestBody ListRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar uma tarefa", description = "Altera uma tarefa por ID, mas o status continua PENDENTE")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
    @ApiResponse(responseCode = "404", description = "Recurso inválido")
    })
    public ResponseEntity alterarTarefa(@PathVariable Long id, @RequestBody ListRequest request){
        return service.listarId(id).map(alterar -> {
            service.alterar(id, request);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado"));
    }

    @PutMapping("/concluir/{id}")
    @Operation(summary = "Concluir uma tarefa", description = "Manda na requisição o ID e automaticamente o status e alterado para CONCLUÍDO")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
    @ApiResponse(responseCode = "404", description = "Recurso inválido")
    })
    public ResponseEntity tarefaConcluida(@PathVariable Long id){
        return service.listarId(id).map(concluir -> {
            service.concluirTarefa(id);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma tarefa", description = "Deleta uma tarefa passando o ID na requisição")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Requisição bem sucedida"),
    @ApiResponse(responseCode = "404", description = "Recurso inválido")
    })
    public ResponseEntity deletarTarefa(@PathVariable Long id){
        return service.listarId(id).map(deletar ->{
            service.deletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body("Id não encontrado"));
    }
}
