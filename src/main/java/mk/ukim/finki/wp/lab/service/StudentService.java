package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> listAll();

    List<Student> searchByNameOrSurname(String text);

    Student saveStudent(String username, String password, String name, String surname);
}
