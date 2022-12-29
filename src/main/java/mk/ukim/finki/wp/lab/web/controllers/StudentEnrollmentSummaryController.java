package mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("StudentEnrollmentSummary")
public class StudentEnrollmentSummaryController {
    public final SpringTemplateEngine springTemplateEngine;
    public final StudentService studentService;
    public final CourseService courseService;

    public StudentEnrollmentSummaryController(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String getStudentEnrollmentSummaryPage(HttpServletRequest req, Model model) {
        long chosenCourseId = (long) req.getSession().getAttribute("chosenCourse");

        model.addAttribute("courseName", courseService.getCourse(chosenCourseId));
        model.addAttribute("students", courseService.listStudentsByCourse(chosenCourseId));
        model.addAttribute("bodyContent", "studentsInCourse");
        return "master-template";
    }

    @PostMapping
    public String postStuentEnrollmentSummaryPage(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
        long chosenCourseId = (long) req.getSession().getAttribute("chosenCourse");

        String username = req.getParameter("student");

        //dodadi go selektiraniot student vo lsitata na kursot
        courseService.addStudentInCourse(username, chosenCourseId);
        model.addAttribute("courseName", courseService.getCourse(chosenCourseId));
        model.addAttribute("students", courseService.listStudentsByCourse(chosenCourseId));
        model.addAttribute("bodyContent", "studentsInCourse");
        return "master-template";
    }
}