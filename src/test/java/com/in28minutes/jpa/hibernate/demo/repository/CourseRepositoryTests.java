package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTests {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void findById_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());
    }

    @Test
    @Transactional
    public void findById_firstLevelCacheDemo() {
        Course course = repository.findById(10001L);
        logger.info("First Course Retrieve {}", course);
        Course course1 = repository.findById(10001L);
        logger.info("First Course Retrieve again {}", course1);
        assertEquals("JPA in 50 Steps", course.getName());
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        assertNotNull(repository.findById(10002L));
        repository.deleteById(10002L);
        assertNull(repository.findById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 steps", course.getName());

        course.setName("JPA in 50 steps - updated");

        repository.save(course);
        Course course1 = repository.findById(10001L);
        assertEquals("JPA in 50 steps - updated", course1.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager_basic() {
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = repository.findById(10001L);
        logger.info("course.getReviews() -> {}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview() {
        Review review = entityManager.find(Review.class, 50001L);
        logger.info("review.getCourse() -> {}", review.getCourse());
    }

}
