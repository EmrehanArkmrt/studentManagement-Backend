package emre.studentManagement.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements Serializable {
    @NotBlank(message = "Student NIC number cannot be empty or null")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid student nic number")
    private String nic;
    @NotBlank(message = "Student name cannot be empty or null")
    @Pattern(regexp = "^[A-Za-z][A-Za-z ]+$", message = "Invalid student name")
    private String name;
    @Min(value = 0, message = "Student grade must be at least 0")
    @Max(value = 100, message = "Student grade must be at most 100")
    private int grade;
    @NotBlank(message = "Student contact number cannot be empty or null")
    @Pattern(regexp = "^0\\d{10}$", message = "Invalid student contact number")
    private String contact;

}
