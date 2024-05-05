package org.champqcsoft.customerservice.commons.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DaysOfTheWeekTest {

    @Test
    public void testEnumValues() {
        DaysOfTheWeek[] values = DaysOfTheWeek.values();
        assertEquals(127, values.length); // Ensure all enum values are present

        // Test individual enum values
        assertTrue(containsEnumValue(values, "Monday"));
        assertTrue(containsEnumValue(values, "Tuesday"));
        assertTrue(containsEnumValue(values, "Wednesday"));
        assertTrue(containsEnumValue(values, "Thursday"));
        assertTrue(containsEnumValue(values, "Friday"));
        assertTrue(containsEnumValue(values, "Saturday"));
        assertTrue(containsEnumValue(values, "Sunday"));
        // Add more specific tests for your enum values as needed
    }

    // Helper method to check if the enum values contain a specific string
    private boolean containsEnumValue(DaysOfTheWeek[] values, String value) {
        for (DaysOfTheWeek day : values) {
            if (day.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
