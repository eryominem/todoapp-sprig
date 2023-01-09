package my.pet.todoapp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @Pattern(regexp = "^[a-z0-9_-]*$")
    @Size(min = 3, max = 30)
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;
}
