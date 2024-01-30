package com.example.schooljournal.data.entity.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TeacherTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Teacher}
     *   <li>{@link Teacher#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals("Teacher()", (new Teacher()).toString());
    }
}
