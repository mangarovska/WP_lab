package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.bootstrap.CourseDataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.ArgumentsNotValidException;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherDoesNotExistException;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAllCourses();
    }

//    @Override
//    public Course addStudentInCourse(String username, Long courseId) {
//
//        Student s = (Student) studentService.listAll().stream().filter(x -> x.getUsername().equals(username));
//        return courseRepository.addStudentToCourse(s, courseRepository.findById(courseId));
//    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {

        Student studentX = (Student) studentService.listAll().stream().filter(y -> y.getUsername().equals(username)).collect(Collectors.toList()).get(0);
        Course courseX = courseRepository.findById(courseId);
        return courseRepository.addStudentToCourse(studentX, courseX);
    }

    @Override
    public Course saveCourse(String name, String description, Long teacherId) {
        if (name.isEmpty() && description.isEmpty()) {
            throw new ArgumentsNotValidException();
        }

        Teacher teacher = teacherService.findById(teacherId).orElseThrow(TeacherDoesNotExistException::new);

        return courseRepository.saveCourse(name, description, teacher);
    }

    @Override
    public void deleteById(Long id) {
        CourseDataHolder.courses.removeIf(c -> c.getCourseId().equals(id));
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(Long.valueOf(courseId));
    }
}