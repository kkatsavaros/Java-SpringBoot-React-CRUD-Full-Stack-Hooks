package kks.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kks.exception.ResourceNotFoundException;
import kks.model.Employee;
import kks.repositiry.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@CrossOrigin(origins = "http://localhost:3000") // Για το React.
//@CrossOrigin("*") // Για το React.
@RestController // Γιατί θα δημιουργήσουμε Rest API.
@RequestMapping("api/v1")
public class EmployeeControler {

    @Autowired  // Κάνω inject το EmployeeRepository.
    private EmployeeRepository employeeRepository;

    
    //Get all Employees.
    @GetMapping("/test")
    public String test() {
        return ("<h1>Test</h1>");
    }
    
    //Get all Employees.
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //Create Employee Rest API
    // @RequestBody: Θα παραλάβω JSON και να το μετατρέψω σε Java Object
    // για να γίνει το insert.
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get employee by id rest api.
    // ResponseEntity: JSON to Java Object.
    // {/* GoToUpdateForm GTUF:7*/}
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id: " + id));

        return ResponseEntity.ok(employee);
    }

    // Update Employee rest api.   
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id: " + id));

        employee.setFirstname(employeeDetails.getFirstname());
        employee.setLastname(employeeDetails.getLastname());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete Employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id: " + id));

        employeeRepository.delete(employee);
        // Map<String, Boolean> response = new HashMap<>();
        // response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete Employee rest api
//    @DeleteMapping("/employees/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
//
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not exists with id: " + id));
//
//        employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//
//    }
}



