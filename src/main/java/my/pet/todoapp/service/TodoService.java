package my.pet.todoapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import my.pet.todoapp.entity.Todo;
import my.pet.todoapp.entity.User;
import lombok.RequiredArgsConstructor;
import my.pet.todoapp.payload.AddTodoRequest;
import org.springframework.stereotype.Service;
import my.pet.todoapp.repository.TodoRepository;
import my.pet.todoapp.exception.TodoNotFoundException;
import my.pet.todoapp.exception.TodoNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private TodoRepository todoRepository;

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

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

        logger.info("Received TODO with id:{}", todo.getId());
        return todo;
    }

    public List<Todo> getTodoList() {
        User user = getCurrentUser();
        List<Todo> todos = todoRepository.findByUserId(user.getId());
        if (todos == null) {
            throw new TodoNotFoundException("Todo list is empty!");
        }

        logger.info("Received TODO list of user \"{}\"", user.getUsername());
        return todos;
    }

    public Todo addTodo(AddTodoRequest addTodoRequest) {
        Todo todo = new Todo();
        todo.setTittle(addTodoRequest.getTittle());
        todo.setContent(addTodoRequest.getContent());
        todo.setCompleted(Boolean.FALSE);
        todo.setCreatedDate(LocalDateTime.now());
        todo.setDeadlineDate(addTodoRequest.getDeadlineDate());
        todo.setUser(getCurrentUser());
        todoRepository.save(todo);

        logger.info("Added new TODO with id:{}", todo.getId());
        return todo;
    }

    public Todo deleteTodo(Long id) {
        Todo todo = getTodo(id);
        todoRepository.deleteById(id);

        logger.info("Deleted TODO with id:{}", todo.getId());
        return todo;
    }


    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
