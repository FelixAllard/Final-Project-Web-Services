package org.champqcsoft.employeeservice.enums;

import org.champqcsoft.employeeservice.commons.enums.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleTest {

    @Test
    public void testEnumValues() {
        Role[] values = Role.values();
        assertEquals(3, values.length); // Ensure all enum values are present

        assertTrue(containsEnumValue(values, "Janitor"));
        assertTrue(containsEnumValue(values, "Cashier"));
        assertTrue(containsEnumValue(values, "Manager"));
    }

    // Helper method to check if the enum values contain a specific string
    private boolean containsEnumValue(Role[] values, String value) {
        for (Role role : values) {
            if (role.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
