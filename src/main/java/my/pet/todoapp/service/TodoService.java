package my.pet.todoapp.service;

import my.pet.todoapp.entity.Todo;
import my.pet.todoapp.entity.User;
import my.pet.todoapp.exception.TodoNotAvailableException;
import my.pet.todoapp.payload.AddTodoRequest;
import org.springframework.stereotype.Service;
import my.pet.todoapp.repository.TodoRepository;
import my.pet.todoapp.exception.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("Todo with id " + id + " not found.")
        );

        if (todo.getUser().getId() != getCurrentUser().getId()) {
            throw new TodoNotAvailableException("This resource is not a resource for the current user.");
        }

        return todo;
    }

    public List<Todo> getTodoList() {
        User user = getCurrentUser();
        List<Todo> todos = todoRepository.findByUserId(user.getId());
        if (todos == null) {
            throw new TodoNotFoundException("Todo list is empty!");
        }
        return todos;
    }

    public Todo addTodo(AddTodoRequest addTodoRequest) {
        Todo todo = new Todo();
        todo.setTittle(addTodoRequest.getTittle());
        todo.setContent(addTodoRequest.getContent());
        todo.setCreatedTime(LocalDateTime.now());
        todo.setCompleted(Boolean.FALSE);
        todo.setUser(getCurrentUser());

        todoRepository.save(todo);
        return todo;
    }

    public Todo deleteTodo(Long id) {
        Todo todo = getTodo(id);
        todoRepository.deleteById(id);
        return todo;
    }


    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
