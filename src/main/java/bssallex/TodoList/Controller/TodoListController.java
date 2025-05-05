package bssallex.TodoList.Controller;

import bssallex.TodoList.Service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class TodoListController {

    private final TodoListService service;
}
