package com.example.schooljournal.data.entity.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.schooljournal.data.entity.user.enums.SchoolRole;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ViewTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link View#View()}
     *   <li>{@link View#setComponentIdsOrdered(List)}
     *   <li>{@link View#setRole(SchoolRole)}
     *   <li>{@link View#toString()}
     *   <li>{@link View#getComponentIdsOrdered()}
     *   <li>{@link View#getRole()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        View actualView = new View();
        ArrayList<String> componentIdsOrdered = new ArrayList<>();
        actualView.setComponentIdsOrdered(componentIdsOrdered);
        actualView.setRole(SchoolRole.TEACHER);
        String actualToStringResult = actualView.toString();
        List<String> actualComponentIdsOrdered = actualView.getComponentIdsOrdered();

        // Assert that nothing has changed
        assertEquals("View(role=TEACHER, componentIdsOrdered=[])", actualToStringResult);
        assertEquals(SchoolRole.TEACHER, actualView.getRole());
        assertSame(componentIdsOrdered, actualComponentIdsOrdered);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link View#View(SchoolRole, List)}
     *   <li>{@link View#setComponentIdsOrdered(List)}
     *   <li>{@link View#setRole(SchoolRole)}
     *   <li>{@link View#toString()}
     *   <li>{@link View#getComponentIdsOrdered()}
     *   <li>{@link View#getRole()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<String> componentIdsOrdered = new ArrayList<>();

        // Act
        View actualView = new View(SchoolRole.TEACHER, componentIdsOrdered);
        ArrayList<String> componentIdsOrdered2 = new ArrayList<>();
        actualView.setComponentIdsOrdered(componentIdsOrdered2);
        actualView.setRole(SchoolRole.TEACHER);
        String actualToStringResult = actualView.toString();
        List<String> actualComponentIdsOrdered = actualView.getComponentIdsOrdered();

        // Assert that nothing has changed
        assertEquals("View(role=TEACHER, componentIdsOrdered=[])", actualToStringResult);
        assertEquals(SchoolRole.TEACHER, actualView.getRole());
        assertEquals(componentIdsOrdered, actualComponentIdsOrdered);
        assertSame(componentIdsOrdered2, actualComponentIdsOrdered);
    }
}
