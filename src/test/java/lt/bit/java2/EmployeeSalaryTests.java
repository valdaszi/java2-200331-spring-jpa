package lt.bit.java2;

import lt.bit.java2.entities.Employee;
import lt.bit.java2.repositories.EmployeeRepository;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class EmployeeSalaryTests {

    @Resource
    private EmployeeRepository employeeRepository;

    @Test
    void getEmployeeWithSalaryError() {
        Optional<Employee> employee = employeeRepository.findById(10002);
        Assertions.assertEquals("Bezalel", employee.get().getFirstName());
        Assertions.assertEquals(LocalDate.of(1985, 11, 21), employee.get().getHireDate());
        Assertions.assertThrows(LazyInitializationException.class, () -> employee.get().getSalaries().size());
    }

    @Test
    void getEmployeeWithSalaryEntityGraph() {
        Employee employee = employeeRepository.findEmployeeByEmpNo(10002);
        Assertions.assertEquals("Bezalel", employee.getFirstName());
        Assertions.assertEquals(LocalDate.of(1985, 11, 21), employee.getHireDate());
        Assertions.assertEquals(6, employee.getSalaries().size());
    }

    @Transactional
    @Test
    void getEmployeeWithSalaryTransactional() {
        Optional<Employee> employee = employeeRepository.findById(10002);
        Assertions.assertEquals("Bezalel", employee.get().getFirstName());
        Assertions.assertEquals(LocalDate.of(1985, 11, 21), employee.get().getHireDate());
        Assertions.assertEquals(6, employee.get().getSalaries().size());
    }
}
