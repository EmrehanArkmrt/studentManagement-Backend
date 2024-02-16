package emre.studentManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student implements SuperEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(10)")
    private String nic;
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;
    @Column(nullable = false)
    private Integer grade;
    @Column(nullable = false, columnDefinition = "VARCHAR(11)")
    private String contact;
}
