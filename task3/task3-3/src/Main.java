
public class Main {

	public static void main(String[] args) {
		Architect arch1 = new Architect("Сергеев", "Василий", 2, 3);
		Architect arch2 = new Architect("Петров", "Дмитрий", 15, 10);
		Designer design = new Designer("Сметеева", "Екатерина", 1);
		Programmer prog = new Programmer("Ветров", "Андрей", 10);
		Tester test1 = new Tester("Тестовый", "Дмитрий", 5, true);
		Tester test2 = new Tester("Тестовый", "Аркадий", 1, false);

		//Общая месячная получка сотрудников
		long totalSalary = arch1.getSalary() + arch2.getSalary() + design.getSalary() + prog.getSalary() + test1.getSalary() + test2.getSalary();
		System.out.println("Total salary: "+totalSalary);

	}

}
