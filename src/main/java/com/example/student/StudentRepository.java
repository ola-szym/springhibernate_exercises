package com.example.student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long> {
//    List<Student> findBySubjects_Name(String name);
    List<Student> findByAdditionalDataCreationDateAfter(LocalDate date);
}
