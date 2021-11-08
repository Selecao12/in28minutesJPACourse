package com.in28minutes.jpa.hibernate.demo;

import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        studentRepository.saveStudentWithPassport();
//        repository.playWithEntityManager();
//        courseRepository.addHardcodedReviewsForCourse();

//        List<Review> reviews = new ArrayList<>();
//        reviews.add(new Review("5", "Great Hands-on Stuff"));
//        reviews.add(new Review("5", "Hatsoff."));

//        courseRepository.addReviewsForCourse(10003L, reviews);

//        studentRepository.insertHardcodedStudentAndCourse();

//        studentRepository.insertStudentAndCourse(new Student("Jack"),
//                new Course("Microservices in 100 Steps"));

//        employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
//        employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
//
//        logger.info("Employees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
//        logger.info("Employees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
    }
}
