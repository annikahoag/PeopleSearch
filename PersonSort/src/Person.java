
public class Person {

	//instance variables 
	private String name;
	private int age;
	
	//constructor 
	public Person(String n, int a) {
		name = n;
		age = a;
	}
	
	
	//return name
	public String getName() {
		return name;
	}
	
	//return age
	public int getAge() {
		return age;
	}
	
	//sets name
	public void setName(String n) {
		name = n;
	}
	
	//sets age 
	public void setAge(int a) {
		age = a;
	}
}
