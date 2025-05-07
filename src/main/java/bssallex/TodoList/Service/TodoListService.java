package bssallex.TodoList.Service;

import bssallex.TodoList.Entity.Status;
import bssallex.TodoList.Entity.TodoListEntity;
import bssallex.TodoList.Mapper.ListMapper;
import bssallex.TodoList.Repository.TodoListRepository;
import bssallex.TodoList.Request.ListRequest;
import bssallex.TodoList.Response.ListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final TodoListRepository repository;

    public List<ListResponse> listar(){
         List<TodoListEntity> listar = repository.findAll();
         return listar.stream()
                 .map(ListMapper::ListResponse)
                 .toList();
    }

    public List<ListResponse> listarPendentes(){
        List<TodoListEntity> listar = repository.findByStatus(Status.PENDENTE);
        return listar.stream()
                .map(ListMapper::ListResponse)
                .toList();
    }

    public List<ListResponse> listarConcluidas(){
        List<TodoListEntity> listar = repository.findByStatus(Status.CONCLUIDO);
        return listar.stream()
                .map(ListMapper::ListResponse)
                .toList();
    }

    public Optional<ListResponse> listarId(Long id){
        Optional<TodoListEntity> verificarId = repository.findById(id);
        return verificarId.map(ListMapper::ListResponse);
    }

    public ListResponse criar(ListRequest request){
         TodoListEntity salvar = ListMapper.ListRequest(request);
         salvar.setStatus(Status.PENDENTE);
         return ListMapper.ListResponse(repository.save(salvar));
    }

    public Optional<ListResponse> alterar(long id, ListRequest request){
        return repository.findById(id).map(a -> {
            a.setNomeTarefa(request.nomeTarefa());
            a.setDescricaoTarefa(request.descricaoTarefa());
            a.setDataCriacao(request.dataCriacao());
            a.getStatus();
            return ListMapper.ListResponse(repository.save(a));
        });
    }

    public Optional<ListResponse> concluirTarefa(long id){
        return repository.findById(id).map(a -> {
            a.setStatus(Status.CONCLUIDO);
            return ListMapper.ListResponse(repository.save(a));
        });
    }

    public Optional<ListResponse> deletar(Long id){
        return repository.findById(id).map(d -> {
            repository.delete(d);
            return ListMapper.ListResponse(d);
        });
    }
}
