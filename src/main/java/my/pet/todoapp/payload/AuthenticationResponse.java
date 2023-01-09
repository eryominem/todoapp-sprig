package my.pet.todoapp.payload;

import lombok.*;
import my.pet.todoapp.entity.Role;
import my.pet.todoapp.entity.Todo;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
}

