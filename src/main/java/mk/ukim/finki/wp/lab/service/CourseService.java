package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Student> listStudentsByCourse(Long courseId);

    List<Course> listAllCourses();

    Course addStudentInCourse(String username, Long courseId);

    Optional<Course> save(String name, String description, Long teacherId);

    void deleteById(Long id);



}
