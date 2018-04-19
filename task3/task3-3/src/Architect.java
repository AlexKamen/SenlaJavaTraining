
public class Architect extends Employee {

	//оклад
	private static final int BASE_SALARY = 30000;
	
	//количество выполненных проектов
	private int countProjects = 0;
	//надбавки
	private int premium = 0;
	
	Architect (String family, String name, int experience, int cntProj) {
		this.family = family;
		this.name = name;
		this.experience = experience;
		this.countProjects = cntProj;
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

		if (this.countProjects > 0) {
			prem += 1000 * this.countProjects;
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
