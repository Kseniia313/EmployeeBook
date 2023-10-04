package sky.pro.EmployeeBokVersion1.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeAlreadyAddedException;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeNotFoundException;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeStorageIsFullException;
import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new EmployeeServiceImpl();
    }

    private Employee expectedEmployee = new Employee("Nika", "Ivanova", 1, 50_000);
    @Test
    void addEmployee_shouldAddEmployeeToListAndReturnEmployee() {

        Employee result = underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        assertTrue(underTest.getAll().contains(expectedEmployee));
        assertEquals(expectedEmployee,result);
    }

    @Test
    void addEmployee_shouldThrowExceptionWhenNotEnoughSize() {
        for (int i = 0; i < 5; i++) {
            underTest.addEmployee((expectedEmployee.getFirstName() + i),
                    (expectedEmployee.getLastName() + i),
                    expectedEmployee.getDepartment(),
                    expectedEmployee.getSalary());
        }
        EmployeeStorageIsFullException employeeStorageIsFullException =
                assertThrows(EmployeeStorageIsFullException.class, () -> underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary()));

    }

    @Test
    void addEmployee_shouldThrowExceptionWhenEqualEmployeeInList() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        EmployeeAlreadyAddedException employeeAlreadyAddedException =
                assertThrows(EmployeeAlreadyAddedException.class, () -> underTest.addEmployee(
                expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary()));

    }

    @Test
    void removeEmployee_ShouldRemoveEmployeeAndReturn() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        Employee resultWithoutEmployee = underTest.removeEmployee(
                expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        assertFalse(underTest.getAll().remove(expectedEmployee));
        assertEquals(expectedEmployee, resultWithoutEmployee);


    }

    @Test
    void removeEmployee_shouldThrowExceptionWhenEmployeeNotFound() {
        Employee employee = new Employee("Semen", "Semenov", 1, 40_000);
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        EmployeeNotFoundException employeeNotFoundException = assertThrows(EmployeeNotFoundException.class,
                () -> underTest.removeEmployee(employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartment(),
                        employee.getSalary()));
    }

    @Test
    void findEmployee_ShouldFindEmployeeAndReturnEmployee() {
      underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        Employee employee = underTest.findEmployee(
                expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        assertTrue(underTest.getAll().contains(employee));
        assertEquals(expectedEmployee,employee);
    }

    @Test
    void findEmployee_ShouldThrowEmployeeNotFoundException() {
        Employee employee = new Employee("Rita", "Morozova", 1, 65_000);
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());
        assertThrows(EmployeeNotFoundException.class,()
                -> underTest.findEmployee(employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartment(),
                employee.getSalary()));
    }

    @Test
    void getAll_ShouldReturnAllEmployee() {
        Employee employee = new Employee("Vitalii", "Beregnoi", 1, 100_000);
        underTest.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        underTest.addEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getDepartment(), employee.getSalary());

        Collection<Employee> result = underTest.getAll();

        assertTrue(result.containsAll(List.of(expectedEmployee,employee)));
    }

    @Test
    void getAll_ShouldReturnEmptyListWhenEmployeeNotInList() {
        Collection<Employee> all = underTest.getAll();
        assertTrue(all.isEmpty());

    }
}