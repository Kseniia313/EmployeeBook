package sky.pro.EmployeeBokVersion1.Service;

import org.springframework.stereotype.Service;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeNotFoundException;
import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private EmployeeService employeeService;

    public DepartmentServiceImpl() {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findMaxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(empl -> empl.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public Employee findMinimumSalary(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(empl -> empl.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    /*@Override
    public Integer returnSumSalaryInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.summingInt(empl -> empl.getSalary()));
    }*/

    @Override
    public List<Employee> getAllEmployeesInDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment()))
                ;
    }
}
