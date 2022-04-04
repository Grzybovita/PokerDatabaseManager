package almaszy.pokerapp.controller;


import almaszy.pokerapp.exception.EmployeeNotFoundException;
import almaszy.pokerapp.model.Employee;
import almaszy.pokerapp.repository.EmployeeRepository;
import almaszy.pokerapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@Validated
@RequestMapping(path="/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    Logger logger = Logger.getLogger(EmployeeController.class.getName());

    @PostMapping(path="/add")
    public void addNewEmployee (
            @Valid
            @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            logger.log(Level.WARNING, "Errors occured when trying to add new employee!");
        } else {
            employeeRepository.save(employee);
            logger.log(Level.WARNING, "New employee has been added!");
        }
    }

    @GetMapping(path="/find/{id}")
    public @ResponseBody
    Employee getEmployeeById (@PathVariable("id") int id) throws EmployeeNotFoundException {
        if (employeeRepository.findById(id).isPresent()) {
            return employeeRepository.findById(id).get();
        }
        else {
            logger.log(Level.WARNING, "Employee not found");
            throw new EmployeeNotFoundException(id);
        }
    }

    @PostMapping (path="/updatetel/{id}")
    public void updateEmployeeTel (@RequestBody @PathVariable("id") int id, String telnumber) throws EmployeeNotFoundException {
        if (employeeRepository.findById(id).isPresent()) {
            Employee p = employeeRepository.getById(id);
            p.setTelnumber(telnumber);
            employeeRepository.save(p);
            logger.log(Level.ALL, "Employee {} tel. number updated!", id);
        } else {
            logger.log(Level.WARNING, "Employee {} not found!", id);
            throw new EmployeeNotFoundException(id);
        }
    }

    @PostMapping (path="/updatemail/{id}")
    public void updateEmployeeEmail (
            @RequestBody
            @PathVariable("id") int id, String email) throws EmployeeNotFoundException {
        if (employeeRepository.findById(id).isPresent()) {
            Employee p = employeeRepository.getById(id);
            p.setEmail(email);
            employeeRepository.save(p);
            logger.log(Level.ALL, "employee {} email has been updated!", id);
        } else {
            logger.log(Level.WARNING, "employee {} not found!", id);
            throw new EmployeeNotFoundException(id);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Employee> getAllEmployees() {
        // This returns a JSON or XML with the users
        return employeeRepository.findAll();
    }

    @GetMapping(path="/delete/{id}")
    public @ResponseBody void deleteEmployeeById (@PathVariable("id") int id) throws EmployeeNotFoundException {
        if (employeeRepository.findById(id).isPresent()) {
            employeeService.delete(id);
            logger.log(Level.ALL, "Employee {} deleted!", id);
        } else {
            logger.log(Level.WARNING, "Employee {} not found!", id);
            throw new EmployeeNotFoundException(id);
        }
    }
}
