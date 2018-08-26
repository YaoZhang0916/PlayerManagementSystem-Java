package com.qfedu.dao;

import java.util.Scanner;

import com.qfedu.entity.Player;

public class TeamManager {
	//球队的名字
	private String teamName;
	//保存球员信息的数组
	private Player[] allPlayers = new Player[defaultCount];

	//统计当前球队中有多少球员
	private static int itemCount = 0; //元素个数
	//球队的默认球员个数
	private static final int defaultCount = 10; 

	public TeamManager() {}

	public TeamManager(String teamName) {
		this.setTeamName(teamName);
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	/*
	 * 添加球员
	 * 解雇球员
	 * 修改球员信息
	 * 查询球员信息
	 * 
	 * 排序算法
	 * 
	 * 数组增长
	 */
	/**
	 * 添加新球员到数组中
	 * @param playerToAdd 要添加的球员类对象
	 */
	public void addPlayer(Player playerToAdd) {
		//参数合法性判断(以后叫【异常处理】)
		if (null == playerToAdd) {
			System.out.println("球员信息为空，不可添加");
			return;
		}

		//类内有一个itemCount的静态成员变量，是用来统计插入的元素个数，而且也是下个元素保存的下标位置
		//因为是插入操作，所以要考虑数组的容量问题，如果插入的数据个数已经大于了数组的长度，需要扩容
		if (itemCount >= allPlayers.length) {
			//扩容
			grow();
		}

		allPlayers[itemCount++] = playerToAdd;
	}

	/**
	 * 通过球员的ID删除球员
	 * @param playerID
	 */
	public void layoffPlayerByPlayerID(int playerID) {
		//需求查询方法，调用类内私有化通过球员ID查询球员在数组中位置的方法，获取下标
		int index = findPlayerIndexByPlayerID(playerID);

		if (index >= 0) {
			//删除该位置的球员，数组整体左移
			/*   1 2 3 4 5
			 index 1   << 3
			 index 3   << 1
			 */
			for (int i = index; i < itemCount - 1; i++) {
				allPlayers[i] = allPlayers[i + 1];
			}
			//原本最后一个有效元素赋值为null
			allPlayers[itemCount - 1] = null;

			//球员的球员个数 - 1
			itemCount--;
		} else {
			System.out.println("查无此人，无法删除");
		}
	}

	/**
	 * 通过球员的ID，来查询球员的信息
	 * @param playerID 要展示的球员ID号
	 */
	public void showPlayerInfoByPlayerID(int playerID) {
		int index = findPlayerIndexByPlayerID(playerID);

		if (index > -1) {
			System.out.println(allPlayers[index]);
		} else {
			System.out.println("查无此人");
		}
	}

	/**
	 * 通过球员ID 修改球员信息
	 * @param playerID 需要修改信息的球员ID
	 */
	public void modifyPlayerInfoByPlayerID(int playerID) {
		int index = findPlayerIndexByPlayerID(playerID);
		Scanner sc = new Scanner(System.in);
		//表示找到球员，进行修改操作
		if (index > -1) {

			//while(true) switch - case
			int flag = 0;
			int choose = -1;
			Player temp = allPlayers[index];

			while (true) {
				System.out.println("修改" + temp.getId() + ":" + temp.getName() + "的信息");
				System.out.println("***Age:" + temp.getAge());
				System.out.println("***Salary:" + temp.getSalary());
				System.out.println("***Location:" + temp.getLocation());

				System.out.println("1. 修改球员姓名");
				System.out.println("2. 修改球员年龄");
				System.out.println("3. 修改球员工资");
				System.out.println("4. 修改球员位置");
				System.out.println("5. 退出");

				choose = sc.nextInt();
				sc.nextLine();

				switch (choose) {
				case 1:
					System.out.println("请输入球员的姓名:");
					String name = sc.nextLine();
					temp.setName(name);
					break;
				case 2:
					System.out.println("请输入球员的年龄:");
					int age = sc.nextInt();
					temp.setAge(age);
					break;
				case 3:
					System.out.println("请输入球员的工资:");
					double salary = sc.nextDouble();
					temp.setSalary(salary);
					break;
				case 4:
					System.out.println("请输入球员的位置:");
					String location = sc.nextLine();
					temp.setLocation(location);
					break;
				case 5:
					flag = 1;
					break;
				default:
					System.out.println("选择错误");
					break;
				} //switch(choose) - case

				if (1 == flag) {
					allPlayers[index] = temp;
					System.out.println("保存退出");
					break;
				}

			} //while (true)

		} else {
			System.out.println("查无此人");
		}
		//sc.close();
	} 

	/**
	 * 工资的降序排序
	 */
	public void descendingSelectSortBySalary() {
		//保护源数据！！！
		//1. 准备一个数组专门用来做排序操作，数组的大小和源数据有效元素个数一致
		Player[] sortArray = new Player[itemCount];

		//2. 数据拷贝
		for (int i = 0; i < itemCount; i++) {
			sortArray[i] = allPlayers[i];
		}

		//外层控制选择排序的次数
		for (int i = 0; i < itemCount - 1; i++) {
			int index = i;

			for (int j = i + 1; j < itemCount; j++) {
				if (sortArray[index].getSalary() < sortArray[j].getSalary()) {
					index = j;
				}
			}

			if (index != i) {
				Player temp = sortArray[index];
				sortArray[index] = sortArray[i];
				sortArray[i] = temp;
			}
		}

		showSortResult(sortArray);
	}

	/**
	 * 年龄的升序排序
	 */
	public void ascendingSelectSortByAge() {
		//保护源数据！！！
		//1. 准备一个数组专门用来做排序操作，数组的大小和源数据有效元素个数一致
		Player[] sortArray = new Player[itemCount];

		//2. 数据拷贝
		for (int i = 0; i < itemCount; i++) {
			sortArray[i] = allPlayers[i];
		}

		//外层控制选择排序的次数
		for (int i = 0; i < itemCount - 1; i++) {
			int index = i;

			for (int j = i + 1; j < itemCount; j++) {
				if (sortArray[index].getAge() > sortArray[j].getAge()) {
					index = j;
				}
			}

			if (index != i) {
				Player temp = sortArray[index];
				sortArray[index] = sortArray[i];
				sortArray[i] = temp;
			}
		}

		showSortResult(sortArray);
	}

	/**
	 * 自己研究一下冒泡排序
	 */
	public void descendingBubbleSortByAge() {
		//保护源数据！！！
		//1. 准备一个数组专门用来做排序操作，数组的大小和源数据有效元素个数一致
		Player[] sortArray = new Player[itemCount];

		//2. 数据拷贝
		for (int i = 0; i < itemCount; i++) {
			sortArray[i] = allPlayers[i];
		}

		//外层控制比较的轮次
		for (int i = 0; i < itemCount - 1; i++) {
			//内层控制两两比较次数
			for (int j = 0; j < itemCount - i - 1; j++) {

				if (sortArray[j].getAge() < sortArray[j + 1].getAge()) {
					Player temp = sortArray[j];
					sortArray[j] = sortArray[j + 1];
					sortArray[j + 1] = temp;
				}
			}
		} 

		showSortResult(sortArray);

	}



	public void showAllPlayers() {
		for (Player player : allPlayers) {
			if (null == player) {
				break;
			}
			System.out.println(player);
		}
	}

	/**
	 * 需要一个扩容方法，方法不需要参数，不要类外调用，只是在类内使用
	 */
	private void grow() {
		//1. 获取原数组元素个数
		int oldCapacity = this.allPlayers.length;

		//2. 通过原数组元素计算新的元素个数 , 大约相对于原本元素个数的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);

		//3. 创建新的数组，元素格式是原本的1.5倍
		Player[] newArray = new Player[newCapacity];

		//4. 利用循环拷贝数据
		for (int i = 0; i < oldCapacity; i++) {
			newArray[i] = this.allPlayers[i];
		}

		//5. 地址交换
		this.allPlayers = newArray;
	}

	/**
	 * 私有化的方法，只提供给类内使用，用来获取指定球员ID在数组中下标位置
	 * @param playerID 要查询的球员ID号
	 * @return int类型，返回查询的数据在数组中的下标位置，如果没有找到，返回-1
	 */
	private int findPlayerIndexByPlayerID(int playerID) {
		//参数合法性判断
		if (playerID < 1 || playerID > 100) {
			System.out.println("传入球员ID不合法");
			return -1;
		}

		//用来保存球员的下标位置
		int index = -1;

		for (int i = 0; i < itemCount; i++) {
			//拿球员的ID和传入的ID进行比较
			if (allPlayers[i].getId() == playerID) {
				index = i;
				break;
			}
		}

		return index;
	}

	private void showSortResult(Player[] sortArray) {
		//增强for循环
		for (Player player : sortArray) {
			System.out.println(player);
		}
		//普通for循环
		//		for (int i = 0; i < sortArray.length; i++) {
		//			System.out.println(sortArray[i]);
		//		}
	}
}





















