package my.pet.todoapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import my.pet.todoapp.entity.User;
import my.pet.todoapp.entity.Role;
import lombok.RequiredArgsConstructor;
import my.pet.todoapp.exception.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import my.pet.todoapp.payload.RegisterRequest;
import my.pet.todoapp.repository.UserRepository;
import my.pet.todoapp.payload.AuthenticationRequest;
import my.pet.todoapp.config.security.jwt.JwtService;
import my.pet.todoapp.payload.AuthenticationResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    //регистрация
    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AppError("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AppError("Error: Email is exist"));
        }

        var user = User.builder()       //@Builder - позволяет вам автоматически создавать код, необходимый для
                .email(request.getEmail())    //создания экземпляра вашего класса (.builder())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .todoList(new ArrayList<>())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        logger.info("User \"{}\" has been created", user.getUsername());
        return new ResponseEntity(AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build(), HttpStatus.CREATED);
    }

    //авторизация
    public ResponseEntity<?> login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        logger.info("User \"{}\" has been authorized", user.getUsername());
        return ResponseEntity.ok(AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build());
    }
}
