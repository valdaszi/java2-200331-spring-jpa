package lt.bit.java2.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@NamedEntityGraph(name = "Employee.salaries", attributeNodes = @NamedAttributeNode("salaries"))
public class Employee {

    @Id
    private Integer empNo;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private Gender gender;

    private LocalDate birthDate;

    private LocalDate hireDate;

    @OneToMany(mappedBy = "id.employee", fetch = FetchType.LAZY)
    private List<Salary> salaries;

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
}
