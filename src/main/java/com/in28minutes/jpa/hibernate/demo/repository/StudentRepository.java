package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }

        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z312321");
        em.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }

    public void someOpertaionToUnderstandPersistenceContext() {
        // DB Op1 - Retrieve student
        Student student = em.find(Student.class, 20001L);
        // Persistence Context(student)

        // DB Op1 - Retrieve passport
        Passport passport = student.getPassport();
        // Persistence Context(student, passport)

        // DB Op1 - Retrieve student
        passport.setNumber("E123457");
        // Persistence Context(student, passport++)

        // DB Op1 - Retrieve student
        student.setName("Range - updated");
        // Persistence Context(student++, passport)
    }

}
