package mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    public final CourseService courseService;
    public final TeacherService teacherService;
    public final CourseRepository courseRepository;

    public CourseController(CourseService courseService, TeacherService teacherService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Course> courses = this.courseService.listAllCourses();
        model.addAttribute("courses", courseService.listAllCourses());

        return "listCourses"; //  html-ot
    }

    @PostMapping("/add-course")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long id) {

        try {
            courseService.save(name, description, id);
        } catch (RuntimeException e) {
            return "redirect:/courses?error=" + e.getMessage();
        }
        return "redirect:/courses";
    }

    // /products?id=78 -> query parametar
    @DeleteMapping("/delete/{id}") // vaka e so path variable
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.courseRepository.findById(id) != null) {
            Course course = this.courseRepository.findById(id);
            List<Teacher> teachers = this.teacherService.findAll();

            model.addAttribute("course", course);
            model.addAttribute("teacher", teachers);

            return "add-course";
        }

        return "redirect:/courses?error=CourseNotFound";
    }
}
