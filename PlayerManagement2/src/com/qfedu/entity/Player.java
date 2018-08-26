package com.qfedu.entity;

/*
 ��Աʵ���ࣺ
 	��Ա������
 		��������ţ����䣬���ʣ�λ��
 	��Ա������
 		Ͷ��������
 */
public class Player {
	//��Ա����
	private String name;
	private int id; //ʵ��ID���Զ�����������Ҫʹ�ü�����
	private int age;
	private double salary;
	private String location;
	
	private static int count = 1; //������
	
	//������������ɼ���������
	{
		this.id = count++;
	}
	
	public Player() {}
	
	public Player(String name, int age, double salary, String location) {
		//����ʹ���Լ���set����������ɸ�ֵ��Ա�����Ĳ���
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
	
	//��ͨ�ĳ�Ա����ҳ�㶨
	public void shot() {
		System.out.println(this.getName() + "Ͷ����ϰ��~~~");
	}
	
	public void passBall() {
		System.out.println(this.getName() + "������ϰ��~~~");
	}

	//��д��Java�г��ࡾObject������toString,toString�Ǹö��������
	//��ͨ��System.out.println(player)���Զ����õķ���
	@Override
	public String toString() {
		return "[ID:" + this.id + " Name:" + this.name + " Age:" + this.age
				+ " Salary:" + this.salary + " Location:" + this.location + "]";
	}
}
