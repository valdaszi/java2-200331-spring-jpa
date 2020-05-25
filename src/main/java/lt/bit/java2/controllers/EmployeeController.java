package lt.bit.java2.controllers;

import lt.bit.java2.entities.Employee;
import lt.bit.java2.repositories.EmployeeRepository;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    EmployeeRepository employeeRepository;

    // /employee?pageSize={s}&pageNumber={n}
    @GetMapping
    String index(Model model,
                 @RequestParam(defaultValue = "10", required = false) int pageSize,
                 @RequestParam(defaultValue = "0", required = false) int pageNumber) {

        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(pageNumber, pageSize));
        model.addAttribute("page", employees);
        return "employee/index";
    }

    // /employee/{empNo}
    @GetMapping(path = "{empNo}")
    String employee(@PathVariable int empNo, Model model) {
        Employee employee = employeeRepository.findEmployeeByEmpNo(empNo);

        model.addAttribute("empNo", empNo);
        if (employee == null) return "employee/notFound";

        model.addAttribute("emp", employee);
        return "employee/get";
    }

}
