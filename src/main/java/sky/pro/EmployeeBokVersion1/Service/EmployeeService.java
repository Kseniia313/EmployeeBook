package sky.pro.EmployeeBokVersion1.Service;

import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int department, int salary);

    Employee removeEmployee(String firstName, String lastName, int department, int salary);

    Employee findEmployee(String firstName, String lastName,int department, int salary);

    Collection<Employee> getAll();
}
