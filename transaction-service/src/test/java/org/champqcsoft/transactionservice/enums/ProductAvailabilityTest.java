package org.champqcsoft.transactionservice.enums;

import org.champqcsoft.transactionservice.commons.enums.ProductAvailability;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductAvailabilityTest {

    @Test
    public void testEnumValues() {
        ProductAvailability[] values = ProductAvailability.values();
        assertEquals(3, values.length); // Ensure all enum values are present

        assertTrue(containsEnumValue(values, "Available"));
        assertTrue(containsEnumValue(values, "NonAvailable"));
        assertTrue(containsEnumValue(values, "LastRemaining"));
    }

    // Helper method to check if the enum values contain a specific string
    private boolean containsEnumValue(ProductAvailability[] values, String value) {
        for (ProductAvailability availability : values) {
            if (availability.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
