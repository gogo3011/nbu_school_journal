package com.example.schooljournal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.schooljournal.data.repository.AbsenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AbsenceService.class})
@ExtendWith(SpringExtension.class)
class AbsenceServiceTest {
    @MockBean
    private AbsenceRepository absenceRepository;

    @Autowired
    private AbsenceService absenceService;

    /**
     * Method under test: {@link AbsenceService#count()}
     */
    @Test
    void testCount() {
        // Arrange
        when(absenceRepository.count()).thenReturn(3L);

        // Act
        long actualCountResult = absenceService.count();

        // Assert
        verify(absenceRepository).count();
        assertEquals(3L, actualCountResult);
    }
}
