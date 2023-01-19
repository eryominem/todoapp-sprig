package my.pet.todoapp.payload;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private final String type = "Bearer";
    private String token;
}

