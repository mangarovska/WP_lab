package mk.ukim.finki.wp.lab.model.exceptions;

public class TeacherDoesNotExistException extends RuntimeException{

    public TeacherDoesNotExistException(){
        super("No such teacher!");
    }
}
