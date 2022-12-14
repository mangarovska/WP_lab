package mk.ukim.finki.wp.lab.web.servlets;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "list-student-servlet", urlPatterns = "/AddStudentServlet")
public class ListStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;


    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());

        String courseId = req.getParameter("courseId");
        //req.getSession().setAttribute("chosenCourse", Long.valueOf(courseId));

        Optional<Course> course = courseService.getCourse(Long.valueOf(courseId));
        //req.getSession().setAttribute("course", course.get()); // ===============================================================

        List<Student> enrolled = courseService.listStudentsByCourse(Long.valueOf(courseId)); // gi zima site zapisani na predmetot
        List<Student> all = studentService.listAll(); // site
        List<Student> notEnrolled = new ArrayList<>();

        for (Student s : all) {
            boolean f = false;
            for (Student a : enrolled) {
                if (s.getUsername().equals(a.getUsername())) {
                    f = true;
                }
            }
            if (!f) {
                notEnrolled.add(s);
            }
        }

        context.setVariable("students", notEnrolled);
        context.setVariable("courseId", courseId);

        //course service to get course
        //Optional<Course> course = courseService.findCourseById(Long.valueOf(courseId));

        //resp.setContentType("text/html; charset=UTF-8");

        //context.setVariable("bodyContent","listStudents");

        springTemplateEngine.process("listStudents.htl", context, resp.getWriter());
        //springTemplateEngine.process("master-template.html", context, resp.getWriter());
    }
}
