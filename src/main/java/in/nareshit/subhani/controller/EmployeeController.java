package in.nareshit.subhani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.subhani.exception.EmployeeNotFoundException;
import in.nareshit.subhani.model.Employee;
import in.nareshit.subhani.service.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService service;
	
	//1.To Disply Register page
	@GetMapping("/register")
	public String showRegPage() {
		return "EmployeeRegister";
	}
	
	@PostMapping("/save")
	public String saveEmp(
			@ModelAttribute Employee employee,
			Model model
			)
	{
		Integer id=service.saveEmployee(employee);
		String msg = "Employee '"+id+"' saved";
		
		model.addAttribute("message",msg);
		return "EmployeeRegister";
	}
	
	@GetMapping("/all")
	public String showAllEmps(Model model) {
		getUiEmployeeTableData(model);
		return "EmployeeData";

	}
	
	private void getUiEmployeeTableData(Model model) {
		List<Employee> list=service.getAllEmployees();
		model.addAttribute("list",list);
	}
	
	@GetMapping("/delete")
	public String deleteEmp(
					@RequestParam("id") Integer empId,
					Model model
				)
	{
		String page="EmployeeData";
		try {
			service.deleteEmployee(empId);
			model.addAttribute("msg","Employee '"+empId+"' Deleted");
			//page="EmployeeData";
		}catch(EmployeeNotFoundException enf) {
			//throw enf;
			page="EmployeeData";
			model.addAttribute("msg",enf.getMessage());					
		} catch(Exception e) {			
			e.printStackTrace();
		}
		
		getUiEmployeeTableData(model);
		
		return page;

	}
	
	@GetMapping("/edit")
	public String showEdit(
				@RequestParam("id") Integer id,
				Model model
				) {
		/*String page=null;
		Optional<Employee> opt=service.getOneEmployee(id);
		
		if(opt.isPresent()) {
			Employee e=opt.get();
			model.addAttribute("employee", e);
			page="EmployeeEdit";
		}else {
			page="redirect:all";
		}
		
		return page;*/
		String page="";
		try {
		
			Employee emp=service.getOneEmployee(id);
			model.addAttribute("employee", emp);
			page="EmployeeEdit";
		} catch(EmployeeNotFoundException enf) {
			//throw enf;
			page="EmployeeData";
			model.addAttribute("msg",enf.getMessage());
			getUiEmployeeTableData(model);
		} catch(Exception e) {			
			e.printStackTrace();
		}
		
		
		return page;
	}
	
	@PostMapping("/update")
	public String updateEmployee(
							@ModelAttribute Employee employee,
							Model model
							)
	{
		service.updateEmployee(employee);
		
		List<Employee> list = service.getAllEmployees();
		model.addAttribute("list", list);
		model.addAttribute("msg", "Employee '"+employee.getEmpId()+"' Updated!!");
		return "EmployeeData";
		//return "redirect:all";

	}
	
	@GetMapping("/validateMail")
	public @ResponseBody String validateEmail(
				@RequestParam("email") String empMail
				) 
	{
		String message="";
		
		if(service.isEmployeeEmailExist(empMail)) {
			message=empMail+", All Ready existed";
		}
		
		return message;

	}
}
