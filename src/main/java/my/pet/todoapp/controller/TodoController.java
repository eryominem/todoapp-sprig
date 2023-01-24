package my.pet.todoapp.controller;

import my.pet.todoapp.entity.Todo;
import my.pet.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import my.pet.todoapp.payload.AddTodoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /*@GetMapping
    public ResponseEntity<?> getTodos() {
        List<Todo> todos = todoService.getTodoList();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable(value = "id") Long id) {
        Todo todo = todoService.getTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@Valid @RequestBody AddTodoRequest addTodoRequest) {
        Todo todo = todoService.addTodo(addTodoRequest);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") Long id) {
        Todo todo = todoService.deleteTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

}
