package com.example.schooljournal.data.entity.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class ParentTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Parent}
     *   <li>{@link Parent#setStudents(Set)}
     *   <li>{@link Parent#toString()}
     *   <li>{@link Parent#getStudents()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Parent actualParent = new Parent();
        HashSet<Student> students = new HashSet<>();
        actualParent.setStudents(students);
        String actualToStringResult = actualParent.toString();

        // Assert that nothing has changed
        assertEquals("Parent(students=[])", actualToStringResult);
        assertSame(students, actualParent.getStudents());
    }
}
