package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.repository.impl.InMemoryStudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByNameOrSurname(text, text);
    }

    @Override
    public Student saveStudent(String username, String password, String name, String surname) {

        Student s = new Student(username, password, name, surname);

        if (s.getUsername().isEmpty() || s.getPassword().isEmpty() || s.getName().isEmpty() || s.getSurname().isEmpty()) {
            throw new IllegalArgumentException();
        }
        studentRepository.findAll().removeIf(x -> x.getUsername().equals(username)); // remove for rewrite
        studentRepository.findAll().add(s);

        return s;
    }
}
