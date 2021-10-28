package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }

        return course;
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web Services in 100 steps");
        em.persist(course1);

        Course course2 = findById(10001L);
        course2.setName("JPA in 50 steps - Updated");

        em.flush();
    }

    public void addReviewsForCourse() {
        // get the course 10003
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        // add 2 reviews
        Review review1 = new Review("5", "Great Hands-on Stuff");
        Review review2 = new Review("5", "Hatsoff.");

        // setting the relationship
        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);



        // save to db
        em.persist(review1);
        em.persist(review2);
    }
}
