package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.CourseDataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class CourseRepository {

    public List<Course> findAllCourses() {
        return CourseDataHolder.courses;
    }

    public Course findById(Long courseId) { // optional -> za vrednost koja moze da ja ima ili nema
        Optional<Course> course = CourseDataHolder.courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst();

        if (course.isEmpty()) {
            throw new NoSuchElementException();
        }
        return course.get();
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        return findById(courseId).getStudents();
    }

    public Course addStudentToCourse(Student student, Course course) {
        if (!CourseDataHolder.courses.contains(course)) {
            throw new NoSuchElementException();
        }

        course.getStudents().removeIf(s -> s.getUsername().equals(student.getUsername())); // brise ako postoi studentot
        course.getStudents().add(student);

        return course;
    }

    public Optional<Course> saveCourse(String name, String description, Teacher teacher) {
//        if (name == null && !name.isEmpty() && description == null && !description.isEmpty()) {
//            throw new ArgumentsNotValidException();
//        }

        CourseDataHolder.courses.removeIf(c -> c.getName().equals(name));

        Course course = new Course(name, description, teacher);
        CourseDataHolder.courses.add(course);
        return Optional.of(course);
    }

}
