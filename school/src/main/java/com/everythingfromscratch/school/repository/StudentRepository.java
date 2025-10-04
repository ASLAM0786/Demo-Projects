package com.everythingfromscratch.school.repository;

import com.everythingfromscratch.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
