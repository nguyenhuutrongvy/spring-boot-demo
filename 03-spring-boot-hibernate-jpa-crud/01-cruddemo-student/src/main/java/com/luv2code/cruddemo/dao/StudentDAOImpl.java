package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
  // Define field for entity manager
  private EntityManager entityManager;

  // Inject entity manager using constructor injection
  @Autowired
  public StudentDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  // Implement save method
  @Override
  @Transactional
  public void save(Student theStudent) {
    entityManager.persist(theStudent);
  }

  @Override
  public Student findById(Integer id) {
    return entityManager.find(Student.class, id);
  }

  @Override
  public List<Student> findAll() {
    // Create query
    TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student ORDER BY lastName ASC", Student.class);

    // Return query results
    return theQuery.getResultList();
  }

  @Override
  public List<Student> findByLastName(String theLastName) {
    // Create query
    TypedQuery<Student> theQuery = entityManager.createQuery(
        "FROM Student WHERE lastName = :theData", Student.class);

    // Set query parameters
    theQuery.setParameter("theData", theLastName);

    // Return query results
    return theQuery.getResultList();
  }

  @Override
  @Transactional
  public void update(Student theStudent) {
    entityManager.merge(theStudent);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    // Retrieve the student
    Student theStudent = entityManager.find(Student.class, id);

    // Delete the student
    entityManager.remove(theStudent);
  }

  @Override
  @Transactional
  public int deleteAll() {
    int numRowsDeleted = entityManager
        .createQuery("DELETE FROM Student")
        .executeUpdate();

    return numRowsDeleted;
  }
}
