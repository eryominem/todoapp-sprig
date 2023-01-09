package my.pet.todoapp.repository;

import my.pet.todoapp.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
