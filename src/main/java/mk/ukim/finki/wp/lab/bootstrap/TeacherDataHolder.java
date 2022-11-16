package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDataHolder {

    public static List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init() {
        teachers.add(new Teacher(123444L, "Igor", "Mishkovski"));
        teachers.add(new Teacher(231566L, "Kire", "Trivodaliev"));
        teachers.add(new Teacher(444555L, "Boban", "Joksimovski"));
        teachers.add(new Teacher(889756L, "Sasho", "Gramatikov"));
        teachers.add(new Teacher(777888L, "Kostadin", "Mishev"));
    }
}
