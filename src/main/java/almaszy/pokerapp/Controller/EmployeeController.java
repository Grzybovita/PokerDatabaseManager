package almaszy.pokerapp.Controller;


import almaszy.pokerapp.Model.Employee;
import almaszy.pokerapp.Repository.EmployeeRepository;
import almaszy.pokerapp.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(path="/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @PostMapping(path="/add")
    public String addNewEmployee (
            @Valid
            @RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        employeeRepository.save(employee);
        return "Saved!";
    }


}
