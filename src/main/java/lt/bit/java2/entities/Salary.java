package lt.bit.java2.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "salaries")
public class Salary {

    // PK: empNo + fromDate
    @EmbeddedId
    private SalaryPK id;

    private LocalDate toDate;
    private int salary;

    public SalaryPK getId() {
        return id;
    }

    public void setId(SalaryPK id) {
        this.id = id;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
