package lt.bit.java2.repositories;

import lt.bit.java2.entities.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     *  Spring JPA pagal metodo varda automatiškai sugeneruoja reikalingą SQL Query
     *  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
     */
    List<Employee> findByBirthDate(LocalDate birthDate);


    /**
     *  Jei automatiškai generuojami Query mums netinka, tai galime patys parašyti reikalingą arba JPQL
     *  arba standartine SQL kalba
     *  p.s. jei rašome standartine SQL, tada reikia nurodyti: nativeQuery = true
     */
    @Query(value = "SELECT a FROM Employee a WHERE a.birthDate BETWEEN :d1 AND :d2 ORDER BY a.firstName DESC" )
    List<Employee> findJPQL(LocalDate d1, LocalDate d2);

    @Query(value = "SELECT * FROM employees WHERE birth_date BETWEEN :d1 AND :d2 ORDER BY first_name", nativeQuery = true)
    List<Employee> findNative(@Param("d1") LocalDate date1, @Param("d2") LocalDate date2);

    /**
     * Gauname employee pagal jo empNo su salaries arba naudojant aprašytą NamedEntityGraph arba automatiškai sugeneruotą EntityGraph
     */
//    @EntityGraph(value = "Employee.salaries") // aprašytas NamedEntityGraph
    @EntityGraph(attributePaths = {"salaries"}) // automatiškai sugeneruotas EntityGraph
    Employee findEmployeeByEmpNo(int empNo);

};
