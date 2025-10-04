package com.everythingfromscratch.school.mapper;

import com.everythingfromscratch.school.dto.SimpleTeacherDto;
import com.everythingfromscratch.school.dto.StudentDto;
import com.everythingfromscratch.school.dto.TeacherDto;
import com.everythingfromscratch.school.model.Student;
import com.everythingfromscratch.school.model.Teacher;

import java.util.List;

public class SchoolMapper {
    private SchoolMapper(){
        throw new IllegalStateException("Mapper Utility class");
    }
    public static StudentDto toStudentDto(Student student) {
        if (student == null) return null;
        return new StudentDto(student.getId(), student.getName());
    }

    public static TeacherDto toTeacherDto(Teacher teacher) {
        if (teacher == null) return null;
        List<StudentDto> studentDtos = teacher.getStudents()
                .stream()
                .map(SchoolMapper::toStudentDto)
                .toList();
        return new TeacherDto(teacher.getId(), teacher.getName(), studentDtos);
    }

    public static SimpleTeacherDto toSimpleTeacherDto(Teacher teacher) {
        if (teacher == null) return null;
        return new SimpleTeacherDto(teacher.getId(), teacher.getName());
    }

    public static List<StudentDto> toStudentDtoList(List<Student> students) {
        return students.stream()
                .map(SchoolMapper::toStudentDto)
                .toList();
    }

    public static List<SimpleTeacherDto> toTeacherDtoList(List<Teacher> teachers) {
        return teachers.stream()
                .map(SchoolMapper::toSimpleTeacherDto)
                .toList();
    }
}
