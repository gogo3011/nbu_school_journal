package com.example.schooljournal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.schooljournal.data.repository.SchoolClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SchoolClassService.class})
@ExtendWith(SpringExtension.class)
class SchoolClassServiceTest {
    @MockBean
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private SchoolClassService schoolClassService;

    /**
     * Method under test: {@link SchoolClassService#count()}
     */
    @Test
    void testCount() {
        // Arrange
        when(schoolClassRepository.count()).thenReturn(3L);

        // Act
        long actualCountResult = schoolClassService.count();

        // Assert
        verify(schoolClassRepository).count();
        assertEquals(3L, actualCountResult);
    }
}
