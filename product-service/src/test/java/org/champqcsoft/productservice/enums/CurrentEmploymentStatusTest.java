package org.champqcsoft.productservice.enums;

import org.champqcsoft.productservice.commons.enums.CurrentEmploymentStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrentEmploymentStatusTest {

    @Test
    public void testEnumValues() {
        CurrentEmploymentStatus[] values = CurrentEmploymentStatus.values();
        assertEquals(4, values.length); // Ensure all enum values are present

        assertTrue(containsEnumValue(values, "Employed"));
        assertTrue(containsEnumValue(values, "Fired"));
        assertTrue(containsEnumValue(values, "Suspended"));
        assertTrue(containsEnumValue(values, "InVacation"));
    }

    // Helper method to check if the enum values contain a specific string
    private boolean containsEnumValue(CurrentEmploymentStatus[] values, String value) {
        for (CurrentEmploymentStatus status : values) {
            if (status.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}

