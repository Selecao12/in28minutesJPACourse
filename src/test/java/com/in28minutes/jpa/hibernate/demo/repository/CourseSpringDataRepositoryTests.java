package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoApplication.class)
class CourseSpringDataRepositoryTests {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void findById_coursePresent() {
        Optional<Course> courseOptional = repository.findById(10001L);

        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_courseNotPresent() {
        Optional<Course> courseOptional = repository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playAroundWithSpringDataRepository() {
//        Course course = repository.save(new Course("Microservices in 100 Steps"));
//        repository.save(course);

//        course.setName("Microservices in 100 Steps - Updated");
//        repository.save(course);

        logger.info("Courses -> {}", repository.findAll());
        logger.info("Count -> {}", repository.count());
    }

    @Test
    public void sort() {

        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        logger.info("Sorted Courses -> {}", repository.findAll(sort));
//        [Course[JPA in 50 Steps], Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps]]
//        [Course[Spring in 50 Steps], Course[Spring Boot in 100 Steps], Course[JPA in 50 Steps]]
        logger.info("Count -> {}", repository.count());
    }

    @Test
    public void pagination() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First Page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second Page -> {}", secondPage.getContent());
    }

}
