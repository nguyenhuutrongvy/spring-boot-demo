package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(CruddemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(AppDAO appDAO) {
    return runner -> {
//      createInstructor(appDAO);
//      findInstructor(appDAO);
      deleteInstructor(appDAO);
    };
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
