package mk.ukim.finki.wp.lab.web.servlets;

import mk.ukim.finki.wp.lab.model.Course;
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
import java.util.stream.Collectors;

@WebServlet(name = "stud-enrol-servlet", urlPatterns = "/studentEnrollmentSummaryServlet")
public class StudentEnrollmentSummary extends HttpServlet {

    public final SpringTemplateEngine springTemplateEngine;
    public final CourseService courseService;
    public final StudentService studentService;


    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long chosenCourseId = (Long) req.getSession().getAttribute("chosenCourse");

        if (req.getParameter("student") == null) {
            resp.sendRedirect("/AddStudent?courseId=" + chosenCourseId);
            return;
        }

        String S_username = req.getParameter("student");
        courseService.addStudentInCourse(S_username, chosenCourseId);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        //context.setVariable("students", courseService.listStudentsByCourse(course.getCourseId()));
        //context.setVariable("courseName", courseService.listAllCourses().stream().filter(c -> c.getCourseId().equals(course.getCourseId())).collect(Collectors.toList()).get(0).getName());

        context.setVariable("courseName",courseService.findCourseById(chosenCourseId));
        context.setVariable("students",courseService.listStudentsByCourse(chosenCourseId));

        // gi zima site zapisani na predmetot so pomos na courseId


        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/listCourses");
    }
}
