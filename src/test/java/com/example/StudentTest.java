package com.example;

import com.example.student.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    @Autowired
    StudentRepository repository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    EntityManager em;

//    @Test
//    @Transactional
//    public void shouldSaveStudentWithSubjects() {
//        //given
//        Student student = new Student();
//        PersonalData personalData = new PersonalData();
//        personalData.setName("Piotr");
//        personalData.setLastName("Kowalski");
//        personalData.setPesel("18568236382532");
//
//        Subject subject1 = new Subject();
//        subject1.setName("Mathematics");
//
//        Subject subject2 = new Subject();
//        subject2.setName("English");
//
//        Set<Subject> subjects = new HashSet<>();
//        subjects.add(subject1);
//        subjects.add(subject2);
//
//        student.setSubjects(subjects);
//
//        //when
//        repository.save(student);
//
//        //then
//        Assert.assertEquals(1, repository.findAll().size());
//    }

//    @Test
//    @Transactional
//    public void shouldFindAllStudentsForSubject() {
//        //given
//        Student student1 = new Student();
//        PersonalData personalData = new PersonalData();
//        personalData.setName("Piotr");
//        personalData.setLastName("Kowalski");
//        personalData.setPesel("91041583753");
//
//        Student student2 = new Student();
//        personalData.setName("Jan");
//        personalData.setLastName("Nowak");
//        personalData.setPesel("89101773682");
//
//        Subject subject1 = new Subject();
//        subject1.setName("Mathematics");
//
//        Subject subject2 = new Subject();
//        subject2.setName("English");
//
//        Set<Subject> subjects = new HashSet<>();
//        subjects.add(subject1);
//        subjects.add(subject2);
//
//        student1.setSubjects(subjects);
//        repository.save(student1);
//        em.flush();
//
//        student2.setSubjects(subjects);
//        repository.save(student2);
//        em.flush();
//
//        //when
//        List<Student> foundStudents = repository.findBySubjects_Name(subject1.getName());
//
//        //then
//        Assert.assertEquals(2, foundStudents.size());
//    }

//    @Test
//    @Transactional
//    public void shouldFindAllSubjectsForStudent() {
//        //given
//        Student student1 = new Student();
//        PersonalData personalData = new PersonalData();
//        personalData.setName("Piotr");
//        personalData.setLastName("Kowalski");
//        personalData.setPesel("91041583753");
//
//        Student student2 = new Student();
//        personalData.setName("Jan");
//        personalData.setLastName("Nowak");
//        personalData.setPesel("89101773682");
//
//        Subject subject1 = new Subject();
//        subject1.setName("Mathematics");
//
//        Subject subject2 = new Subject();
//        subject2.setName("English");
//
//        Set<Student> students = new HashSet<>();
//        students.add(student1);
//        students.add(student2);
//
//        subject1.setStudents(students);
//        subjectRepository.save(subject1);
//        em.flush();
//
//        subject2.setStudents(students);
//        subjectRepository.save(subject2);
//        em.flush();
//
//        //when
//        List<Subject> foundSubjects = subjectRepository.findByPersonalDataName(student1.getPersonalData().getName());
//
//        //then
//        Assert.assertEquals(2, foundSubjects.size());
//    }

//    @Test
//    @Transactional
//    public void shouldDeleteAllStudentsWithSubjects() {
//        //given
//        Student student1 = new Student();
//        PersonalData personalData = new PersonalData();
//        personalData.setName("Piotr");
//        personalData.setLastName("Kowalski");
//        personalData.setPesel("91041583753");
//
//        Student student2 = new Student();
//        personalData.setName("Jan");
//        personalData.setLastName("Nowak");
//        personalData.setPesel("89101773682");
//
//        Subject subject1 = new Subject();
//        subject1.setName("Mathematics");
//
//        Subject subject2 = new Subject();
//        subject2.setName("English");
//
//        Set<Subject> subjects = new HashSet<>();
//        subjects.add(subject1);
//        subjects.add(subject2);
//
//        student1.setSubjects(subjects);
//        repository.save(student1);
//        em.flush();
//
//        student2.setSubjects(subjects);
//        repository.save(student2);
//        em.flush();
//
//        //when
//        repository.deleteAll();
//
//        //given
//        Assert.assertTrue("empty",repository.findAll().isEmpty());
//    }

    @Test
    @Transactional
    public void shouldFindAllStudentsCreated_afterDate() {
        //given
        Student student1 = new Student();
        PersonalData personalData = new PersonalData();
        personalData.setName("Piotr");
        personalData.setLastName("Kowalski");
        personalData.setPesel("91041583753");
        AdditionalData additionalData = new AdditionalData();
        additionalData.setCreationDate(LocalDate.of(2017,04,07));

        Student student2 = new Student();
        personalData.setName("Jan");
        personalData.setLastName("Nowak");
        personalData.setPesel("89101773682");
        additionalData.setCreationDate(LocalDate.of(2016,01,12));

        Subject subject1 = new Subject();
        subject1.setName("Mathematics");
        additionalData.setCreationDate(LocalDate.of(2018,11,06));

        Subject subject2 = new Subject();
        subject2.setName("English");
        additionalData.setCreationDate(LocalDate.of(2014,05,01));

        Set<Subject> subjects = new HashSet<>();
        subjects.add(subject1);
        subjects.add(subject2);

        student1.setSubjects(subjects);
        repository.save(student1);
        em.flush();

        student2.setSubjects(subjects);
        repository.save(student2);
        em.flush();

        //when
        List<Student> foundAllStudentsCreatedAfterDate = repository.findByAdditionalDataCreationDateAfter(LocalDate.of(2016,1,1));

        //then
        Assert.assertEquals(2, foundAllStudentsCreatedAfterDate.size());
    }

}
