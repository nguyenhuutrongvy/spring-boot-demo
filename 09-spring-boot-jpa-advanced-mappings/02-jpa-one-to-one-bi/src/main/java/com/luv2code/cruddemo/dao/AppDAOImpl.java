package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
