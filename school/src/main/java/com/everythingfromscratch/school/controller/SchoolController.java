package com.everythingfromscratch.school.controller;

import com.everythingfromscratch.school.dto.SimpleTeacherDto;
import com.everythingfromscratch.school.dto.StudentDto;
import com.everythingfromscratch.school.dto.TeacherDto;
import com.everythingfromscratch.school.mapper.SchoolMapper;
import com.everythingfromscratch.school.model.Student;
import com.everythingfromscratch.school.model.Teacher;
import com.everythingfromscratch.school.repository.StudentRepository;
import com.everythingfromscratch.school.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class SchoolController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    //create teacher
    @PostMapping("/teacher")
    public ResponseEntity<SimpleTeacherDto> createTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(SchoolMapper.toSimpleTeacherDto(newTeacher), HttpStatus.CREATED);
    }

    //create student
    @PostMapping("/student")
    public ResponseEntity<StudentDto> createStudent(@RequestBody Student student) {
        Student newStudent = studentRepository.save(student);
        return new ResponseEntity<>(SchoolMapper.toStudentDto(newStudent), HttpStatus.CREATED);
    }

    @PostMapping(value = "/school/teacher/{teacherId}/addStudent",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDto> addStudentToTeacher(@PathVariable Long teacherId,
                                                          @RequestBody Student student
    ) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);
        if (teacherOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Teacher teacher = teacherOpt.get();
        Student savedStudent = (student.getId() == null)
                ? studentRepository.save(student)
                : studentRepository.findById(student.getId()).orElseGet(() -> studentRepository.save(student));

        boolean alreadyMapped = teacher.getStudents()
                .stream()
                .anyMatch(s -> s.getId().equals(savedStudent.getId()));

        if (alreadyMapped) {
            return new ResponseEntity<>(SchoolMapper.toTeacherDto(teacher), HttpStatus.OK);
        }

        teacher.getStudents().add(savedStudent);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(SchoolMapper.toTeacherDto(updatedTeacher), HttpStatus.CREATED);
    }

    @GetMapping("/school/teacher/{teacherId}/students")
    public ResponseEntity<List<StudentDto>> getStudentsByTeacher(@PathVariable Long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if(teacher.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Student> students = List.copyOf(teacher.get().getStudents());
        return new ResponseEntity<>(SchoolMapper.toStudentDtoList(students), HttpStatus.OK);
    }

    @GetMapping("/school/student/{studentId}/teachers")
    public ResponseEntity<List<SimpleTeacherDto>> getTeachersByStudent(@PathVariable Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Teacher> teachers = List.copyOf(student.get().getTeachers());
        return new ResponseEntity<>(SchoolMapper.toTeacherDtoList(teachers), HttpStatus.OK);
    }
}
