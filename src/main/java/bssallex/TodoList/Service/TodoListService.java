package bssallex.TodoList.Service;

import bssallex.TodoList.Repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final TodoListRepository repository;
}
