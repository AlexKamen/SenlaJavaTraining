
public class Tester extends Employee{

	//оклад
	private static final int BASE_SALARY = 20000;
	
	//использует ли автоматизированное тестирование
	private boolean useAutomatic = false;
	//надбавки
	private int premium = 0;

	Tester (String family, String name, int experience, boolean automat) {
		this.family = family;
		this.name = name;
		this.experience = experience;
		this.useAutomatic = automat;
	}

	private int getPremium () {
		int prem = 0;
		if (this.useAutomatic == true) {
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
