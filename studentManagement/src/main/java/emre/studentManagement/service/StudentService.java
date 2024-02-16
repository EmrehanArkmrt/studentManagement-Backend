package emre.studentManagement.service;

import emre.studentManagement.dto.StudentDTO;
import emre.studentManagement.service.SuperService;

import java.util.List;

public interface StudentService extends SuperService {

    void createNewStudent(StudentDTO studentDTO);

    StudentDTO getStudentInfo(String nic);

    void updateStudentDetails(StudentDTO studentDTO);

    void deleteStudent(String nic);

    List<StudentDTO> getAllStudents();
}
