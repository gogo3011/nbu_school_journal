package com.example.schooljournal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.schooljournal.data.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseService.class})
@ExtendWith(SpringExtension.class)
class CourseServiceTest {
    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    /**
     * Method under test: {@link CourseService#count()}
     */
    @Test
    void testCount() {
        // Arrange
        when(courseRepository.count()).thenReturn(3L);

        // Act
        long actualCountResult = courseService.count();

        // Assert
        verify(courseRepository).count();
        assertEquals(3L, actualCountResult);
    }
}
