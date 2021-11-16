package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.hibernate.Criteria;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
class CriteriaQueryTests {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void all_courses() {
        // "Select c from Course c"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query =
                em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info("TypedQuery -> {}", resultList);
    }

    @Test
    public void all_courses_having_100Steps() {
        // "Select c from Course c where name like '%100 Steps'"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");

        // 4. Add Predicates etc to the Criteria Query
        cq.where(like100Steps);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query =
                em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info("TypedQuery -> {}", resultList);
    }

    @Test
    public void all_courses_without_students() {
        // "Select c from Course c where c.students is empty"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

        // 4. Add Predicates etc to the Criteria Query
        cq.where(studentsIsEmpty);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query =
                em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info("TypedQuery -> {}", resultList);
    }
}
