package org.champqcsoft.transactionservice.enums;

import org.champqcsoft.transactionservice.commons.enums.PaymentMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentMethodTest {

    @Test
    public void testEnumValues() {
        PaymentMethod[] values = PaymentMethod.values();
        assertEquals(3, values.length); // Ensure all enum values are present

        assertTrue(containsEnumValue(values, "Cash"));
        assertTrue(containsEnumValue(values, "Credit"));
        assertTrue(containsEnumValue(values, "Debit"));
    }

    // Helper method to check if the enum values contain a specific string
    private boolean containsEnumValue(PaymentMethod[] values, String value) {
        for (PaymentMethod method : values) {
            if (method.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
