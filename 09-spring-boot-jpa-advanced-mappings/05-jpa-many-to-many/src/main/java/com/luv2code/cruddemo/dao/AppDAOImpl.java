package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
  // Define field for entity manager
  private EntityManager entityManager;

  // Inject entity manager using constructor injection
  public AppDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void save(Instructor theInstructor) {
    entityManager.persist(theInstructor);
  }

  @Override
  public Instructor findInstructorById(int theId) {
    return entityManager.find(Instructor.class, theId);
  }

  @Override
  @Transactional
  public void deleteInstructorById(int theId) {
    // Retrieve the instructor
    Instructor tempInstructor = entityManager.find(Instructor.class, theId);

    // Get the courses
    List<Course> courses = tempInstructor.getCourses();

    // Break association of all courses for the instructor
    for (Course tempCourse : courses) {
      tempCourse.setInstructor(null);
    }

    // Delete the instructor
    entityManager.remove(tempInstructor);
  }

  @Override
  public InstructorDetail findInstructorDetailById(int theId) {
    return entityManager.find(InstructorDetail.class, theId);
  }

  @Override
  @Transactional
  public void deleteInstructorDetailById(int theId) {
    // Retrieve instructor detail
    InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

    // Remove the associated object reference
    // Break bidirectional link
    tempInstructorDetail.getInstructor().setInstructorDetail(null);

    // Delete the instructor detail
    entityManager.remove(tempInstructorDetail);
  }

  @Override
  public List<Course> findCoursesByInstructorId(int theId) {
    // Create query
    TypedQuery<Course> query = entityManager.createQuery(
        "from Course where instructor.id = :data",
        Course.class
    );
    query.setParameter("data", theId);

    // Execute query
    List<Course> courses = query.getResultList();

    return courses;
  }

  @Override
  public Instructor findInstructorByIdJoinFetch(int theId) {
    // Create query
    TypedQuery<Instructor> query = entityManager.createQuery(
        "select i from Instructor i " +
            "JOIN FETCH i.courses " +
            "JOIN FETCH i.instructorDetail " +
            "where i.id = :data",
        Instructor.class
    );

    query.setParameter("data", theId);

    // Execute query
    Instructor instructor = query.getSingleResult();

    return instructor;
  }

  @Override
  @Transactional
  public void update(Instructor tempInstructor) {
    entityManager.merge(tempInstructor);
  }

  @Override
  @Transactional
  public void update(Course tempCourse) {
    entityManager.merge(tempCourse);
  }

  @Override
  public Course findCourseById(int theId) {
    return entityManager.find(Course.class, theId);
  }

  @Override
  @Transactional
  public void deleteCourseById(int theId) {
    // Retrieve the course
    Course tempCourse = entityManager.find(Course.class, theId);

    // Delete the course
    entityManager.remove(tempCourse);
  }

  @Override
  @Transactional
  public void save(Course theCourse) {
    entityManager.persist(theCourse);
  }

  @Override
  public Course findCourseAndReviewsByCourseId(int theId) {
    // Create query
    TypedQuery<Course> query = entityManager.createQuery(
        "SELECT c FROM Course c " +
            "JOIN FETCH c.reviews " +
            "WHERE c.id = :data",
        Course.class
    );
    query.setParameter("data", theId);

    // Execute query
    Course course = query.getSingleResult();

    return course;
  }

  @Override
  public Course findCourseAndStudentByCourseId(int theId) {
    // Create query
    TypedQuery<Course> query = entityManager.createQuery(
        "SELECT c FROM Course c " +
            "JOIN FETCH c.students " +
            "WHERE c.id = :data",
        Course.class
    );
    query.setParameter("data", theId);

    // Execute query
    Course course = query.getSingleResult();

    return course;
  }

  @Override
  public Student findStudentAndCoursesByStudentId(int theId) {
    // Create query
    TypedQuery<Student> query = entityManager.createQuery(
        "SELECT s FROM Student s " +
            "JOIN FETCH s.courses " +
            "WHERE s.id = :data",
        Student.class
    );
    query.setParameter("data", theId);

    // Execute query
    Student student = query.getSingleResult();

    return student;
  }

  @Override
  @Transactional
  public void update(Student tempStudent) {
    entityManager.merge(tempStudent);
  }

  @Override
  @Transactional
  public void deleteStudentById(int theId) {
    // Retrieve the student
    Student tempStudent = entityManager.find(Student.class, theId);

    if (tempStudent != null) {
      // Get the courses
      List<Course> courses = tempStudent.getCourses();

      // Break association of all courses for the student
      for (Course tempCourse : courses) {
        tempCourse.getStudents().remove(tempStudent);
      }

      // Now delete the student
      entityManager.remove(tempStudent);
    }
  }
}
