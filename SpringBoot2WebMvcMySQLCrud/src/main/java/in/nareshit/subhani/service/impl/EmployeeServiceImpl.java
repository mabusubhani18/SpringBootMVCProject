package in.nareshit.subhani.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.subhani.exception.EmployeeNotFoundException;
import in.nareshit.subhani.model.Employee;
import in.nareshit.subhani.repo.EmployeeRepository;
import in.nareshit.subhani.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeRepository repo;
	
	public Integer saveEmployee(Employee e) {
		
		//---calculations--
		var sal = e.getEmpSal();
		if(sal!=null) {
		var hra = sal * 12/100;
		var ta = sal * 3/100;
		
		//set data to model cls obj
		e.setEmpHra(hra);
		e.setEmpTa(ta);
		}
		e = repo.save(e);
		Integer empId = e.getEmpId();
		return empId;

	}
	
	public List<Employee> getAllEmployees() {
		List<Employee> list = repo.findAll();
		return list;

	}
	
	 public void deleteEmployee(Integer id) {
		 //repo.deleteById(id);
		 repo.delete(getOneEmployee(id));
	 }

		/*
		 * public Optional<Employee> getOneEmployee(Integer id) { return
		 * repo.findById(id); } 
		 */
	 public Employee getOneEmployee(Integer id) { 
		 
		 Optional<Employee> opt=repo.findById(id);
			/*
			 * if(opt.isEmpty()) { throw new
			 * EmployeeNotFoundException("Employee '"+id+"' Not Exist"); }else { return
			 * opt.get(); }
			 */
		 //OR
		 return repo.findById(id).orElseThrow(
				 ()->new EmployeeNotFoundException(
						 "Employee '"+id+"' Not Exist")
				 );
	//	 return repo.findById(id); 
	 }

	 public void updateEmployee(Employee e) {
		//---calculations--
			var sal = e.getEmpSal();
			var hra = sal * 12/100;
			var ta = sal * 3/100;
			
			//set data to model cls obj
			e.setEmpHra(hra);
			e.setEmpTa(ta);
			
			e = repo.save(e);

	 }
	 
	 public boolean isEmployeeEmailExist(String empMail) {
		 return repo.getEmployeeEmailCount(empMail)>0;

	 }


}
