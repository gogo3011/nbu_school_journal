package com.example.schooljournal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.schooljournal.data.repository.ParentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ParentService.class})
@ExtendWith(SpringExtension.class)
class ParentServiceTest {
    @MockBean
    private ParentRepository parentRepository;

    @Autowired
    private ParentService parentService;

    /**
     * Method under test: {@link ParentService#count()}
     */
    @Test
    void testCount() {
        // Arrange
        when(parentRepository.count()).thenReturn(3L);

        // Act
        long actualCountResult = parentService.count();

        // Assert
        verify(parentRepository).count();
        assertEquals(3L, actualCountResult);
    }
}
