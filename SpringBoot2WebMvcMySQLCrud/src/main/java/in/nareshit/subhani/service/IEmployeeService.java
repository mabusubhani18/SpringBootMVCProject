package in.nareshit.subhani.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.subhani.model.Employee;

public interface IEmployeeService {
	 Integer saveEmployee(Employee e);
	 
	 List<Employee> getAllEmployees();
	 void deleteEmployee(Integer id);
	 //Optional<Employee> getOneEmployee(Integer id);
	 Employee getOneEmployee(Integer id);
	 void updateEmployee(Employee e);
	 boolean isEmployeeEmailExist(String empMail);


}
