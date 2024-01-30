package com.example.schooljournal.data.entity.user;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#getUsername()}
     *   <li>{@link User#isAccountNonExpired()}
     *   <li>{@link User#isAccountNonLocked()}
     *   <li>{@link User#isCredentialsNonExpired()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        User user = new User();

        // Act
        String actualUsername = user.getUsername();
        boolean actualIsAccountNonExpiredResult = user.isAccountNonExpired();
        boolean actualIsAccountNonLockedResult = user.isAccountNonLocked();

        // Assert
        assertNull(actualUsername);
        assertTrue(actualIsAccountNonExpiredResult);
        assertTrue(actualIsAccountNonLockedResult);
        assertTrue(user.isCredentialsNonExpired());
    }
}
