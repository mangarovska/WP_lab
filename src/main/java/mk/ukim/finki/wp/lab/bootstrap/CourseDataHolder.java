package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDataHolder {

    public static List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init() {
        courses.add(new Course("Криптографија", "опис", new ArrayList<Student>()));
        courses.add(new Course("Е-влада", "опис е-влада", new ArrayList<Student>()));
        courses.add(new Course("Mрежна безбедност", "опис мрежи", new ArrayList<Student>()));
        courses.add(new Course("Напредно Програмирање", "опис напредно", new ArrayList<Student>()));
        courses.add(new Course("Маркетинг", "опис маркетинг", new ArrayList<Student>()));
    }
}
