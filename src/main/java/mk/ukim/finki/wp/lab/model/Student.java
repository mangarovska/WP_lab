package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data // za setteri i getteri
@Entity
@Table(name = "students")
public class Student {

    @Id
    private String username;
    // se formiraat i constraints za primaren kluc koj e unikaten
    // i index koj go optimizira prebaruvanje po primaren kluc

    private String password;

    private String name;

    private String surname;


    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Student() {

    }
}
