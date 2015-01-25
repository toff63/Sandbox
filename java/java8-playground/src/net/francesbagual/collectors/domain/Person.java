package net.francesbagual.collectors.domain;

public class Person {

	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("setName called with parameter: " + name);
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		System.out.println("setAge called with parameter: " + age);
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
