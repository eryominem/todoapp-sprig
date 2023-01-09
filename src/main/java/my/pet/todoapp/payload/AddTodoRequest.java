package my.pet.todoapp.payload;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AddTodoRequest {
    private String tittle;
    private String content;
}
