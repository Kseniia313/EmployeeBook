package sky.pro.EmployeeBokVersion1.Service;

import org.springframework.stereotype.Service;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeAlreadyAddedException;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeNotFoundException;
import sky.pro.EmployeeBokVersion1.Exception.EmployeeStorageIsFullException;
import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.Collection;
import java.util.List;
@Service

public class EmployeeServiceImpl implements EmployeeService {
    private List <Employee> employees;
    private static final int EMPLOYEE_SIZE =5;

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }
    @Override

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size()==EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
      Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)){
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
      return employee;
    }
@Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.remove(employee)) {
           throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }
@Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
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
