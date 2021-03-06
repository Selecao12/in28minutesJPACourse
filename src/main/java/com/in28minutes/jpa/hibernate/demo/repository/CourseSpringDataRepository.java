package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByNameAndId(String name, Long id);

    List<Course> findByName(String name);

    List<Course> countByName(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    List<Course> deleteByName(String name);

    @Query("select c from Course c where name like '%100 steps'")
    List<Course> coursesWith100StepsInName();

    @Query(value = "select * from Course c where name like '%100 steps'", nativeQuery = true)
    List<Course> coursesWith100StepsInNameUsingNativeQuery();

    @Query(name = "query_get_all_courses")
    List<Course> coursesWith100StepsInNameUsingNamedQuery();
}
