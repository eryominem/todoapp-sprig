package my.pet.todoapp.exception;

public class TodoNotAvailableException extends TodoException {
    public TodoNotAvailableException(String message) {
        super(message);
    }
}
