package almaszy.pokerapp;

import almaszy.pokerapp.model.Employee;
import almaszy.pokerapp.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PokerappApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository repository;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employee/all",
                HttpMethod.GET, entity, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee();
        employee.setName("admin");
        employee.setLastname("admin");
        employee.setTelnumber("1233456");
        employee.setEmail("admin@admin.com");
        ResponseEntity<Employee> postResponse = restTemplate
                .postForEntity(getRootUrl() + "/employee/add", employee, Employee.class);
        assertEquals(200, postResponse.getStatusCode().value());
    }

    @Test
    public void TestFindEmployeeById() {
        int id = 155;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employee/find/" + id, Employee.class);
        assertNotNull(employee.getName());
    }

    @Test
    public void updateEmployeeTel() {
        int id = 155;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employee/find/" + id, Employee.class);
        assertNotNull(employee.getName());
        employee.setTelnumber("333444555");
        restTemplate.put(getRootUrl() + "/employee/updatetel/" + id, employee);
    }

    @Test
    public void updateEmployeeEmail() {
        int id = 155;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employee/find/" + id, Employee.class);
        assertNotNull(employee);
        employee.setTelnumber("adminxx@admin.com");
        restTemplate.put(getRootUrl() + "/employee/updatemail/" + id, employee);
    }

    /*@Test
    public void testDeleteEmployee() {
        int id = 154;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employee/delete/" + id, Employee.class);
        assertNotNull(employee);
        restTemplate.delete(getRootUrl() + "/employee/delete/" + id);
        try {
            restTemplate.getForObject(getRootUrl() + "/employee/delete/" + id, Employee.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }*/
}
