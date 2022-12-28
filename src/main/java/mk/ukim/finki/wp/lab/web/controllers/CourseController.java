
package mk.ukim.finki.wp.lab.web.controllers;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    public final CourseService courseService;
    public final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;

    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error,
                                 //@RequestParam(required = false) Long id,
                                 Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Course> coursesList = courseService.listAllCourses().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
        //List<Course> coursesListReversed = courseService.listAllCourses().stream().sorted(Comparator.comparing(Course::getName).reversed()).collect(Collectors.toList());
        model.addAttribute("courses", coursesList);
        //model.addAttribute("coursesReversed", coursesListReversed);
        model.addAttribute("bodyContent", "listCourses");

        //return "listCourses"; //  html-ot
        return "master-template";
    }

    @GetMapping("/add-course")
    public String getAddCoursePage(Model model) {
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("bodyContent", "add-course");
        //return "add-course";
        return "master-template";
    }

    @PostMapping("/added-course")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long id) {

        //Course c = new Course(name,description);
        try {
            courseService.saveCourse(name, description, id);
        } catch (RuntimeException e) {
            return "redirect:/courses?error=" + e.getMessage();
        }

        return "redirect:/courses";
    }

    // /products?id=78 -> query parametar
    @GetMapping("/delete/{id}") // vaka e so path variable
    public String deleteCourse(@PathVariable Long id) { // bese String
        this.courseService.deleteById(id);

//        if(this.courseService.findCourseById(id) == null) return ResponseEntity.ok().build();
//        return ResponseEntity.badRequest().build();

        return "redirect:/courses";
    }

    @GetMapping("/coursesReversed") // vaka e so path variable
    public String reverse(Model model) {
        List<Course> coursesListReversed = courseService.listAllCourses().stream().sorted(Comparator.comparing(Course::getName).reversed()).collect(Collectors.toList());
        model.addAttribute("courses", coursesListReversed);
        model.addAttribute("bodyContent2", "listCoursesReversed");

        return "master-templateReversed";
    }

    @GetMapping("/edit-form/{id}")
    public String editCoursePage(@PathVariable Long id, Model model) {

        Course c = courseService.findCourseById(id).get();

        if (this.courseService.listAllCourses().contains(c)) { // ako vo tabelata go ima id-to

            //Long pomId = c.getCourseId();

            courseService.deleteById(id);

            //Course c = this.courseService.findCourseById(id).get();
            List<Teacher> teachers = this.teacherService.findAll();

            model.addAttribute("course", c);
            model.addAttribute("teachers", teachers);
            //model.addAttribute("id", pomId);

            return "add-course";
        }
        return "redirect:/courses?error=CourseNotFound";
    }
}