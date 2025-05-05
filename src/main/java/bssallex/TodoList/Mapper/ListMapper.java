package bssallex.TodoList.Mapper;

import bssallex.TodoList.Entity.TodoListEntity;
import bssallex.TodoList.Request.ListRequest;
import bssallex.TodoList.Response.ListResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ListMapper {

    public static TodoListEntity ListRequest(ListRequest request){

        return TodoListEntity.builder()
                .nomeTarefa(request.nomeTarefa())
                .descricaoTarefa(request.descricaoTarefa())
                .dataCriacao(request.dataCriacao())
                .status(request.status())
                .build();
    }

    public static ListResponse ListResponse(TodoListEntity entity){

        return ListResponse.builder()
                .id(entity.getId())
                .nomeTarefa(entity.getNomeTarefa())
                .descricaoTarefa(entity.getDescricaoTarefa())
                .dataCriacao(entity.getDataCriacao())
                .status(entity.getStatus())
                .build();
    }
}
