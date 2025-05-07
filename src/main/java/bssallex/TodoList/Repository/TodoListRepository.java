package bssallex.TodoList.Repository;

import bssallex.TodoList.Entity.Status;
import bssallex.TodoList.Entity.TodoListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoListEntity, Long> {

    List<TodoListEntity> findByStatus(Status status);
}
