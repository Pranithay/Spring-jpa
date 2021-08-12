package com.fis.springjpa.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/emp") // This means URL's start with /demo (after Application path)
public class EmployeeController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private EmployeeRepository employeeRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewEmployee (@RequestBody Employee employee) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    employeeRepository.save(employee);
    return "Saved";
  }
  @PutMapping(path="/edit")
  public String updateProduct(@RequestBody Employee employee) {
  	Optional<Employee> e = employeeRepository.findById(employee.getId());
  	if (e.isPresent()) {
  		employeeRepository.save(employee);
  		return "Updated";
  	} else {
  		return "Employee Not Found!!";
  	}
  }
  @DeleteMapping(path="/delete")
  public String deleteProduct(@RequestParam int id) {
  	Optional<Employee> p = employeeRepository.findById(id);
  	if (p.isPresent()) {
  		employeeRepository.delete(p.get());
  		return "Deleted";
  	} else {
  		return "Employee Not Found!!";
  	}
  }

  @GetMapping("/{id}")
  public Object getAProduct(@PathVariable int id) {
  	Optional<Employee> p = employeeRepository.findById(id);
  	if (p.isPresent()) {
  		return p.get();
  	} else {
  		return "Employee Not Found!!";
  	}
  }
  @GetMapping(path="/all")
  public @ResponseBody Iterable<Employee> getAllUsers() {
    // This returns a JSON or XML with the users
    return employeeRepository.findAll();
  }
}