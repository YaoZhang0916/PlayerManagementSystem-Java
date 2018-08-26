package com.entiy;
//name
//age
//id

public class Player {
	private String name;
	private int id;//implement auto increase, need counter 
	private int age;
	private double salary;
	private String location;
	
	private static int count = 1;
	
	//Building block
	//In class directly define by {}, Execute each time an object is created
	{
		this.id = count++;
	}
	
	
	//constructor 
	public Player(){}
	
	public Player(String name, int age, double salary, String location){
		//used set method, 
		this.setName(name);
		this.setAge(age);
		this.setSalary(salary);
		this.setLocation(location);
	}
	
	public int getID(){
		return this.id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if(age<-0||age>45){
			this.age = 19;
		}else{
			this.age = age;
		}
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		if(salary <=0){
			this.salary = 1;
		}else{
			this.salary = salary;
		}
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	public void shot(){
		System.out.println(this.getName()+" Practic shotting ");
	}
	
	public void passBall(){
		System.out.println(this.getName()+" Practic pass ball");
		
	}
	
	//override toString class 
	public String toString(){
		return "[ID:" + this.id + " Name: "+ this.name + " Age: "+ this.age + " Salasry: "+ this.salary +
				" Location: "+ this.location+ "]";
	}
	

}
