package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Teacher {
    private Long id;
    private String name;
    private String surname;

    public Teacher(Long id, String name, String surname) {
        this.id = id;
        //this.id = (long) (Math.random() * 1000); // dali treba random?
        this.name = name;
        this.surname = surname;
    }
}
