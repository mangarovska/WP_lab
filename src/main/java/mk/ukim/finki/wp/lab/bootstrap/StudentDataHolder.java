package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDataHolder {
    public static List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student("171175", "banana", "Ана", "Мангаровска"));
        students.add(new Student("203060", "flejta", "Anastazija", "Stojanovska"));
        students.add(new Student("191222", "morkov", "Petar", "Petrovski"));
        students.add(new Student("216889", "twix", "Astor", "Kovachevikj"));
        students.add(new Student("205568", "vuvuzela", "Obi", "Mangarovski"));
    }
}

// da se prikazat site sto ne go slusaat predmetot selektiran vo prvata strana