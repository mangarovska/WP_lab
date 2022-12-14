package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.*;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    // treba da se site repos?
    private final CourseRepository courseRepository; // se koristi jpa repo
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    //private final TeacherService teacherService;
    //private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;

        //this.studentService = studentService;
        //this.teacherService = teacherService;
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        Course c = courseRepository.findById(courseId).orElseThrow(CourseNotFound::new);

        return c.getStudents();
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return findAllStudentsByCourse(courseId);
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

//    @Override
//    public Course addStudentInCourse(String username, Long courseId) {
//
//        Student s = (Student) studentService.listAll().stream().filter(x -> x.getUsername().equals(username));
//        return courseRepository.addStudentToCourse(s, courseRepository.findById(courseId));
//    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {

        //Student studentX = (Student) studentRepository.findAll().stream().filter(y -> y.getUsername().equals(username)).collect(Collectors.toList()).get(0);
        Student studentX = studentRepository.findById(username).orElseThrow(StudentNotFound::new);
        Optional<Course> courseX = courseRepository.findById(courseId);

        if (courseX.isPresent()) {
            Course c = courseX.get();
            c.addStudent(studentX);
            courseRepository.save(c);
        }

        //return courseRepository.save(new Course(studentX, courseX));
        return null;
    }

    @Override
    public Course saveCourse(String name, String description, Long teacherId) {
        if (name.isEmpty() && description.isEmpty()) {
            throw new ArgumentsNotValidException();
        }

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(TeacherDoesNotExistException::new);

        return courseRepository.save(new Course(name, description, teacher)); // bese saveCourse od inMemory
    }

    @Override
    public void deleteById(Long id) {
        //CourseDataHolder.courses.removeIf(c -> c.getCourseId().equals(id));
        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> findCourseById(Long courseId) {
        return courseRepository.findById(courseId);

    }

    @Override
    public Optional<Course> getCourse(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course saveCourse(Course course, long l) {

        //vidi dali se prazni name ili description
        //I don't know if this is even needed
        if (course.getName().isEmpty())
            throw new NameException();
        if (course.getDescription().isEmpty())
            throw new DescriptionException();

        //vidi dali go ima toj profesor
        Teacher teacher = teacherRepository.findById(l).orElseThrow(TeacherDoesNotExistException::new);
        course.setTeacher(teacher);

        return courseRepository.save(course);
    }
}