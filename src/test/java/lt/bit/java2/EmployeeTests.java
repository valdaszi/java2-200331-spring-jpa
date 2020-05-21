package lt.bit.java2;

import lt.bit.java2.entities.Employee;
import lt.bit.java2.entities.Gender;
import lt.bit.java2.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class EmployeeTests {

	@Resource
	private EmployeeRepository employeeRepository;

	@Test
	void getEmployee() {
		Optional<Employee> employee = employeeRepository.findById(10001);
		Assertions.assertTrue(employee.isPresent());
		Assertions.assertEquals("Georgi", employee.get().getFirstName());
		Assertions.assertEquals(Gender.M, employee.get().getGender());
	}

	@Test
	void createEmployee() {
		Employee employee = new Employee();
		employee.setEmpNo(1);
		employee.setFirstName("A");
		employee.setLastName("B");
		employee.setGender(Gender.F);
		employee.setBirthDate(LocalDate.of(1990, 12, 31));
		employee.setHireDate(LocalDate.of(2020, 1, 15));
		Employee e2 = employeeRepository.save(employee);
		Assertions.assertNotNull(e2.getEmpNo());

		Optional<Employee> e = employeeRepository.findById(e2.getEmpNo());
		Assertions.assertEquals("A", e.get().getFirstName());
	}

	@Test
	void deleteEmployee() {
		employeeRepository.deleteById(10001);
		Optional<Employee> optionalEmployee = employeeRepository.findById(10001);
		Assertions.assertFalse(optionalEmployee.isPresent());
	}

}
