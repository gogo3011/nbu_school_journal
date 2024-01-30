package com.example.schooljournal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.schooljournal.data.repository.GradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GradeService.class})
@ExtendWith(SpringExtension.class)
class GradeServiceTest {
    @MockBean
    private CourseService courseService;

    @MockBean
    private GradeRepository gradeRepository;

    @Autowired
    private GradeService gradeService;

    @MockBean
    private ParentService parentService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;

    /**
     * Method under test: {@link GradeService#count()}
     */
    @Test
    void testCount() {
        // Arrange
        when(gradeRepository.count()).thenReturn(3L);

        // Act
        long actualCountResult = gradeService.count();

        // Assert
        verify(gradeRepository).count();
        assertEquals(3L, actualCountResult);
    }
}
