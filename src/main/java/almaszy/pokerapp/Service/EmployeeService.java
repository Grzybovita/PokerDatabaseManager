package almaszy.pokerapp.Service;

import almaszy.pokerapp.Model.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    public Iterable<Employee> findAll();

    public void delete(int id);

    public Employee update(Employee employee);


}
