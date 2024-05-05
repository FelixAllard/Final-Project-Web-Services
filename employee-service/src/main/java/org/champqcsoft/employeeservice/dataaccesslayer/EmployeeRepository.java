package org.champqcsoft.employeeservice.dataaccesslayer;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByEmployeeIdentifier_EmployeeId(String employeeId);

    void deleteEmployeeByEmployeeIdentifier_EmployeeId(String employeeId);

    boolean existsEmployeeByEmployeeIdentifier_EmployeeId(String employeeId);
}
