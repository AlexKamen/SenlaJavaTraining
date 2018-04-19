
public class Designer extends Employee{

	//оклад
	private static final int BASE_SALARY = 25000;
	
	Designer (String family, String name, int experience) {
		this.family = family;
		this.name = name;
		this.experience = experience;
		this.salary = this.BASE_SALARY;
	}

	
}
