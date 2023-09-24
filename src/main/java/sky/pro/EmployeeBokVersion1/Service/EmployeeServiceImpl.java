package sky.pro.EmployeeBokVersion1.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeAlreadyAddedException;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeNotFoundException;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeStorageIsFullException;
import sky.pro.EmployeeBokVersion1.dto.Employee;


import java.util.Collection;

import java.util.List;


@Service

public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employees;
    private static final int EMPLOYEE_SIZE = 5;



    @Override

    public Employee addEmployee(String firstName, String lastName, int department, int salary) {

        if (employees.size() == EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        Employee employee = new Employee(
        StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                department,
                salary);

        if (employees.contains(employee)) {

            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }

    @Override

    public Employee removeEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    @Override

    public Employee findEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.contains(employee);
        return employee;
    }

    @Override
    public Collection<Employee> getAll() {
        return employees;
    }

}
