package my.pet.todoapp.controller;

import lombok.RequiredArgsConstructor;
import my.pet.todoapp.entity.Todo;
import my.pet.todoapp.entity.User;
import my.pet.todoapp.payload.AddTodoRequest;
import my.pet.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;


    /**
     *         ДОДЕЛАТЬ:
     * получение конкретной задачи
     *     удаление задачи
     *
     */
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<?> getTodos() {
        List<Todo> todos = todoService.getTodoList();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@Valid @RequestBody AddTodoRequest addTodoRequest) {
        Todo newTodo = todoService.addTodo(addTodoRequest);
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }
}
