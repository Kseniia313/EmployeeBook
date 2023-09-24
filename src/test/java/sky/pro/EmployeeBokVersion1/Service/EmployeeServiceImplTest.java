package sky.pro.EmployeeBokVersion1.Service;

import org.junit.jupiter.api.Test;
import sky.pro.EmployeeBokVersion1.dto.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl underTest = new EmployeeServiceImpl();
    @Test
    void addEmployee_shouldAddEmployeeToListAndReturnEmployee() {
        String firstName = "Nika";
        String lastName = "Ivanova";
        int department = 1;
        int salary = 50_000;
        Employee expectedEmployee = new Employee(firstName, lastName, department, salary);

        Employee result = underTest.addEmployee(firstName, lastName, department, salary);

        assertTrue(underTest.getAll().contains(expectedEmployee));
        assertEquals(expectedEmployee,result);
    }

    @Test
    void removeEmployee() {
    }

    @Test
    void findEmployee() {
    }

    @Test
    void getAll() {
    }
}