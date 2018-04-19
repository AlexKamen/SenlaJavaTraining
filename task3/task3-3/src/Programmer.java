
public class Programmer extends Employee {

	//оклад
	private static final int BASE_SALARY = 25000;
	
	//надбавки
	private int premium = 0;
	
	Programmer (String family, String name, int experience) {
		this.family = family;
		this.name = name;
		this.experience = experience;
	}

	private int getPremium () {
		int prem = 0;
		if (this.experience >= 1 && this.experience < 2) {
			prem += 2000; 
		}
		else if (this.experience >= 2 && this.experience < 5) {
			prem += 3000;
		}
		else if (this.experience >= 2 && this.experience < 5) {
			prem += 4000;
		}
		else if (this.experience >= 5) {
			prem += 5000;
		}

		return prem;

	}

	public int getSalary() {
		//если зарплата не расcчитывалась, расcчитываем
		if (this.salary == 0) {
			this.premium = this.getPremium();
			this.salary = this.BASE_SALARY + this.premium;
		}

		return this.salary;
	}
}
