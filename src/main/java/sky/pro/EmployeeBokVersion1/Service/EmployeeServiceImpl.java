package sky.pro.EmployeeBokVersion1.Service;

import org.springframework.stereotype.Service;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeAlreadyAddedException;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeNotFoundException;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeStorageIsFullException;
import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;
    private static final int EMPLOYEE_SIZE = 5;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        String key = generateKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        String key = generateKey(firstName, lastName);
        Employee employee = employees.remove(key);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = firstName + lastName;
        Employee employee = employees.get(key);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }

    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }

    private String generateKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
