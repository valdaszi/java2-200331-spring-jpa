package lt.bit.java2.api;

import lt.bit.java2.entities.Employee;
import lt.bit.java2.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

// @Controller
@RestController
@RequestMapping("/api/employee")
public class EmployeeApiController {

    @Resource
    EmployeeRepository employeeRepository;

    // CRUD

    @GetMapping("/{empNo}")
    // @ResponseBody
    ResponseEntity<Employee> read(@PathVariable int empNo) {
        if (empNo <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Employee employee = employeeRepository.findEmployeeByEmpNo(empNo);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    ResponseEntity<Employee> create(@RequestBody Employee employee) {
        if (employee == null || employee.getEmpNo() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Employee emp = employeeRepository.save(employee);
        return ResponseEntity.ok(emp);
    }

    @PutMapping("/{empNo}")
    ResponseEntity<Employee> update(@PathVariable int empNo, @RequestBody Employee employee) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @DeleteMapping("/{empNo}")
    ResponseEntity<?> delete(@PathVariable int empNo) {
        //TODO
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }


}
