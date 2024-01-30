package com.example.schooljournal.data.entity.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AddressTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Address}
     *   <li>{@link Address#setCity(String)}
     *   <li>{@link Address#setCountryCode(String)}
     *   <li>{@link Address#setHouseNumber(String)}
     *   <li>{@link Address#setStreet(String)}
     *   <li>{@link Address#getCity()}
     *   <li>{@link Address#getCountryCode()}
     *   <li>{@link Address#getHouseNumber()}
     *   <li>{@link Address#getStreet()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Address actualAddress = new Address();
        actualAddress.setCity("Oxford");
        actualAddress.setCountryCode("GB");
        actualAddress.setHouseNumber("42");
        actualAddress.setStreet("Street");
        String actualCity = actualAddress.getCity();
        String actualCountryCode = actualAddress.getCountryCode();
        String actualHouseNumber = actualAddress.getHouseNumber();

        // Assert that nothing has changed
        assertEquals("42", actualHouseNumber);
        assertEquals("GB", actualCountryCode);
        assertEquals("Oxford", actualCity);
        assertEquals("Street", actualAddress.getStreet());
    }
}
