package emre.studentManagement.controller;

import emre.studentManagement.exception.PathMismatchException;
import emre.studentManagement.dto.StudentDTO;
import emre.studentManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.createNewStudent(studentDTO);
        return studentDTO;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{nic}", produces = "application/json")
    public StudentDTO getStudentDetails(@PathVariable String nic) {
        return studentService.getStudentInfo(nic);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(value = "/{nic}", consumes = "application/json", produces = "application/json")
    public StudentDTO updateStudent(@PathVariable String nic, @Valid @RequestBody StudentDTO studentDTO) {
        if (!nic.equals(studentDTO.getNic())) throw new PathMismatchException("JSON object student nic number is not match with the url pattern student nic number");
        studentService.updateStudentDetails(studentDTO);
        return studentDTO;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{nic}")
    public void deleteStudent(@PathVariable String nic) {
        studentService.deleteStudent(nic);
    }
}
