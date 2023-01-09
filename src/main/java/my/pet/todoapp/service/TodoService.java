package my.pet.todoapp.service;

import lombok.RequiredArgsConstructor;
import my.pet.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private TodoRepository todoRepository;
}
