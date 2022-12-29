package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test") // ke se aktivira profilot test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // dali contextot uspesno ke se vcita -> dali app ke startne
// na koja porta ke slusame
class LabApplicationTests {

    @Test
    void contextLoads() {
    }

    MockMvc mockMvc;

    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @BeforeEach // pred sekoj test ovoj metod
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //initData();
    }

//    private void initData() {
//        if (!dataInitialized) {
//            c1 = categoryService.create("c1", "c1");
//            categoryService.create("c2", "c2");
//
//            m1 = manufacturerService.save("m1", "m1").get();
//            manufacturerService.save("m2", "m2");
//
//            String user = "user";
//            String admin = "admin";
//
//            userService.register(user, user, user, user, user, Role.ROLE_USER);
//            userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
//            dataInitialized = true;
//        }
//
//    }

    private static boolean dataInitialized = false;

    @Test
    public void testGetCourses() throws Exception {
        MockHttpServletRequestBuilder courseRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(courseRequest)
                .andDo(MockMvcResultHandlers.print()) // pecati status na odgovor
                .andExpect(MockMvcResultMatchers.status().isOk()) //sto sakame da ocekuvaeme
                //.andExpect(MockMvcResultMatchers.model().attributeExists("teachers")) // dali postoi attr
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "listCourses"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));
    }

    @Test
    public void deleteCourse() {

    }
}
