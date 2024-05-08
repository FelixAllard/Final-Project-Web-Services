package org.champqcsoft.employeeservice.dataaccesslayer;

import org.champqcsoft.employeeservice.commons.enums.*;
import org.champqcsoft.employeeservice.commons.identifiers.EmployeeIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class EmployeeRepositoryIntegrationTest {
    @Autowired
    private EmployeeRepository EmployeeRepository;

    @BeforeEach
    public void setUpDb() { EmployeeRepository.deleteAll(); }

    @Test
    public void whenEmployeeExists_ReturnEmployeeByEmployeeId(){
        //arrange
        Employee Employee1;
        Employee1 = new Employee(1, "name", 20, new EmployeeIdentifier(), CurrentEmploymentStatus.Employed,
                new DaysNonAvailable(true, new Date(3, 5, 6, 7, 4, 2),
                        "family", DaysOfTheWeek.Friday_Saturday),
                new Salary(new Price(20.20, Currency.CAD), PaymentMethod.Cash)
        );
        EmployeeRepository.save(Employee1);

        //act
        Employee Employee1Found = EmployeeRepository.findEmployeeByEmployeeIdentifier_EmployeeId(Employee1.getEmployeeIdentifier().getEmployeeId());

        //assert
        assertNotNull(Employee1Found);
        assertEquals(Employee1Found.getId(), Employee1.getId());
        assertEquals(Employee1Found.getName(), Employee1.getName());
        assertEquals(Employee1Found.getEmployeeIdentifier().getEmployeeId(), Employee1.getEmployeeIdentifier().getEmployeeId());
        assertEquals(Employee1Found.getCurrentEmploymentStatus(), Employee1.getCurrentEmploymentStatus());
        assertEquals(Employee1Found.getDaysNonAvailable().getFullDay(), Employee1.getDaysNonAvailable().getFullDay());
        assertEquals(Employee1Found.getDaysNonAvailable().getNonAvailability().getStartDay(), Employee1.getDaysNonAvailable().getNonAvailability().getStartDay());
        assertEquals(Employee1Found.getDaysNonAvailable().getNonAvailability().getStartMonth(), Employee1.getDaysNonAvailable().getNonAvailability().getStartMonth());
        assertEquals(Employee1Found.getDaysNonAvailable().getNonAvailability().getStartYear(), Employee1.getDaysNonAvailable().getNonAvailability().getStartYear());
        assertEquals(Employee1Found.getDaysNonAvailable().getNonAvailability().getEndDay(), Employee1.getDaysNonAvailable().getNonAvailability().getEndDay());
        assertEquals(Employee1Found.getDaysNonAvailable().getNonAvailability().getEndMonth(), Employee1.getDaysNonAvailable().getNonAvailability().getEndMonth());
        assertEquals(Employee1Found.getDaysNonAvailable().getNonAvailability().getEndYear(), Employee1.getDaysNonAvailable().getNonAvailability().getEndYear());
        assertEquals(Employee1Found.getDaysNonAvailable().getReason(), Employee1.getDaysNonAvailable().getReason());
        assertEquals(Employee1Found.getDaysNonAvailable().getDaysOfTheWeek(), Employee1.getDaysNonAvailable().getDaysOfTheWeek());
        assertEquals(Employee1Found.getSalary().getSalaryPrice().getValue(), Employee1.getSalary().getSalaryPrice().getValue());
        assertEquals(Employee1Found.getSalary().getSalaryPrice().getCurrency(), Employee1.getSalary().getSalaryPrice().getCurrency());
        assertEquals(Employee1Found.getSalary().getPaymentMethod(), Employee1.getSalary().getPaymentMethod());

    }
}
