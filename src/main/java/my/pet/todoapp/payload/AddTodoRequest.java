package my.pet.todoapp.payload;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AddTodoRequest {
    @NotNull
    private String tittle;
    @NotNull
    private String content;
}
