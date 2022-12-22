package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    //@Column(name = "course_name")
    private String name;

    @Column(length = 4000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER) // to many deka se raboti za lista -> se implementira so megutabela
    private List<Student> students;

    @ManyToOne //(fetch = FetchType.EAGER)
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description, List<Student> students) {
        this.name = name;
        this.description = description;
        this.students = students;
    }

    public Course(String name, String description, Teacher teacher) {
        //this.courseId = (long) (Math.random() * 1000); // za random id
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        students = new ArrayList<>();
    }

//    public Course(Student studentX, Optional<Course> courseX) {
//        this.name = name;
//        this.description = description;
//        this.students = students;
//
//
//    }

    public void addStudent(Student student){
        students.removeIf(s->s.getUsername().equals(student.getUsername()));
        students.add(student);
    }

    public Course() {

    }
}
