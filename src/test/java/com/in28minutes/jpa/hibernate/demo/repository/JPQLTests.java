package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
class JPQLTests {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void jpql_basic() {
        Query query = em.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("select c from course c -> {}", resultList);
    }

    @Test
    public void jpql_typed() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("select c from course c -> {}", resultList);
    }

    @Test
    public void jpql_where() {
        TypedQuery<Course> query = em.createNamedQuery("query_get_100_step_course", Course.class);
        List<Course> resultList = query.getResultList();

        logger.info("select c from Course c where name like '%100 steps' -> {}", resultList);
    }

    @Test
    public void jpql_courses_without_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);

        List<Course> resultList = query.getResultList();

        logger.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_courses_with_at_least_2_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);

        List<Course> resultList = query.getResultList();

        logger.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_courses_ordered_by_students() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc", Course.class);

        List<Course> resultList = query.getResultList();

        logger.info("Results -> {}", resultList);
    }

    @Test
    public void jpql_students_with_passports_in_a_certain_pattern() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("Results -> {}", resultList);
    }

    //    JOIN => Select c, s from Course c JOIN c.students s
//    LEFT JOIN => Select c, s from Course c LEFT JOIN c.students s
//    CROSS JOIN => Select c, s from Course c, Student s
    @Test
    public void join() {
        Query query = em.createQuery("select c, s from Course c join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());

        for (Object[] result : resultList) {
            logger.info("Course {}, Student {}", result[0], result[1]);
        }
    }

    @Test
    public void left_join() {
        Query query = em.createQuery("select c, s from Course c left join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());

        for (Object[] result : resultList) {
            logger.info("Course {}, Student {}", result[0], result[1]);
        }
    }

    @Test
    public void join_where() {
        Query query = em.createQuery("select c, s from Course c left join c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());

        for (Object[] result : resultList) {
            logger.info("Course {}, Student {}", result[0], result[1]);
        }
    }

    @Test
    public void cross_join() {
        Query query = em.createQuery("select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());

        for (Object[] result : resultList) {
            logger.info("Course {}, Student {}", result[0], result[1]);
        }
    }
}
