package my.pet.todoapp.controller;

import my.pet.todoapp.entity.Todo;
import my.pet.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import my.pet.todoapp.payload.AddTodoRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getTodoList();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo getTodoById(@PathVariable(value = "id") Long id) {
        return todoService.getTodo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@Valid @RequestBody AddTodoRequest addTodoRequest) {
        return todoService.addTodo(addTodoRequest);
    }

    @DeleteMapping("/{id}")
    public Todo deleteTodoById(@PathVariable("id") Long id) {
        return todoService.deleteTodo(id);
    }
}
