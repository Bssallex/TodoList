package bssallex.TodoList.Controller;

import bssallex.TodoList.Request.ListRequest;
import bssallex.TodoList.Response.ListResponse;
import bssallex.TodoList.Service.TodoListService;
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
    public ResponseEntity<List<ListResponse>> listarTarefas(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/pendentes")
    public ResponseEntity listarTarefasPendentes(){
        return ResponseEntity.ok(service.listarPendentes());
    }

    @GetMapping("/concluidos")
    public ResponseEntity listarTarefasConcluidas(){
        return ResponseEntity.ok(service.listarConcluidas());
    }

    @GetMapping("/{id}")
    public ResponseEntity listarTarefaPorId(@PathVariable Long id){

        return service.listarId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity salvarTarefa(@RequestBody ListRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity alterarTarefa(@PathVariable Long id, @RequestBody ListRequest request){
        return service.listarId(id).map(alterar -> {
            service.alterar(id, request);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado"));
    }

    @PutMapping("/concluir/{id}")
    public ResponseEntity tarefaConcluida(@PathVariable Long id){
        return service.listarId(id).map(concluir -> {
            service.concluirTarefa(id);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarTarefa(@PathVariable Long id){
        return service.listarId(id).map(deletar ->{
            service.deletar(id);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body("Id não encontrado"));
    }
}
