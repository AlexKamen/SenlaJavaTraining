
public class Employee {
	protected String family;
	protected String name;
	protected int experience;
	protected int salary = 0;

	Employee () {
		
	}

	Employee (String f, String n, int e) {
		this.family = f;
		this.name = n;
		this.experience = e;
	}

	public int getSalary() {
		return this.salary;
	}
	
}