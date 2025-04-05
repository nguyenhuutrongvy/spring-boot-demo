package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(CruddemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(AppDAO appDAO) {
    return runner -> {
//      createCourseAndStudent(appDAO);
//      findCourseAndStudents(appDAO);
//      findStudentAndCourses(appDAO);
//      addMoreCoursesForStudent(appDAO);
//      deleteCourse(appDAO);
      deleteStudent(appDAO);
    };
  }

  private void deleteStudent(AppDAO appDAO) {
    int theId = 1;
    System.out.println("Deleting student id: " + theId);

    appDAO.deleteStudentById(theId);

    System.out.println("Done!");
  }

  private void addMoreCoursesForStudent(AppDAO appDAO) {
    int theId = 2;
    Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

    // Create more courses
    Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
    Course tempCourse2 = new Course("Atari 2600 - Game Development");

    // Add courses to student
    tempStudent.addCourse(tempCourse1);
    tempStudent.addCourse(tempCourse2);

    System.out.println("Updating student: " + tempStudent);
    System.out.println("associated courses: " + tempStudent.getCourses());

    appDAO.update(tempStudent);

    System.out.println("Done!");
  }

  private void findStudentAndCourses(AppDAO appDAO) {
    int theId = 2;
    Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

    System.out.println("Loaded student: " + tempStudent);
    System.out.println("Courses: " + tempStudent.getCourses());

    System.out.println("Done!");
  }

  private void findCourseAndStudents(AppDAO appDAO) {
    int theId = 10;
    Course tempCourse = appDAO.findCourseAndStudentByCourseId(theId);

    System.out.println("Loaded course: " + tempCourse);
    System.out.println("Students: " + tempCourse.getStudents());

    System.out.println("Done!");
  }

  private void createCourseAndStudent(AppDAO appDAO) {
    // Create a course
    Course tempCourse = new Course("Pacman - How To Score One Million Points");

    // Create the students
    Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
    Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

    // Add students to the course
    tempCourse.addStudent(tempStudent1);
    tempCourse.addStudent(tempStudent2);

    // Save the course and associated students
    System.out.println("Saving the course: " + tempCourse);
    System.out.println("associated students: " + tempCourse.getStudents());

    appDAO.save(tempCourse);

    System.out.println("Done!");
  }

  private void deleteCourseAndReviews(AppDAO appDAO) {
    int theId = 10;
    System.out.println("Deleting course id: " + theId);
    appDAO.deleteCourseById(theId);
    System.out.println("Done!");
  }

  private void retrieveCourseAndReviews(AppDAO appDAO) {
    // Get the course and reviews
    int theId = 10;
    Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

    // Print the course
    System.out.println(tempCourse);

    // Print the reviews
    System.out.println(tempCourse.getReviews());
  }

  private void createCourseAndReviews(AppDAO appDAO) {
    // Create a course
    Course tempCourse = new Course("Pacman - How to Score One Million Points");

    // Add some reviews
    tempCourse.addReview(new Review("Great course ... loved it!"));
    tempCourse.addReview(new Review("Cool course, job well done."));
    tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

    // Save the course ... and leverage the cascade all
    System.out.println("Saving the courses");
    System.out.println(tempCourse);
    System.out.println(tempCourse.getReviews());

    appDAO.save(tempCourse);

    System.out.println("Done!");
  }

  private void deleteCourse(AppDAO appDAO) {
    int theId = 10;

    System.out.println("Deleting course id: " + theId);

    appDAO.deleteCourseById(theId);

    System.out.println("Done!");
  }

  private void updateCourse(AppDAO appDAO) {
    int theId = 10;

    // Find the course
    System.out.println("Finding course id: " + theId);
    Course tempCourse = appDAO.findCourseById(theId);

    // Update the course
    System.out.println("Updating course id: " + theId);
    tempCourse.setTitle("Enjoy the Simple Things");

    appDAO.update(tempCourse);

    System.out.println("Done!");
  }

  private void updateInstructor(AppDAO appDAO) {
    int theId = 1;
    // Find the instructor
    System.out.println("Finding instructor id: " + theId);
    Instructor tempInstructor = appDAO.findInstructorById(theId);

    // Update the instructor
    System.out.println("Updating instructor id: " + theId);
    tempInstructor.setLastName("TESTER");

    appDAO.update(tempInstructor);

    System.out.println("Done!");
  }

  private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
    int theId = 1;

    // Find the instructor
    System.out.println("Finding instructor id: " + theId);
    Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

    System.out.println("tempInstructor: " + tempInstructor);
    System.out.println("the associated courses: " + tempInstructor.getCourses());

    System.out.println("Done!");
  }

  private void findCoursesForInstructor(AppDAO appDAO) {
    int theId = 1;

    // Find instructor
    System.out.println("Finding instructor id: " + theId);

    Instructor tempInstructor = appDAO.findInstructorById(theId);

    System.out.println("tempInstructor: " + tempInstructor);

    // Find courses for instructor
    System.out.println("Finding courses for instructor id: " + theId);
    List<Course> courses = appDAO.findCoursesByInstructorId(theId);

    // Associate the objects
    tempInstructor.setCourses(courses);

    System.out.println("the associated courses: " + tempInstructor.getCourses());

    System.out.println("Done!");
  }

  private void findInstructorWithCourses(AppDAO appDAO) {
    int theId = 1;

    System.out.println("Finding instructor id: " + theId);

    Instructor tempInstructor = appDAO.findInstructorById(theId);

    System.out.println("tempInstructor: " + tempInstructor);
    System.out.println("the associated courses: " + tempInstructor.getCourses());

    System.out.println("Done!");
  }

  private void createInstructorWithCourses(AppDAO appDAO) {
    // Create the instructor
    Instructor tempInstructor = new Instructor(
        "Susan",
        "Public",
        "susan.public@luv2code.com"
    );

    // Create the instructor detail
    InstructorDetail tempInstructorDetail = new InstructorDetail(
        "http://www.youtube.com",
        "Video Games"
    );

    // Associate the objects
    tempInstructor.setInstructorDetail(tempInstructorDetail);

    // Create some courses
    Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
    Course tempCourse2 = new Course("The Pinball Masterclass");

    // Add courses to instructor
    tempInstructor.add(tempCourse1);
    tempInstructor.add(tempCourse2);

    // Save the instructor
    // NOTE: this will also save the courses because of CascadeType.PERSIST
    System.out.println("Saving instructor: " + tempInstructor);
    System.out.println("The courses: " + tempInstructor.getCourses());
    appDAO.save(tempInstructor);

    System.out.println("Done!");
  }

  private void deleteInstructorDetail(AppDAO appDAO) {
    int theId = 3;
    System.out.println("Deleting instructor detail id: " + theId);
    appDAO.deleteInstructorDetailById(theId);
    System.out.println("Done!");
  }

  private void findInstructorDetail(AppDAO appDAO) {
    // Get the instructor detail object
    int theId = 2;
    InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

    // Print the instructor detail
    System.out.println("tempInstructorDetail: " + tempInstructorDetail);

    // Print the associated instructor
    System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

    System.out.println("Done!");
  }

  private void deleteInstructor(AppDAO appDAO) {
    int theId = 1;
    System.out.println("Deleting instructor id: " + theId);
    appDAO.deleteInstructorById(theId);
    System.out.println("Done!");
  }

  private void findInstructor(AppDAO appDAO) {
    int theId = 2;

    System.out.println("Finding instructor id: " + theId);
    Instructor tempInstructor = appDAO.findInstructorById(theId);

    System.out.println("tempInstructor: " + tempInstructor);
    System.out.println("the associate instructorDetail only: " + tempInstructor.getInstructorDetail());
  }

  private void createInstructor(AppDAO appDAO) {
    /*// Create the instructor
    Instructor tempInstructor = new Instructor(
        "Chad",
        "Darby",
        "darby@luv2code.com"
    );

    // Create the instructor detail
    InstructorDetail tempInstructorDetail = new InstructorDetail(
        "http://www.luv2code.com/youtube",
        "Luv 2 code!!!"
    );*/

    // Create the instructor
    Instructor tempInstructor = new Instructor(
        "Madhu",
        "Patel",
        "madhu@luv2code.com"
    );

    // Create the instructor detail
    InstructorDetail tempInstructorDetail = new InstructorDetail(
        "http://www.luv2code.com/youtube",
        "Guitar"
    );

    // Associate the objects
    tempInstructor.setInstructorDetail(tempInstructorDetail);

    // Save the instructor
    // NOTE: this will also save the detail object because of CascadeType.ALL
    System.out.println("Saving instructor: " + tempInstructor);
    appDAO.save(tempInstructor);

    System.out.println("Done!");
  }
}
