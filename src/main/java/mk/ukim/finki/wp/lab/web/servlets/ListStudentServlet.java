package mk.ukim.finki.wp.lab.web.servlets;

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

@WebServlet(name = "list-student-servlet", urlPatterns = "/AddStudent")
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
        context.setVariable("courseId", courseId);

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

        req.getSession().setAttribute("chosenCourse", Long.valueOf(courseId));

        //resp.setContentType("text/html; charset=UTF-8");

        springTemplateEngine.process("listStudents.html", context, resp.getWriter());
    }
}
