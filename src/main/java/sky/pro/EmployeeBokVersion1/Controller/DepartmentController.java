package sky.pro.EmployeeBokVersion1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.EmployeeBokVersion1.Service.DepartmentService;
import sky.pro.EmployeeBokVersion1.dto.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findMaxSalary(@RequestParam int department) {
        return departmentService.findMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee findMinimumSalary(@RequestParam int department) {
        return departmentService.findMinimumSalary(department);
    }

   /* @GetMapping("/sum-salary")
    public Integer returnMaxSalary(@RequestParam int department) {
        return departmentService.returnSumSalaryInDepartment(department);
    }*/

    @GetMapping(value = "/all", params = "department")
    public List<Employee> returnAllEmployeesInDepartment(@RequestParam int department) {
        return departmentService.getAllEmployeesInDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
}
