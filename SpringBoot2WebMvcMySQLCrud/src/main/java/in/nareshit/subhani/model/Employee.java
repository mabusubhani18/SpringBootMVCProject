package in.nareshit.subhani.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="employee_tab")
public class Employee {
	
	@Id
	@Column(name="emp_id_col")
	@GeneratedValue
	private Integer empId;
	@Column(name="emp_name_col")
	private String empName;
	@Column(name="emp_sal_col")
	private Double empSal;
	@Column(name="emp_dept_col")
	private String empDept;
	@Column(name="emp_email_col")
	private String empMail;
	@Column(name="emp_addr_col")
	private String empAddr;
	@Column(name="emp_hra_col")
	private Double empHra;
	@Column(name="emp_ta_col")
	private Double empTa;
}
