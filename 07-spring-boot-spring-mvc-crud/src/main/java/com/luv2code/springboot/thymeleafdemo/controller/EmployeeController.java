package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

  private EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/list")
  public String listEmployees(Model theModel) {
    // Get the employees from db
    List<Employee> theEmployees = employeeService.findAll();

    // Add to the Spring model
    theModel.addAttribute("employees", theEmployees);

    return "employees/list-employees";
  }

  @GetMapping("/showFormForAdd")
  public String showFormForAdd(Model theModel) {
    // Create model attribute to bind form data
    Employee theEmployee = new Employee();

    theModel.addAttribute("employee", theEmployee);

    return "employees/employee-form";
  }

  @GetMapping("/showFormForUpdate")
  public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
    // Get the employee from the service
    Employee theEmployee = employeeService.findById(theId);

    // Set employee in the model to prepopulate the form
    theModel.addAttribute("employee", theEmployee);

    // Send over to our form
    return "employees/employee-form";
  }

  @PostMapping("/save")
  public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
    // Save the employee
    employeeService.save(theEmployee);

    // Use a redirect to prevent duplicate submissions
    return "redirect:/employees/list";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam("employeeId") int theId) {
    // Delete the employee
    employeeService.deleteById(theId);

    // Redirect to the /employees/list
    return "redirect:/employees/list";
  }
}
