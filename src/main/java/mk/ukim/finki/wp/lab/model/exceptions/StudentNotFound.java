package mk.ukim.finki.wp.lab.model.exceptions;

public class StudentNotFound extends RuntimeException {
    public StudentNotFound() {
        super("Student not found!");
    }
}
