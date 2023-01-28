package my.pet.todoapp.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AddTodoRequest {
    @NotNull
    private String tittle;
    @NotNull
    private String content;

    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private LocalDateTime deadlineDate;
}
