package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.TeacherDataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTeacherRepository {

    public List<Teacher> findAll() {
        return TeacherDataHolder.teachers;
    }

    public Optional<Teacher> findById(Long teacherId) { // vrakja objekt od Teacher
        return TeacherDataHolder.teachers.stream().filter(t -> t.getId().equals(teacherId)).findFirst();
    }
}
