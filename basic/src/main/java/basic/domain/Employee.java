package basic.domain;

import basic.BasicApplication;
import basic.domain.EmployeeAdded;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Employee_table")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Boolean isManager;

    @Embedded
    @AttributeOverride(
        name = "id",
        column = @Column(name = "departmentIdId", nullable = true)
    )
    private DepartmentId departmentId;

    @Embedded
    @AttributeOverride(
        name = "id",
        column = @Column(name = "rankIdId", nullable = true)
    )
    private RankId rankId;

    @ElementCollection
    private List<String> employeeUser;

    @PostPersist
    public void onPostPersist() {
        EmployeeAdded employeeAdded = new EmployeeAdded(this);
        employeeAdded.publishAfterCommit();
    }

    public static EmployeeRepository repository() {
        EmployeeRepository employeeRepository = BasicApplication.applicationContext.getBean(
            EmployeeRepository.class
        );
        return employeeRepository;
    }
}
