package lt.bit.java2;

import lt.bit.java2.entities.Employee;
import lt.bit.java2.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class EmployeeQueryTest {

    @Resource
    private EmployeeRepository employeeRepository;


    @Test
    void getListByBirthDate() {
        List<Employee> employees = employeeRepository.findByBirthDate(LocalDate.of(1959, 12, 3));
        Assertions.assertEquals(1, employees.size());
        Assertions.assertEquals("Parto", employees.get(0).getFirstName());
    }

    @Test
    void getEmployeesWithNativeQuery() {
        List<Employee> employees = employeeRepository.findNative(
                LocalDate.of(1954, 1, 1),
                LocalDate.of(1959, 12, 31));
        Assertions.assertEquals(2, employees.size());
        Assertions.assertEquals("Chirstian", employees.get(0).getFirstName());
        Assertions.assertEquals("Parto", employees.get(1).getFirstName());
    }

    @Test
    void getEmployeesWithJPQLQuery() {
        List<Employee> employees = employeeRepository.findJPQL(
                LocalDate.of(1954, 1, 1),
                LocalDate.of(1959, 12, 31));
        Assertions.assertEquals(2, employees.size());
        Assertions.assertEquals("Parto", employees.get(0).getFirstName());
        Assertions.assertEquals("Chirstian", employees.get(1).getFirstName());
    }
}
