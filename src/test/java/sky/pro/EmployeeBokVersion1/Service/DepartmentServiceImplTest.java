package sky.pro.EmployeeBokVersion1.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeNotFoundException;
import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;


    private List<Employee> employees = List.of(new Employee("Viktor", "Seleznev", 1, 50_000),
            new Employee("Ivan", "Ivanov", 1, 60_000),
            new Employee("Maksim", "Maksimov", 2, 100_000));


    @Test
    void findMaxSalary_shouldReturnEmployeeWithMaxSalaryWhenEmployeeInDepartment() {
        when(employeeService.getAll()).thenReturn(employees);

        Employee maxSalary = departmentService.findMaxSalary(employees.get(0).getDepartment());
        assertEquals(employees.get(1), maxSalary);

    }

    @Test
    void findMaxSalary_shouldThrowExceptionWhenEmployeeIsNotInDepartment() {
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMaxSalary(1));
    }

    @Test
    void findMinimumSalary_ShouldFindAndReturnEmployeeWithMinSalary() {
        when(employeeService.getAll()).thenReturn(employees);

        Employee minSalary = departmentService.findMinimumSalary(employees.get(1).getDepartment());

        assertEquals(employees.get(0), minSalary);

    }
    @Test
    void findMinSalary_shouldThrowExceptionWhenEmployeeIsNotInDepartment() {
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMinimumSalary(1));
    }

    @Test
    void returnAllEmployeesInDepartment_shouldReturnEmployeesInDepartment() {
        when(employeeService.getAll()).thenReturn(employees);

        List<Employee> result = departmentService.getAllEmployeesInDepartment(
                employees.get(0).getDepartment());

        assertEquals(List.of(employees.get(0), employees.get(1)), result);

    }

    @BeforeTestMethod
    void beforeTest() {
        employeeService = new EmployeeServiceImpl();
    }
    @Test
    void getAllEmployeesInDepartment_shouldReturnEmptyListWhenEmployeesAreNotInDepartment() {
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        List<Employee> employeeInDepartment = List.of();

        List<Employee> result = departmentService.getAllEmployeesInDepartment(1);

        assertEquals(employeeInDepartment, result);
    }

    @Test
    void getAll_ShouldReturnMapWithEmployeesWhenEmployeeInDepartments() {
        when(employeeService.getAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(
                1, List.of(employees.get(0), employees.get(1)),
                2, List.of(employees.get(2)));

        Map<Integer, List<Employee>> result = departmentService.getAll() ;
assertEquals(expectedMap, result);
    }

    @Test
    void getAll_ShouldReturnEmptyListWhenEmployeeIsNotFoundInMap() {
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        Map<Integer, List<Employee>> aLLEmployees = Map.of();

        Map<Integer, List<Employee>> result = departmentService.getAll();

        assertEquals(aLLEmployees, result);

    }
}