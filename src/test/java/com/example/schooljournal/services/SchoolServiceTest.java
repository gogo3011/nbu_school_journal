package com.example.schooljournal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.schooljournal.data.repository.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SchoolService.class})
@ExtendWith(SpringExtension.class)
class SchoolServiceTest {
    @MockBean
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolService schoolService;

    /**
     * Method under test: {@link SchoolService#count()}
     */
    @Test
    void testCount() {
        // Arrange
        when(schoolRepository.count()).thenReturn(3L);

        // Act
        long actualCountResult = schoolService.count();

        // Assert
        verify(schoolRepository).count();
        assertEquals(3L, actualCountResult);
    }
}
