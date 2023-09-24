package sky.pro.EmployeeBokVersion1.Service;

import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findMaxSalary(int department);

    Employee findMinimumSalary(int department);

    Integer returnSumSalaryInDepartment(int department);

    List<Employee> returnAllEmployeesInDepartment(int department);

    Map<Integer, List<Employee>> getAll();



}
