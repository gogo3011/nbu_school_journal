package com.example.schooljournal.web.endpoints;

import static org.mockito.Mockito.when;

import com.example.schooljournal.services.DirectorService;
import com.example.schooljournal.services.StudentService;
import com.example.schooljournal.services.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentEndpoint.class})
@ExtendWith(SpringExtension.class)
class StudentEndpointTest {
    @MockBean
    private DirectorService directorService;

    @Autowired
    private StudentEndpoint studentEndpoint;

    @MockBean
    private StudentService studentService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link StudentEndpoint#list(Integer, Integer, String)}
     */
    @Test
    void testList() throws Exception {
        // Arrange
        when(studentService.getAll(Mockito.<Pageable>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/student/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNo", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1))
                .param("sortBy", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(studentEndpoint)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link StudentEndpoint#listMyStudents(Integer, Integer, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testListMyStudents() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because "authentication" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.Authentication.getName()" because "authentication" is null
        //       at com.example.schooljournal.web.endpoints.GenericEndpoint.getCurrentUser(GenericEndpoint.java:21)
        //       at com.example.schooljournal.web.endpoints.StudentEndpoint.listMyStudents(StudentEndpoint.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new StudentEndpoint()).listMyStudents(1, 3, "Sort By");
    }
}
