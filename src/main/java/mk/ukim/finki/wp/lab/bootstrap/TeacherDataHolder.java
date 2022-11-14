package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDataHolder {

    public static List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init(){
        teachers.add(new Teacher("Igor", "Mishkovski"));
        teachers.add(new Teacher("Kire", "Trivodaliev"));
        teachers.add(new Teacher("Boban", "Joksimovski"));
        teachers.add(new Teacher("Sasho", "Gramatikov"));
        teachers.add(new Teacher("Kostadin", "Mishev"));
    }
}
