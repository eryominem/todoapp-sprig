package my.pet.todoapp.service;

import my.pet.todoapp.entity.User;
import my.pet.todoapp.entity.Todo;
import my.pet.todoapp.exception.TodoNotFoundException;
import my.pet.todoapp.payload.AddTodoRequest;
import my.pet.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

   /* public List<Todo> getTodoList() {
        List<Todo> todos = todoRepository.findAll();
        if (todos == null) {
            throw new TodoNotFoundException("Todo list is empty!");
        }
        return todos;
    }

    public Todo getTodoById(Long id) {
        Todo todo = todoRepository.getById(id);
        if (todo == null) {
            throw new TodoNotFoundException("Todo not found!");
        }
        return todo;
    }*/

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

    /*public void deleteById(Long id) {
        Todo todo = todoRepository.getById(id);
        if (todo == null) {
            throw new TodoNotFoundException("Todo not found!");
        }
        todoRepository.deleteById(id);
    }*/

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
