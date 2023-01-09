package my.pet.todoapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.pet.todoapp.payload.RegisterRequest;
import org.springframework.http.ResponseEntity;
import my.pet.todoapp.service.AuthenticationService;
import my.pet.todoapp.payload.AuthenticationRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> register(@Valid @RequestBody AuthenticationRequest request) {
        return authenticationService.login(request);
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo cont is work";
    }
}
