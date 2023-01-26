package my.pet.todoapp.exception;

public class TodoNotFoundException extends TodoException {
    public TodoNotFoundException(String message) {
        super(message);
    }
}
