package com.qfedu.entity;

/*
 球员实体类：
 	成员变量：
 		姓名，编号，年龄，工资，位置
 	成员方法：
 		投篮，传球
 */
public class Player {
	//成员变量
	private String name;
	private int id; //实现ID的自动增长，这里要使用计数器
	private int age;
	private double salary;
	private String location;
	
	private static int count = 1; //计数器
	
	//构造代码块来完成计数器操作
	{
		this.id = count++;
	}
	
	public Player() {}
	
	public Player(String name, int age, double salary, String location) {
		//这里使用自己的set方法，来完成赋值成员变量的操作
		this.setName(name);
		this.setAge(age);
		this.setSalary(salary);
		this.setLocation(location);
	}
	
	public int getId() {
		return id;
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
		if (age <= 0 || age > 45) {
			this.age = 19;
		} else {
			this.age = age;
		}
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		if (salary <= 0) {
			this.salary = 1;
		} else {
			this.salary = salary;
		}
 	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	//普通的成员方法页搞定
	public void shot() {
		System.out.println(this.getName() + "投篮练习中~~~");
	}
	
	public void passBall() {
		System.out.println(this.getName() + "传球练习中~~~");
	}

	//重写的Java中超类【Object】类中toString,toString是该对象的描述
	//当通过System.out.println(player)会自动调用的方法
	@Override
	public String toString() {
		return "[ID:" + this.id + " Name:" + this.name + " Age:" + this.age
				+ " Salary:" + this.salary + " Location:" + this.location + "]";
	}
}
