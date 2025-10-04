package com.everythingfromscratch.school.repository;

import com.everythingfromscratch.school.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
