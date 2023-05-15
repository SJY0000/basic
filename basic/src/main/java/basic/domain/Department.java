package basic.domain;

import basic.BasicApplication;
import basic.domain.DepartmentAdded;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Department_table")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @PostPersist
    public void onPostPersist() {
        DepartmentAdded departmentAdded = new DepartmentAdded(this);
        departmentAdded.publishAfterCommit();
    }

    public static DepartmentRepository repository() {
        DepartmentRepository departmentRepository = BasicApplication.applicationContext.getBean(
            DepartmentRepository.class
        );
        return departmentRepository;
    }
}
