package almaszy.pokerapp.exception;

public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException(int id) {
        System.out.println("Employee not found exception!");
    }
}
