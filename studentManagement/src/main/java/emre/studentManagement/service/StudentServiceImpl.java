package emre.studentManagement.service;

import emre.studentManagement.dto.StudentDTO;
import emre.studentManagement.entity.Student;
import emre.studentManagement.repository.StudentRepository;
import emre.studentManagement.service.StudentService;
import emre.studentManagement.exception.NotFoundException;
import emre.studentManagement.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final Transformer transformer;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, Transformer transformer) {
        this.studentRepository = studentRepository;
        this.transformer = transformer;
    }

    @Override
    @Transactional
    public void createNewStudent(StudentDTO studentDTO) {
        Optional<Student> student = studentRepository.findById(studentDTO.getNic());
        if (student.isEmpty()) studentRepository.save(transformer.toStudentEntity(studentDTO));
        else throw new DuplicateKeyException("A student is already exists with this nic number");
    }

    @Override
    @Transactional
    public StudentDTO getStudentInfo(String nic) {
        if (!studentRepository.existsById(nic)) throw new NotFoundException("Student doesn't exist");
        Optional<Student> student = studentRepository.findById(nic);
        return transformer.toStudentDTO(student.get());
    }

    @Override
    @Transactional
    public void updateStudentDetails(StudentDTO studentDTO) {
        Optional<Student> student = studentRepository.findById(studentDTO.getNic());
        if (student.isPresent()) studentRepository.save(transformer.toStudentEntity(studentDTO));
        else throw new NotFoundException("Student doesn't exist");
    }

    @Override
    @Transactional
    public void deleteStudent(String nic) {
        if (!studentRepository.existsById(nic)) throw new NotFoundException("Student doesn't exist");
        studentRepository.deleteById(nic);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        return students.stream().map(transformer::toStudentDTO).collect(Collectors.toList());
    }
}
