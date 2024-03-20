package code.with.vanilson.department;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_departments")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonPropertyOrder({"departmentId", "departmentName", "departmentDescription", "departmentCode"})
public class Department implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long departmentId;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

    public Department() {
    }

    public Department(long departmentId, String departmentName, String departmentDescription, String departmentCode) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.departmentCode = departmentCode;
    }
}
