package sky.pro.EmployeeBokVersion1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.EmployeeBokVersion1.Service.EmployeeService;

import sky.pro.EmployeeBokVersion1.Util.EmployeeNameValidator;
import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.Collection;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int department,
                                @RequestParam int salary) {
        EmployeeNameValidator.employeeNameIsAlpha(firstName, lastName);
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployy(@RequestParam String firstName,
                                  @RequestParam String lastName,
                                  @RequestParam int department,
                                  @RequestParam int salary
    ) {
        EmployeeNameValidator.employeeNameIsAlpha(firstName, lastName);
        return employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam int department,
                                 @RequestParam int salary) {
        EmployeeNameValidator.employeeNameIsAlpha(firstName, lastName);
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping
    public Collection<Employee> getAll() {
        return employeeService.getAll();
    }
}
