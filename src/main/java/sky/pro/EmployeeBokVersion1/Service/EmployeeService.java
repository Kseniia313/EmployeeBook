package sky.pro.EmployeeBokVersion1.Service;

import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> getAll();
}
