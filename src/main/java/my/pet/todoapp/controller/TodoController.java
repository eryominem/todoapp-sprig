package my.pet.todoapp.controller;

import lombok.RequiredArgsConstructor;
import my.pet.todoapp.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private TodoService todoService;
}
