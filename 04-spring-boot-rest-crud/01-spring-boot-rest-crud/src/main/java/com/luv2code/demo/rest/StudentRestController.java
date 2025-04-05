package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
  private List<Student> theStudents;

  // Define @PostConstruct to load the student data only once!
  @PostConstruct
  public void loadData() {
    theStudents = new ArrayList<>();

    theStudents.add(new Student("Porrima", "Patel"));
    theStudents.add(new Student("Mario", "Rossi"));
    theStudents.add(new Student("Mary", "Smith"));
  }

  // Define endpoint for "/students" - return a list of students
  @GetMapping("/students")
  public List<Student> getStudents() {
    return theStudents;
  }

  // Define endpoint or "/students/{studentId}" - return student at index
  @GetMapping("/students/{studentId}")
  public Student getStudent(@PathVariable int studentId) {
    // Check the studentId again list size
    if (studentId < 0 || studentId >= theStudents.size()) {
      throw new StudentNotFoundException("Student id not found - " + studentId);
    }

    // Just index into the list... keep it simple for now
    return theStudents.get(studentId);
  }
}
