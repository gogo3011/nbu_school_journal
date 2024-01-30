package com.example.schooljournal.web.endpoints;

import static org.mockito.Mockito.when;

import com.example.schooljournal.data.entity.core.Address;
import com.example.schooljournal.data.entity.course.Course;
import com.example.schooljournal.data.entity.course.Grade;
import com.example.schooljournal.data.entity.course.enums.CourseSubject;
import com.example.schooljournal.data.entity.school.School;
import com.example.schooljournal.data.entity.school.enums.SchoolType;
import com.example.schooljournal.data.entity.schoolClass.SchoolClass;
import com.example.schooljournal.data.entity.user.Director;
import com.example.schooljournal.data.entity.user.Student;
import com.example.schooljournal.data.entity.user.Teacher;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.example.schooljournal.services.CourseService;
import com.example.schooljournal.services.DirectorService;
import com.example.schooljournal.services.GradeService;
import com.example.schooljournal.services.ParentService;
import com.example.schooljournal.services.UserService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

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

@ContextConfiguration(classes = {GradeEndpoint.class})
@ExtendWith(SpringExtension.class)
class GradeEndpointTest {
    @MockBean
    private CourseService courseService;

    @MockBean
    private DirectorService directorService;

    @Autowired
    private GradeEndpoint gradeEndpoint;

    @MockBean
    private GradeService gradeService;

    @MockBean
    private ParentService parentService;

    @MockBean
    private UserService userService;


    /**
     * Method under test: {@link GradeEndpoint#list(Integer, Integer, String)}
     */
    @Test
    void testList() throws Exception {
        // Arrange
        when(gradeService.getAll(Mockito.<Pageable>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/grade/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNo", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1))
                .param("sortBy", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(gradeEndpoint)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GradeEndpoint#list(Integer, Integer, String)}
     */
    @Test
    void testList2() throws Exception {
        // Arrange
        Address address = new Address();
        address.setCity("Oxford");
        address.setCountryCode("GB");
        address.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address.setCreator(new User());
        address.setDeleted(true);
        address.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address.setEditor(new User());
        address.setHouseNumber("42");
        address.setId(1L);
        address.setStreet("Street");

        User creator = new User();
        creator.setAddress(new Address());
        creator.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator.setCreator(new User());
        creator.setDeleted(true);
        creator.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator.setEditor(new User());
        creator.setEmail("jane.doe@example.org");
        creator.setFirstName("Jane");
        creator.setId(1L);
        creator.setLastName("Doe");
        creator.setPassword("iloveyou");
        creator.setSchoolRole(SchoolRole.TEACHER);

        User editor = new User();
        editor.setAddress(new Address());
        editor.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor.setCreator(new User());
        editor.setDeleted(true);
        editor.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor.setEditor(new User());
        editor.setEmail("jane.doe@example.org");
        editor.setFirstName("Jane");
        editor.setId(1L);
        editor.setLastName("Doe");
        editor.setPassword("iloveyou");
        editor.setSchoolRole(SchoolRole.TEACHER);

        User creator2 = new User();
        creator2.setAddress(address);
        creator2.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator2.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator2.setCreator(creator);
        creator2.setDeleted(true);
        creator2.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator2.setEditor(editor);
        creator2.setEmail("jane.doe@example.org");
        creator2.setFirstName("Jane");
        creator2.setId(1L);
        creator2.setLastName("Doe");
        creator2.setPassword("iloveyou");
        creator2.setSchoolRole(SchoolRole.TEACHER);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setCountryCode("GB");
        address2.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address2.setCreator(new User());
        address2.setDeleted(true);
        address2.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address2.setEditor(new User());
        address2.setHouseNumber("42");
        address2.setId(1L);
        address2.setStreet("Street");

        User creator3 = new User();
        creator3.setAddress(new Address());
        creator3.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator3.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator3.setCreator(new User());
        creator3.setDeleted(true);
        creator3.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator3.setEditor(new User());
        creator3.setEmail("jane.doe@example.org");
        creator3.setFirstName("Jane");
        creator3.setId(1L);
        creator3.setLastName("Doe");
        creator3.setPassword("iloveyou");
        creator3.setSchoolRole(SchoolRole.TEACHER);

        User editor2 = new User();
        editor2.setAddress(new Address());
        editor2.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor2.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor2.setCreator(new User());
        editor2.setDeleted(true);
        editor2.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor2.setEditor(new User());
        editor2.setEmail("jane.doe@example.org");
        editor2.setFirstName("Jane");
        editor2.setId(1L);
        editor2.setLastName("Doe");
        editor2.setPassword("iloveyou");
        editor2.setSchoolRole(SchoolRole.TEACHER);

        User editor3 = new User();
        editor3.setAddress(address2);
        editor3.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor3.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor3.setCreator(creator3);
        editor3.setDeleted(true);
        editor3.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor3.setEditor(editor2);
        editor3.setEmail("jane.doe@example.org");
        editor3.setFirstName("Jane");
        editor3.setId(1L);
        editor3.setLastName("Doe");
        editor3.setPassword("iloveyou");
        editor3.setSchoolRole(SchoolRole.TEACHER);

        Address address3 = new Address();
        address3.setCity("Oxford");
        address3.setCountryCode("GB");
        address3.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address3.setCreator(new User());
        address3.setDeleted(true);
        address3.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address3.setEditor(new User());
        address3.setHouseNumber("42");
        address3.setId(1L);
        address3.setStreet("Street");

        User creator4 = new User();
        creator4.setAddress(new Address());
        creator4.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator4.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator4.setCreator(new User());
        creator4.setDeleted(true);
        creator4.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator4.setEditor(new User());
        creator4.setEmail("jane.doe@example.org");
        creator4.setFirstName("Jane");
        creator4.setId(1L);
        creator4.setLastName("Doe");
        creator4.setPassword("iloveyou");
        creator4.setSchoolRole(SchoolRole.TEACHER);

        Director director = new Director();
        director.setAddress(new Address());
        director.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        director.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        director.setCreator(new User());
        director.setDeleted(true);
        director.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        director.setEditor(new User());
        director.setEmail("jane.doe@example.org");
        director.setFirstName("Jane");
        director.setId(1L);
        director.setLastName("Doe");
        director.setPassword("iloveyou");
        director.setSchool(new School());
        director.setSchoolRole(SchoolRole.TEACHER);

        User editor4 = new User();
        editor4.setAddress(new Address());
        editor4.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor4.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor4.setCreator(new User());
        editor4.setDeleted(true);
        editor4.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor4.setEditor(new User());
        editor4.setEmail("jane.doe@example.org");
        editor4.setFirstName("Jane");
        editor4.setId(1L);
        editor4.setLastName("Doe");
        editor4.setPassword("iloveyou");
        editor4.setSchoolRole(SchoolRole.TEACHER);

        School school = new School();
        school.setAddress(address3);
        school.setClasses(new HashSet<>());
        school.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        school.setCreator(creator4);
        school.setDeleted(true);
        school.setDirector(director);
        school.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        school.setEditor(editor4);
        school.setId(1L);
        school.setName("Name");
        school.setSchoolType(SchoolType.PRIMARY);

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setCountryCode("GB");
        address4.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address4.setCreator(new User());
        address4.setDeleted(true);
        address4.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address4.setEditor(new User());
        address4.setHouseNumber("42");
        address4.setId(1L);
        address4.setStreet("Street");

        User creator5 = new User();
        creator5.setAddress(new Address());
        creator5.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator5.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator5.setCreator(new User());
        creator5.setDeleted(true);
        creator5.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator5.setEditor(new User());
        creator5.setEmail("jane.doe@example.org");
        creator5.setFirstName("Jane");
        creator5.setId(1L);
        creator5.setLastName("Doe");
        creator5.setPassword("iloveyou");
        creator5.setSchoolRole(SchoolRole.TEACHER);

        User editor5 = new User();
        editor5.setAddress(new Address());
        editor5.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor5.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor5.setCreator(new User());
        editor5.setDeleted(true);
        editor5.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor5.setEditor(new User());
        editor5.setEmail("jane.doe@example.org");
        editor5.setFirstName("Jane");
        editor5.setId(1L);
        editor5.setLastName("Doe");
        editor5.setPassword("iloveyou");
        editor5.setSchoolRole(SchoolRole.TEACHER);

        Teacher teacher = new Teacher();
        teacher.setAddress(address4);
        teacher.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setCreator(creator5);
        teacher.setDeleted(true);
        teacher.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setEditor(editor5);
        teacher.setEmail("jane.doe@example.org");
        teacher.setFirstName("Jane");
        teacher.setId(1L);
        teacher.setLastName("Doe");
        teacher.setPassword("iloveyou");
        teacher.setSchoolRole(SchoolRole.TEACHER);

        Course course = new Course();
        course.setClassesWithThisCourse(new HashSet<>());
        course.setCourseSubject(CourseSubject.BIOLOGY);
        course.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        course.setCreator(creator2);
        course.setDeleted(true);
        course.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        course.setEditor(editor3);
        course.setId(1L);
        course.setName("Name");
        course.setSchool(school);
        course.setTeacher(teacher);

        User creator6 = new User();
        creator6.setAddress(new Address());
        creator6.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setCreator(new User());
        creator6.setDeleted(true);
        creator6.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setEditor(new User());
        creator6.setEmail("jane.doe@example.org");
        creator6.setFirstName("Jane");
        creator6.setId(1L);
        creator6.setLastName("Doe");
        creator6.setPassword("iloveyou");
        creator6.setSchoolRole(SchoolRole.TEACHER);

        User editor6 = new User();
        editor6.setAddress(new Address());
        editor6.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor6.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor6.setCreator(new User());
        editor6.setDeleted(true);
        editor6.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor6.setEditor(new User());
        editor6.setEmail("jane.doe@example.org");
        editor6.setFirstName("Jane");
        editor6.setId(1L);
        editor6.setLastName("Doe");
        editor6.setPassword("iloveyou");
        editor6.setSchoolRole(SchoolRole.TEACHER);

        Address address5 = new Address();
        address5.setCity("Oxford");
        address5.setCountryCode("GB");
        address5.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address5.setCreator(creator6);
        address5.setDeleted(true);
        address5.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address5.setEditor(editor6);
        address5.setHouseNumber("42");
        address5.setId(1L);
        address5.setStreet("Street");

        Address address6 = new Address();
        address6.setCity("Oxford");
        address6.setCountryCode("GB");
        address6.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address6.setCreator(new User());
        address6.setDeleted(true);
        address6.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address6.setEditor(new User());
        address6.setHouseNumber("42");
        address6.setId(1L);
        address6.setStreet("Street");

        User creator7 = new User();
        creator7.setAddress(new Address());
        creator7.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator7.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator7.setCreator(new User());
        creator7.setDeleted(true);
        creator7.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator7.setEditor(new User());
        creator7.setEmail("jane.doe@example.org");
        creator7.setFirstName("Jane");
        creator7.setId(1L);
        creator7.setLastName("Doe");
        creator7.setPassword("iloveyou");
        creator7.setSchoolRole(SchoolRole.TEACHER);

        User editor7 = new User();
        editor7.setAddress(new Address());
        editor7.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setCreator(new User());
        editor7.setDeleted(true);
        editor7.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setEditor(new User());
        editor7.setEmail("jane.doe@example.org");
        editor7.setFirstName("Jane");
        editor7.setId(1L);
        editor7.setLastName("Doe");
        editor7.setPassword("iloveyou");
        editor7.setSchoolRole(SchoolRole.TEACHER);

        User creator8 = new User();
        creator8.setAddress(address6);
        creator8.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setCreator(creator7);
        creator8.setDeleted(true);
        creator8.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setEditor(editor7);
        creator8.setEmail("jane.doe@example.org");
        creator8.setFirstName("Jane");
        creator8.setId(1L);
        creator8.setLastName("Doe");
        creator8.setPassword("iloveyou");
        creator8.setSchoolRole(SchoolRole.TEACHER);

        Address address7 = new Address();
        address7.setCity("Oxford");
        address7.setCountryCode("GB");
        address7.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address7.setCreator(new User());
        address7.setDeleted(true);
        address7.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address7.setEditor(new User());
        address7.setHouseNumber("42");
        address7.setId(1L);
        address7.setStreet("Street");

        User creator9 = new User();
        creator9.setAddress(new Address());
        creator9.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator9.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator9.setCreator(new User());
        creator9.setDeleted(true);
        creator9.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator9.setEditor(new User());
        creator9.setEmail("jane.doe@example.org");
        creator9.setFirstName("Jane");
        creator9.setId(1L);
        creator9.setLastName("Doe");
        creator9.setPassword("iloveyou");
        creator9.setSchoolRole(SchoolRole.TEACHER);

        User editor8 = new User();
        editor8.setAddress(new Address());
        editor8.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor8.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor8.setCreator(new User());
        editor8.setDeleted(true);
        editor8.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor8.setEditor(new User());
        editor8.setEmail("jane.doe@example.org");
        editor8.setFirstName("Jane");
        editor8.setId(1L);
        editor8.setLastName("Doe");
        editor8.setPassword("iloveyou");
        editor8.setSchoolRole(SchoolRole.TEACHER);

        User editor9 = new User();
        editor9.setAddress(address7);
        editor9.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setCreator(creator9);
        editor9.setDeleted(true);
        editor9.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setEditor(editor8);
        editor9.setEmail("jane.doe@example.org");
        editor9.setFirstName("Jane");
        editor9.setId(1L);
        editor9.setLastName("Doe");
        editor9.setPassword("iloveyou");
        editor9.setSchoolRole(SchoolRole.TEACHER);

        User creator10 = new User();
        creator10.setAddress(address5);
        creator10.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setCreator(creator8);
        creator10.setDeleted(true);
        creator10.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setEditor(editor9);
        creator10.setEmail("jane.doe@example.org");
        creator10.setFirstName("Jane");
        creator10.setId(1L);
        creator10.setLastName("Doe");
        creator10.setPassword("iloveyou");
        creator10.setSchoolRole(SchoolRole.TEACHER);

        User creator11 = new User();
        creator11.setAddress(new Address());
        creator11.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setCreator(new User());
        creator11.setDeleted(true);
        creator11.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setEditor(new User());
        creator11.setEmail("jane.doe@example.org");
        creator11.setFirstName("Jane");
        creator11.setId(1L);
        creator11.setLastName("Doe");
        creator11.setPassword("iloveyou");
        creator11.setSchoolRole(SchoolRole.TEACHER);

        User editor10 = new User();
        editor10.setAddress(new Address());
        editor10.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor10.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor10.setCreator(new User());
        editor10.setDeleted(true);
        editor10.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor10.setEditor(new User());
        editor10.setEmail("jane.doe@example.org");
        editor10.setFirstName("Jane");
        editor10.setId(1L);
        editor10.setLastName("Doe");
        editor10.setPassword("iloveyou");
        editor10.setSchoolRole(SchoolRole.TEACHER);

        Address address8 = new Address();
        address8.setCity("Oxford");
        address8.setCountryCode("GB");
        address8.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address8.setCreator(creator11);
        address8.setDeleted(true);
        address8.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address8.setEditor(editor10);
        address8.setHouseNumber("42");
        address8.setId(1L);
        address8.setStreet("Street");

        Address address9 = new Address();
        address9.setCity("Oxford");
        address9.setCountryCode("GB");
        address9.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address9.setCreator(new User());
        address9.setDeleted(true);
        address9.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address9.setEditor(new User());
        address9.setHouseNumber("42");
        address9.setId(1L);
        address9.setStreet("Street");

        User creator12 = new User();
        creator12.setAddress(new Address());
        creator12.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator12.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator12.setCreator(new User());
        creator12.setDeleted(true);
        creator12.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator12.setEditor(new User());
        creator12.setEmail("jane.doe@example.org");
        creator12.setFirstName("Jane");
        creator12.setId(1L);
        creator12.setLastName("Doe");
        creator12.setPassword("iloveyou");
        creator12.setSchoolRole(SchoolRole.TEACHER);

        User editor11 = new User();
        editor11.setAddress(new Address());
        editor11.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setCreator(new User());
        editor11.setDeleted(true);
        editor11.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setEditor(new User());
        editor11.setEmail("jane.doe@example.org");
        editor11.setFirstName("Jane");
        editor11.setId(1L);
        editor11.setLastName("Doe");
        editor11.setPassword("iloveyou");
        editor11.setSchoolRole(SchoolRole.TEACHER);

        User creator13 = new User();
        creator13.setAddress(address9);
        creator13.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator13.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator13.setCreator(creator12);
        creator13.setDeleted(true);
        creator13.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator13.setEditor(editor11);
        creator13.setEmail("jane.doe@example.org");
        creator13.setFirstName("Jane");
        creator13.setId(1L);
        creator13.setLastName("Doe");
        creator13.setPassword("iloveyou");
        creator13.setSchoolRole(SchoolRole.TEACHER);

        Address address10 = new Address();
        address10.setCity("Oxford");
        address10.setCountryCode("GB");
        address10.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address10.setCreator(new User());
        address10.setDeleted(true);
        address10.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address10.setEditor(new User());
        address10.setHouseNumber("42");
        address10.setId(1L);
        address10.setStreet("Street");

        User creator14 = new User();
        creator14.setAddress(new Address());
        creator14.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator14.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator14.setCreator(new User());
        creator14.setDeleted(true);
        creator14.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator14.setEditor(new User());
        creator14.setEmail("jane.doe@example.org");
        creator14.setFirstName("Jane");
        creator14.setId(1L);
        creator14.setLastName("Doe");
        creator14.setPassword("iloveyou");
        creator14.setSchoolRole(SchoolRole.TEACHER);

        User editor12 = new User();
        editor12.setAddress(new Address());
        editor12.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setCreator(new User());
        editor12.setDeleted(true);
        editor12.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setEditor(new User());
        editor12.setEmail("jane.doe@example.org");
        editor12.setFirstName("Jane");
        editor12.setId(1L);
        editor12.setLastName("Doe");
        editor12.setPassword("iloveyou");
        editor12.setSchoolRole(SchoolRole.TEACHER);

        User editor13 = new User();
        editor13.setAddress(address10);
        editor13.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor13.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor13.setCreator(creator14);
        editor13.setDeleted(true);
        editor13.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor13.setEditor(editor12);
        editor13.setEmail("jane.doe@example.org");
        editor13.setFirstName("Jane");
        editor13.setId(1L);
        editor13.setLastName("Doe");
        editor13.setPassword("iloveyou");
        editor13.setSchoolRole(SchoolRole.TEACHER);

        User editor14 = new User();
        editor14.setAddress(address8);
        editor14.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor14.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor14.setCreator(creator13);
        editor14.setDeleted(true);
        editor14.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor14.setEditor(editor13);
        editor14.setEmail("jane.doe@example.org");
        editor14.setFirstName("Jane");
        editor14.setId(1L);
        editor14.setLastName("Doe");
        editor14.setPassword("iloveyou");
        editor14.setSchoolRole(SchoolRole.TEACHER);

        User creator15 = new User();
        creator15.setAddress(new Address());
        creator15.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator15.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator15.setCreator(new User());
        creator15.setDeleted(true);
        creator15.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator15.setEditor(new User());
        creator15.setEmail("jane.doe@example.org");
        creator15.setFirstName("Jane");
        creator15.setId(1L);
        creator15.setLastName("Doe");
        creator15.setPassword("iloveyou");
        creator15.setSchoolRole(SchoolRole.TEACHER);

        User editor15 = new User();
        editor15.setAddress(new Address());
        editor15.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor15.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor15.setCreator(new User());
        editor15.setDeleted(true);
        editor15.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor15.setEditor(new User());
        editor15.setEmail("jane.doe@example.org");
        editor15.setFirstName("Jane");
        editor15.setId(1L);
        editor15.setLastName("Doe");
        editor15.setPassword("iloveyou");
        editor15.setSchoolRole(SchoolRole.TEACHER);

        Address address11 = new Address();
        address11.setCity("Oxford");
        address11.setCountryCode("GB");
        address11.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address11.setCreator(creator15);
        address11.setDeleted(true);
        address11.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address11.setEditor(editor15);
        address11.setHouseNumber("42");
        address11.setId(1L);
        address11.setStreet("Street");

        Address address12 = new Address();
        address12.setCity("Oxford");
        address12.setCountryCode("GB");
        address12.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address12.setCreator(new User());
        address12.setDeleted(true);
        address12.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address12.setEditor(new User());
        address12.setHouseNumber("42");
        address12.setId(1L);
        address12.setStreet("Street");

        User creator16 = new User();
        creator16.setAddress(new Address());
        creator16.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator16.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator16.setCreator(new User());
        creator16.setDeleted(true);
        creator16.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator16.setEditor(new User());
        creator16.setEmail("jane.doe@example.org");
        creator16.setFirstName("Jane");
        creator16.setId(1L);
        creator16.setLastName("Doe");
        creator16.setPassword("iloveyou");
        creator16.setSchoolRole(SchoolRole.TEACHER);

        User editor16 = new User();
        editor16.setAddress(new Address());
        editor16.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor16.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor16.setCreator(new User());
        editor16.setDeleted(true);
        editor16.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor16.setEditor(new User());
        editor16.setEmail("jane.doe@example.org");
        editor16.setFirstName("Jane");
        editor16.setId(1L);
        editor16.setLastName("Doe");
        editor16.setPassword("iloveyou");
        editor16.setSchoolRole(SchoolRole.TEACHER);

        User creator17 = new User();
        creator17.setAddress(address12);
        creator17.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator17.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator17.setCreator(creator16);
        creator17.setDeleted(true);
        creator17.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator17.setEditor(editor16);
        creator17.setEmail("jane.doe@example.org");
        creator17.setFirstName("Jane");
        creator17.setId(1L);
        creator17.setLastName("Doe");
        creator17.setPassword("iloveyou");
        creator17.setSchoolRole(SchoolRole.TEACHER);

        Address address13 = new Address();
        address13.setCity("Oxford");
        address13.setCountryCode("GB");
        address13.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address13.setCreator(new User());
        address13.setDeleted(true);
        address13.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address13.setEditor(new User());
        address13.setHouseNumber("42");
        address13.setId(1L);
        address13.setStreet("Street");

        User creator18 = new User();
        creator18.setAddress(new Address());
        creator18.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator18.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator18.setCreator(new User());
        creator18.setDeleted(true);
        creator18.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator18.setEditor(new User());
        creator18.setEmail("jane.doe@example.org");
        creator18.setFirstName("Jane");
        creator18.setId(1L);
        creator18.setLastName("Doe");
        creator18.setPassword("iloveyou");
        creator18.setSchoolRole(SchoolRole.TEACHER);

        User editor17 = new User();
        editor17.setAddress(new Address());
        editor17.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor17.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor17.setCreator(new User());
        editor17.setDeleted(true);
        editor17.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor17.setEditor(new User());
        editor17.setEmail("jane.doe@example.org");
        editor17.setFirstName("Jane");
        editor17.setId(1L);
        editor17.setLastName("Doe");
        editor17.setPassword("iloveyou");
        editor17.setSchoolRole(SchoolRole.TEACHER);

        User editor18 = new User();
        editor18.setAddress(address13);
        editor18.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor18.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor18.setCreator(creator18);
        editor18.setDeleted(true);
        editor18.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor18.setEditor(editor17);
        editor18.setEmail("jane.doe@example.org");
        editor18.setFirstName("Jane");
        editor18.setId(1L);
        editor18.setLastName("Doe");
        editor18.setPassword("iloveyou");
        editor18.setSchoolRole(SchoolRole.TEACHER);

        Teacher classLeader = new Teacher();
        classLeader.setAddress(new Address());
        classLeader.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        classLeader.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        classLeader.setCreator(new User());
        classLeader.setDeleted(true);
        classLeader.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        classLeader.setEditor(new User());
        classLeader.setEmail("jane.doe@example.org");
        classLeader.setFirstName("Jane");
        classLeader.setId(1L);
        classLeader.setLastName("Doe");
        classLeader.setPassword("iloveyou");
        classLeader.setSchoolRole(SchoolRole.TEACHER);

        User creator19 = new User();
        creator19.setAddress(new Address());
        creator19.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator19.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator19.setCreator(new User());
        creator19.setDeleted(true);
        creator19.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator19.setEditor(new User());
        creator19.setEmail("jane.doe@example.org");
        creator19.setFirstName("Jane");
        creator19.setId(1L);
        creator19.setLastName("Doe");
        creator19.setPassword("iloveyou");
        creator19.setSchoolRole(SchoolRole.TEACHER);

        User editor19 = new User();
        editor19.setAddress(new Address());
        editor19.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor19.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor19.setCreator(new User());
        editor19.setDeleted(true);
        editor19.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor19.setEditor(new User());
        editor19.setEmail("jane.doe@example.org");
        editor19.setFirstName("Jane");
        editor19.setId(1L);
        editor19.setLastName("Doe");
        editor19.setPassword("iloveyou");
        editor19.setSchoolRole(SchoolRole.TEACHER);

        School school2 = new School();
        school2.setAddress(new Address());
        school2.setClasses(new HashSet<>());
        school2.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        school2.setCreator(new User());
        school2.setDeleted(true);
        school2.setDirector(new Director());
        school2.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        school2.setEditor(new User());
        school2.setId(1L);
        school2.setName("Name");
        school2.setSchoolType(SchoolType.PRIMARY);

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassLeader(classLeader);
        schoolClass.setClassOf(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        schoolClass.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        schoolClass.setCreator(creator19);
        schoolClass.setDeleted(true);
        schoolClass.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        schoolClass.setEditor(editor19);
        schoolClass.setId(1L);
        schoolClass.setLetter('A');
        schoolClass.setSchool(school2);
        schoolClass.setStudents(new HashSet<>());
        schoolClass.setTaughtCourses(new HashSet<>());

        Student student = new Student();
        student.setAddress(address11);
        student.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        student.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        student.setCreator(creator17);
        student.setDeleted(true);
        student.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        student.setEditor(editor18);
        student.setEmail("jane.doe@example.org");
        student.setFirstName("Jane");
        student.setId(1L);
        student.setLastName("Doe");
        student.setParents(new HashSet<>());
        student.setPassword("iloveyou");
        student.setSchoolClass(schoolClass);
        student.setSchoolRole(SchoolRole.TEACHER);

        Grade grade = new Grade();
        grade.setCourse(course);
        grade.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        grade.setCreator(creator10);
        grade.setDeleted(true);
        grade.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        grade.setEditor(editor14);
        grade.setGrade(1);
        grade.setId(1L);
        grade.setStudent(student);

        ArrayList<Grade> gradeList = new ArrayList<>();
        gradeList.add(grade);
        when(gradeService.getAll(Mockito.<Pageable>any())).thenReturn(gradeList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/grade/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNo", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1))
                .param("sortBy", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(gradeEndpoint)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0"
                                        + ",\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,"
                                        + "\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate"
                                        + "\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null"
                                        + ",\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address"
                                        + "\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName"
                                        + "\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,"
                                        + "\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER"
                                        + "\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\""
                                        + ":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,"
                                        + "\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe"
                                        + "\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},"
                                        + "\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address"
                                        + "\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\""
                                        + ":null},\"deleted\":true,\"countryCode\":\"GB\",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},"
                                        + "\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited"
                                        + "\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email"
                                        + "\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\""
                                        + ":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,"
                                        + "\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,"
                                        + "\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,"
                                        + "\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber"
                                        + "\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,"
                                        + "\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\""
                                        + ":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane"
                                        + ".doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null"
                                        + ",\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,"
                                        + "\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"email\":\"jane.doe@example.org\","
                                        + "\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created"
                                        + "\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,"
                                        + "\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"countryCode\":\"GB\""
                                        + ",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"email"
                                        + "\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":1,\"created"
                                        + "\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null"
                                        + ",\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate"
                                        + "\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null"
                                        + ",\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address"
                                        + "\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName"
                                        + "\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,"
                                        + "\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER"
                                        + "\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\""
                                        + ":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,"
                                        + "\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe"
                                        + "\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},"
                                        + "\"deleted\":true,\"countryCode\":\"GB\",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},\"schoolRole\""
                                        + ":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor"
                                        + "\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\""
                                        + ":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\""
                                        + ":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,"
                                        + "\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{"
                                        + "\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\""
                                        + ":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,"
                                        + "\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\""
                                        + ":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\""
                                        + ":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,"
                                        + "\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\""
                                        + ":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id"
                                        + "\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null"
                                        + ",\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,"
                                        + "\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\""
                                        + ":null},\"deleted\":true,\"countryCode\":\"GB\",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},"
                                        + "\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited"
                                        + "\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email"
                                        + "\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\""
                                        + ":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,"
                                        + "\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,"
                                        + "\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,"
                                        + "\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber"
                                        + "\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,"
                                        + "\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\""
                                        + ":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane"
                                        + ".doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null"
                                        + ",\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,"
                                        + "\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"email\":\"jane.doe@example.org\","
                                        + "\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created"
                                        + "\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,"
                                        + "\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"countryCode\":\"GB\""
                                        + ",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"email"
                                        + "\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":1,\"created"
                                        + "\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null"
                                        + ",\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate"
                                        + "\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null"
                                        + ",\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address"
                                        + "\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName"
                                        + "\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,"
                                        + "\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER"
                                        + "\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\""
                                        + ":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,"
                                        + "\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe"
                                        + "\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},"
                                        + "\"deleted\":true,\"countryCode\":\"GB\",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},\"schoolRole\""
                                        + ":\"TEACHER\"},\"deleted\":true,\"grade\":1,\"course\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created"
                                        + "\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null"
                                        + ",\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate"
                                        + "\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null"
                                        + ",\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address"
                                        + "\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName"
                                        + "\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,"
                                        + "\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER"
                                        + "\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\""
                                        + ":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,"
                                        + "\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe"
                                        + "\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},"
                                        + "\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address"
                                        + "\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\""
                                        + ":null},\"deleted\":true,\"countryCode\":\"GB\",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},"
                                        + "\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited"
                                        + "\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email"
                                        + "\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\""
                                        + ":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,"
                                        + "\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,"
                                        + "\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,"
                                        + "\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber"
                                        + "\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,"
                                        + "\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\""
                                        + ":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane"
                                        + ".doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null"
                                        + ",\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,"
                                        + "\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"email\":\"jane.doe@example.org\","
                                        + "\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created"
                                        + "\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,"
                                        + "\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"countryCode\":\"GB\""
                                        + ",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"name\""
                                        + ":\"Name\",\"courseSubject\":\"BIOLOGY\",\"school\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\""
                                        + ":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\""
                                        + ":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null"
                                        + "},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email"
                                        + "\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\""
                                        + ":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id"
                                        + "\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,"
                                        + "\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,"
                                        + "\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false"
                                        + ",\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},"
                                        + "\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\""
                                        + ":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\""
                                        + ":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id"
                                        + "\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,"
                                        + "\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"name\":\"Name\","
                                        + "\"schoolType\":\"PRIMARY\",\"address\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited"
                                        + "\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,"
                                        + "\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,"
                                        + "\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate"
                                        + "\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"countryCode\":\"GB\",\"houseNumber\":\"42\",\"city\""
                                        + ":\"Oxford\",\"street\":\"Street\"},\"director\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null"
                                        + ",\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\""
                                        + ":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\""
                                        + ":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,"
                                        + "\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\","
                                        + "\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,"
                                        + "\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street"
                                        + "\":null},\"schoolRole\":\"TEACHER\",\"school\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\""
                                        + ":null,\"deleted\":false,\"name\":null,\"schoolType\":null,\"address\":null,\"director\":null,\"classes\":null}},"
                                        + "\"classes\":[]},\"teacher\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor"
                                        + "\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,"
                                        + "\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0"
                                        + ",\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\""
                                        + ":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane"
                                        + ".doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null"
                                        + ",\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,"
                                        + "\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\""
                                        + ":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,"
                                        + "\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0"
                                        + ",\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\""
                                        + ":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane"
                                        + ".doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null"
                                        + ",\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,"
                                        + "\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"email\":\"jane.doe@example.org\","
                                        + "\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created"
                                        + "\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,"
                                        + "\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"countryCode\":\"GB\""
                                        + ",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},\"schoolRole\":\"TEACHER\"},\"classesWithThisCourse"
                                        + "\":[]},\"student\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id"
                                        + "\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null"
                                        + ",\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,"
                                        + "\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\""
                                        + ":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\""
                                        + ":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,"
                                        + "\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{"
                                        + "\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\""
                                        + ":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,"
                                        + "\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\""
                                        + ":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\""
                                        + ":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,"
                                        + "\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\""
                                        + ":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id"
                                        + "\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null"
                                        + ",\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,"
                                        + "\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\""
                                        + ":null},\"deleted\":true,\"countryCode\":\"GB\",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},"
                                        + "\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited"
                                        + "\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email"
                                        + "\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\""
                                        + ":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,"
                                        + "\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,"
                                        + "\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,"
                                        + "\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber"
                                        + "\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,"
                                        + "\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\""
                                        + ":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane"
                                        + ".doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null"
                                        + ",\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,"
                                        + "\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"email\":\"jane.doe@example.org\","
                                        + "\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":"
                                        + "{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName"
                                        + "\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created"
                                        + "\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,"
                                        + "\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,\"countryCode\":\"GB\""
                                        + ",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"email"
                                        + "\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":1,\"created"
                                        + "\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null"
                                        + ",\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate"
                                        + "\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null"
                                        + ",\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address"
                                        + "\":null,\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName"
                                        + "\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,"
                                        + "\"deleted\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER"
                                        + "\"},\"creator\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\""
                                        + ":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,"
                                        + "\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null},\"deleted\":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe"
                                        + "\",\"birthDate\":0,\"address\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted"
                                        + "\":false,\"countryCode\":null,\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},"
                                        + "\"deleted\":true,\"countryCode\":\"GB\",\"houseNumber\":\"42\",\"city\":\"Oxford\",\"street\":\"Street\"},\"schoolRole\""
                                        + ":\"TEACHER\",\"parents\":[],\"schoolClass\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":1,\"created\":0,"
                                        + "\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false"
                                        + ",\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},"
                                        + "\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\""
                                        + ":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\""
                                        + ":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id"
                                        + "\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,"
                                        + "\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"creator\":{\"id\":1,\"created\":0,"
                                        + "\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false"
                                        + ",\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},"
                                        + "\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\""
                                        + ":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\""
                                        + ":true,\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id"
                                        + "\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,"
                                        + "\"houseNumber\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"deleted\":true,\"classOf\":0,"
                                        + "\"letter\":\"A\",\"school\":{\"id\":1,\"created\":0,\"edited\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,"
                                        + "\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate"
                                        + "\":null,\"address\":null,\"schoolRole\":null},\"creator\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null"
                                        + ",\"creator\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address"
                                        + "\":null,\"schoolRole\":null},\"deleted\":true,\"name\":\"Name\",\"schoolType\":\"PRIMARY\",\"address\":{\"id\":0,\"created"
                                        + "\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber\""
                                        + ":null,\"city\":null,\"street\":null},\"director\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator"
                                        + "\":null,\"deleted\":false,\"email\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null"
                                        + ",\"schoolRole\":null,\"school\":null},\"classes\":[]},\"students\":[],\"classLeader\":{\"id\":1,\"created\":0,\"edited"
                                        + "\":0,\"editor\":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email"
                                        + "\":null,\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"creator\""
                                        + ":{\"id\":0,\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"email\":null,"
                                        + "\"firstName\":null,\"lastName\":null,\"birthDate\":null,\"address\":null,\"schoolRole\":null},\"deleted\":true,"
                                        + "\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"birthDate\":0,\"address\":{\"id\":0,"
                                        + "\"created\":null,\"edited\":null,\"editor\":null,\"creator\":null,\"deleted\":false,\"countryCode\":null,\"houseNumber"
                                        + "\":null,\"city\":null,\"street\":null},\"schoolRole\":\"TEACHER\"},\"taughtCourses\":[]}}}]"));
    }
}
