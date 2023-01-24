package my.pet.todoapp.repository;

import my.pet.todoapp.entity.Todo;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long id);

    Optional<Todo> findById(Long id);
}
