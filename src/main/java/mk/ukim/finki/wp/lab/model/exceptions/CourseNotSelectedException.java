package mk.ukim.finki.wp.lab.model.exceptions;

public class CourseNotSelectedException extends RuntimeException{
    public CourseNotSelectedException(){
        super("Please select course!");
    }
}
