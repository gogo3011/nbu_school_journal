package com.example.schooljournal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.schooljournal.data.entity.core.Address;
import com.example.schooljournal.data.entity.user.Teacher;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.example.schooljournal.data.repository.TeacherRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TeacherService.class})
@ExtendWith(SpringExtension.class)
class TeacherServiceTest {
    @MockBean
    private AuthService authService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link TeacherService#getAll(Integer, Integer, String)}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(teacherRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        List<Teacher> actualAll = teacherService.getAll(1, 3, "Sort By");

        // Assert
        verify(teacherRepository).findAll(Mockito.<Pageable>any());
        assertTrue(actualAll.isEmpty());
    }

    /**
     * Method under test: {@link TeacherService#getAll(Integer, Integer, String)}
     */
    @Test
    void testGetAll2() {
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
        address3.setCreator(creator2);
        address3.setDeleted(true);
        address3.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address3.setEditor(editor3);
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

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setCountryCode("GB");
        address4.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address4.setCreator(creator4);
        address4.setDeleted(true);
        address4.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address4.setEditor(editor4);
        address4.setHouseNumber("42");
        address4.setId(1L);
        address4.setStreet("Street");

        Address address5 = new Address();
        address5.setCity("Oxford");
        address5.setCountryCode("GB");
        address5.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address5.setCreator(new User());
        address5.setDeleted(true);
        address5.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address5.setEditor(new User());
        address5.setHouseNumber("42");
        address5.setId(1L);
        address5.setStreet("Street");

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

        User creator6 = new User();
        creator6.setAddress(address5);
        creator6.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setCreator(creator5);
        creator6.setDeleted(true);
        creator6.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setEditor(editor5);
        creator6.setEmail("jane.doe@example.org");
        creator6.setFirstName("Jane");
        creator6.setId(1L);
        creator6.setLastName("Doe");
        creator6.setPassword("iloveyou");
        creator6.setSchoolRole(SchoolRole.TEACHER);

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

        User editor7 = new User();
        editor7.setAddress(address6);
        editor7.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setCreator(creator7);
        editor7.setDeleted(true);
        editor7.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setEditor(editor6);
        editor7.setEmail("jane.doe@example.org");
        editor7.setFirstName("Jane");
        editor7.setId(1L);
        editor7.setLastName("Doe");
        editor7.setPassword("iloveyou");
        editor7.setSchoolRole(SchoolRole.TEACHER);

        User creator8 = new User();
        creator8.setAddress(address4);
        creator8.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setCreator(creator6);
        creator8.setDeleted(true);
        creator8.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setEditor(editor7);
        creator8.setEmail("jane.doe@example.org");
        creator8.setFirstName("Jane");
        creator8.setId(1L);
        creator8.setLastName("Doe");
        creator8.setPassword("iloveyou");
        creator8.setSchoolRole(SchoolRole.TEACHER);

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

        Address address7 = new Address();
        address7.setCity("Oxford");
        address7.setCountryCode("GB");
        address7.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address7.setCreator(creator9);
        address7.setDeleted(true);
        address7.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address7.setEditor(editor8);
        address7.setHouseNumber("42");
        address7.setId(1L);
        address7.setStreet("Street");

        Address address8 = new Address();
        address8.setCity("Oxford");
        address8.setCountryCode("GB");
        address8.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address8.setCreator(new User());
        address8.setDeleted(true);
        address8.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address8.setEditor(new User());
        address8.setHouseNumber("42");
        address8.setId(1L);
        address8.setStreet("Street");

        User creator10 = new User();
        creator10.setAddress(new Address());
        creator10.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setCreator(new User());
        creator10.setDeleted(true);
        creator10.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setEditor(new User());
        creator10.setEmail("jane.doe@example.org");
        creator10.setFirstName("Jane");
        creator10.setId(1L);
        creator10.setLastName("Doe");
        creator10.setPassword("iloveyou");
        creator10.setSchoolRole(SchoolRole.TEACHER);

        User editor9 = new User();
        editor9.setAddress(new Address());
        editor9.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setCreator(new User());
        editor9.setDeleted(true);
        editor9.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setEditor(new User());
        editor9.setEmail("jane.doe@example.org");
        editor9.setFirstName("Jane");
        editor9.setId(1L);
        editor9.setLastName("Doe");
        editor9.setPassword("iloveyou");
        editor9.setSchoolRole(SchoolRole.TEACHER);

        User creator11 = new User();
        creator11.setAddress(address8);
        creator11.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setCreator(creator10);
        creator11.setDeleted(true);
        creator11.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setEditor(editor9);
        creator11.setEmail("jane.doe@example.org");
        creator11.setFirstName("Jane");
        creator11.setId(1L);
        creator11.setLastName("Doe");
        creator11.setPassword("iloveyou");
        creator11.setSchoolRole(SchoolRole.TEACHER);

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

        User editor11 = new User();
        editor11.setAddress(address9);
        editor11.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setCreator(creator12);
        editor11.setDeleted(true);
        editor11.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setEditor(editor10);
        editor11.setEmail("jane.doe@example.org");
        editor11.setFirstName("Jane");
        editor11.setId(1L);
        editor11.setLastName("Doe");
        editor11.setPassword("iloveyou");
        editor11.setSchoolRole(SchoolRole.TEACHER);

        User editor12 = new User();
        editor12.setAddress(address7);
        editor12.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setCreator(creator11);
        editor12.setDeleted(true);
        editor12.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setEditor(editor11);
        editor12.setEmail("jane.doe@example.org");
        editor12.setFirstName("Jane");
        editor12.setId(1L);
        editor12.setLastName("Doe");
        editor12.setPassword("iloveyou");
        editor12.setSchoolRole(SchoolRole.TEACHER);

        Teacher teacher = new Teacher();
        teacher.setAddress(address3);
        teacher.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setCreator(creator8);
        teacher.setDeleted(true);
        teacher.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setEditor(editor12);
        teacher.setEmail("jane.doe@example.org");
        teacher.setFirstName("Jane");
        teacher.setId(1L);
        teacher.setLastName("Doe");
        teacher.setPassword("iloveyou");
        teacher.setSchoolRole(SchoolRole.TEACHER);

        ArrayList<Teacher> content = new ArrayList<>();
        content.add(teacher);
        PageImpl<Teacher> pageImpl = new PageImpl<>(content);
        when(teacherRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        List<Teacher> actualAll = teacherService.getAll(1, 3, "Sort By");

        // Assert
        verify(teacherRepository).findAll(Mockito.<Pageable>any());
        assertEquals(1, actualAll.size());
    }

    /**
     * Method under test: {@link TeacherService#getAll(Pageable)}
     */
    @Test
    void testGetAll3() {
        // Arrange
        when(teacherRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        List<Teacher> actualAll = teacherService.getAll(null);

        // Assert
        verify(teacherRepository).findAll(Mockito.<Pageable>any());
        assertTrue(actualAll.isEmpty());
    }

    /**
     * Method under test: {@link TeacherService#getAll(Pageable)}
     */
    @Test
    void testGetAll4() {
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
        address3.setCreator(creator2);
        address3.setDeleted(true);
        address3.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address3.setEditor(editor3);
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

        Address address4 = new Address();
        address4.setCity("Oxford");
        address4.setCountryCode("GB");
        address4.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address4.setCreator(creator4);
        address4.setDeleted(true);
        address4.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address4.setEditor(editor4);
        address4.setHouseNumber("42");
        address4.setId(1L);
        address4.setStreet("Street");

        Address address5 = new Address();
        address5.setCity("Oxford");
        address5.setCountryCode("GB");
        address5.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address5.setCreator(new User());
        address5.setDeleted(true);
        address5.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address5.setEditor(new User());
        address5.setHouseNumber("42");
        address5.setId(1L);
        address5.setStreet("Street");

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

        User creator6 = new User();
        creator6.setAddress(address5);
        creator6.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setCreator(creator5);
        creator6.setDeleted(true);
        creator6.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator6.setEditor(editor5);
        creator6.setEmail("jane.doe@example.org");
        creator6.setFirstName("Jane");
        creator6.setId(1L);
        creator6.setLastName("Doe");
        creator6.setPassword("iloveyou");
        creator6.setSchoolRole(SchoolRole.TEACHER);

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

        User editor7 = new User();
        editor7.setAddress(address6);
        editor7.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setCreator(creator7);
        editor7.setDeleted(true);
        editor7.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor7.setEditor(editor6);
        editor7.setEmail("jane.doe@example.org");
        editor7.setFirstName("Jane");
        editor7.setId(1L);
        editor7.setLastName("Doe");
        editor7.setPassword("iloveyou");
        editor7.setSchoolRole(SchoolRole.TEACHER);

        User creator8 = new User();
        creator8.setAddress(address4);
        creator8.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setCreator(creator6);
        creator8.setDeleted(true);
        creator8.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator8.setEditor(editor7);
        creator8.setEmail("jane.doe@example.org");
        creator8.setFirstName("Jane");
        creator8.setId(1L);
        creator8.setLastName("Doe");
        creator8.setPassword("iloveyou");
        creator8.setSchoolRole(SchoolRole.TEACHER);

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

        Address address7 = new Address();
        address7.setCity("Oxford");
        address7.setCountryCode("GB");
        address7.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address7.setCreator(creator9);
        address7.setDeleted(true);
        address7.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address7.setEditor(editor8);
        address7.setHouseNumber("42");
        address7.setId(1L);
        address7.setStreet("Street");

        Address address8 = new Address();
        address8.setCity("Oxford");
        address8.setCountryCode("GB");
        address8.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address8.setCreator(new User());
        address8.setDeleted(true);
        address8.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        address8.setEditor(new User());
        address8.setHouseNumber("42");
        address8.setId(1L);
        address8.setStreet("Street");

        User creator10 = new User();
        creator10.setAddress(new Address());
        creator10.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setCreator(new User());
        creator10.setDeleted(true);
        creator10.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator10.setEditor(new User());
        creator10.setEmail("jane.doe@example.org");
        creator10.setFirstName("Jane");
        creator10.setId(1L);
        creator10.setLastName("Doe");
        creator10.setPassword("iloveyou");
        creator10.setSchoolRole(SchoolRole.TEACHER);

        User editor9 = new User();
        editor9.setAddress(new Address());
        editor9.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setCreator(new User());
        editor9.setDeleted(true);
        editor9.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor9.setEditor(new User());
        editor9.setEmail("jane.doe@example.org");
        editor9.setFirstName("Jane");
        editor9.setId(1L);
        editor9.setLastName("Doe");
        editor9.setPassword("iloveyou");
        editor9.setSchoolRole(SchoolRole.TEACHER);

        User creator11 = new User();
        creator11.setAddress(address8);
        creator11.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setCreator(creator10);
        creator11.setDeleted(true);
        creator11.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        creator11.setEditor(editor9);
        creator11.setEmail("jane.doe@example.org");
        creator11.setFirstName("Jane");
        creator11.setId(1L);
        creator11.setLastName("Doe");
        creator11.setPassword("iloveyou");
        creator11.setSchoolRole(SchoolRole.TEACHER);

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

        User editor11 = new User();
        editor11.setAddress(address9);
        editor11.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setCreator(creator12);
        editor11.setDeleted(true);
        editor11.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor11.setEditor(editor10);
        editor11.setEmail("jane.doe@example.org");
        editor11.setFirstName("Jane");
        editor11.setId(1L);
        editor11.setLastName("Doe");
        editor11.setPassword("iloveyou");
        editor11.setSchoolRole(SchoolRole.TEACHER);

        User editor12 = new User();
        editor12.setAddress(address7);
        editor12.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setCreator(creator11);
        editor12.setDeleted(true);
        editor12.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        editor12.setEditor(editor11);
        editor12.setEmail("jane.doe@example.org");
        editor12.setFirstName("Jane");
        editor12.setId(1L);
        editor12.setLastName("Doe");
        editor12.setPassword("iloveyou");
        editor12.setSchoolRole(SchoolRole.TEACHER);

        Teacher teacher = new Teacher();
        teacher.setAddress(address3);
        teacher.setBirthDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setCreated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setCreator(creator8);
        teacher.setDeleted(true);
        teacher.setEdited(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        teacher.setEditor(editor12);
        teacher.setEmail("jane.doe@example.org");
        teacher.setFirstName("Jane");
        teacher.setId(1L);
        teacher.setLastName("Doe");
        teacher.setPassword("iloveyou");
        teacher.setSchoolRole(SchoolRole.TEACHER);

        ArrayList<Teacher> content = new ArrayList<>();
        content.add(teacher);
        PageImpl<Teacher> pageImpl = new PageImpl<>(content);
        when(teacherRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        List<Teacher> actualAll = teacherService.getAll(null);

        // Assert
        verify(teacherRepository).findAll(Mockito.<Pageable>any());
        assertEquals(1, actualAll.size());
    }
}
